
To remove the default JTA implementation of the transaction service and replace it with the JTS for
full distributed transaction support, follow the steps below.

- Do not attempt to use a server with both the JTA and JTS .jar files or both the JTA and JTS config files present.

- By default the JTA libraries are installed in JBOSS_HOME/common/lib and used by every server configuration.
  Each server configuration has its own JTA config file, JBOSS_HOME/server/<config>/conf/jbossjta-properties.xml

- To replace the JTA with JTS for every server <config> dir (not recommended):
  delete JBOSS_HOME/common/lib/jbossjta.jar
  delete JBOSS_HOME/common/lib/jbossjta-integration.jar
  copy jbossjts.jar, jbossjts-integration.jar and jbossjts-jacorb.jar to JBOSS_HOME/common/lib
  delete the JBOSS_HOME/server/<config>/conf/jbossjta-properties files
  copy jbossjts-properties.xml to each of the JBOSS_HOME/server/<config>/conf directories
  Add jacorb to any server config that does not already contain it
  Follow the file editing steps below for each server config

- To replace the JTA with JTS for a single server <config> dir:
  copy JBOSS_HOME/common/lib/jbossjta.jar and JBOSS_HOME/common/lib/jbossjta-integration.jar to
  JBOSS_HOME/server/<config>/lib/ for each server you wish to continue using it with
  delete JBOSS_HOME/common/lib/jbossjta.jar
  delete JBOSS_HOME/common/lib/jbossjta-integration.jar
  copy jbossjts.jar, jbossjts-integration.jar and jbossjts-jacorb.jar to
  JBOSS_HOME/server/<config>/lib for each server you wish to use it with i.e. the one that don't have the JTA.
  For each server using the JTS, delete JBOSS_HOME/server/<config>/conf/jbossjta-properties.xml
  and copy jbossjts-properties.xml to JBOSS_HOME/server/<config>/conf/ instead.
  Follow the file editing steps below for each server config using the JTS.


 - complete the installation by making the following edits to the server configuration
    (These are difficult to automate with ant and hence are manual steps for now)

 - edit the conf/jbossjts-properties.xml file and remove the
   recovery extension property containing the value
   "com.arjuna.ats.internal.jta.recovery.arjunacore.XARecoveryModule"

 - In deploy/transaction-jboss-beans.xml, update the TransactionManager class and dependencies as follows:

    <bean name="TransactionManager" class="com.arjuna.ats.jbossatx.jts.TransactionManagerService">
        <annotation>@org.jboss.aop.microcontainer.aspects.jmx.JMX(name="jboss:service=TransactionManager",
            exposedInterface=com.arjuna.ats.jbossatx.jts.TransactionManagerServiceMBean.class, registerDirectly=true)
        </annotation>

        ...
        <start>
           <parameter><inject bean="jboss:service=CorbaORB" property="ORB"/></parameter>
        </start>
        ...
    </bean>

 - Edit the conf/jacorb.properies as follows.
  - change the jacorb.poa.thread_pool_max property to 32

 - Edit the deploy/iiop-service.xml and modify the
   PortableInterceptorInitializers attribute as follows.
  - remove the following lines
         <!-- comment out to disable null transaction propagation over IIOP -->
         <initializer>org.jboss.tm.iiop.TxServerClientInterceptorInitializer</initializer>
         <!-- comment out to disable transaction demarcation over IIOP -->
         <initializer>org.jboss.tm.iiop.TxServerInterceptorInitializer</initializer>
  - add the following lines
         <!-- JBoss TS interceptor. -->
         <initializer>com.arjuna.ats.jts.orbspecific.jacorb.interceptors.interposition.InterpositionORBInitializerImpl</initializer>
         <!-- RMI/IIOP tx context interceptor -->
         <initializer>com.arjuna.ats.jbossatx.jts.InboundTransactionCurrentInitializer</initializer>

For each deployment of JacORB, you will need to ensure that the jacorb.implname in the jacorb.properties
file is unique.

If running an external recovery manager, edit conf/jbossjts-properties.xml to remove
the recovery activator property named "com.arjuna.ats.arjuna.recovery.recoveryActivator_1".
This may be necessary in cluster setups where the ObjectStore is shared, as
there should be only one recovery manager per ObjectStore.

Finally, note that the application server binds to the
localhost address by default. This is generally inappropriate for distributed transactions,
so please ensure the server is bound to a alternative address.

There is a short article on the JBoss wiki that describes some typical JTS
usage scenarios. It is available at the url https://www.jboss.org/community/docs/DOC-13179

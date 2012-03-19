package aspect;

import org.jboss.aop.joinpoint.ConstructorInvocation;
import org.jboss.aop.joinpoint.FieldWriteInvocation;
import org.jboss.aop.joinpoint.MethodInvocation;

public class LoggingAspect
{
/*
   public Object log(ConstructorInvocation invocation) throws Throwable
   {
      try
      {
         System.out.println("C: Creating BankAccount using constructor " + invocation.getConstructor());
         System.out.println("C: Account number: " + invocation.getArguments()[0]);
         return invocation.invokeNext();
      }
      finally
      {
         System.out.println("C: Done");
      }
   }
*/
   
   public Object log(MethodInvocation invocation) throws Throwable
   {
      try
      {
         System.out.println("M: Calling method " + invocation.getMethod().getName());
         //System.out.println("M: Amount " + invocation.getArguments()[0]);
         return invocation.invokeNext();
      }
      finally
      {
         System.out.println("M: Done");
      }
   }

/*
   public Object log(FieldWriteInvocation invocation) throws Throwable
   {
      BankAccount account = (BankAccount)invocation.getTargetObject();
      System.out.println("F: setting field " + invocation.getField().getName() + " for BankAccount " + account.getAccountNumber());
      System.out.println("F: Field old value " + account.getBalance());
      System.out.println("F: New value will be " + invocation.getValue());
      try
      {
         return invocation.invokeNext();
      }
      finally
      {
         System.out.println("F: Field new value " + account.getBalance());
         System.out.println("F: Done");
      }
   }
*/
}

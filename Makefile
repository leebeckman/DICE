#Need:
#Build web application (jgossip war file, should include all dependencies)
#Build aspects jar
#Apply aspects to web application (include adding aspects to database code)
#Deploy to tomcat

JGOSSIP_BUILDROOT = jgossip
JGOSSIP_WARDEST = output
TAINTASPECT_SRCROOT = src/taint

CC = javac
CCA = ajc
JAR = jar
CFLAGS = -c

all: dist

dist: weave deploy

jgossip:

aspects:

weave: aspects jgossip

deploy:

clean:
	rm -rf $(CLIENT_WEBROOT)
	rm -rf $(SERVER_WEBROOT)


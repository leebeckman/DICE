SHARED_SRCROOT = src/shared
SHARED_SRCS = $(SHARED_SRCROOT)/*.coffee

CLIENT_SRCROOT = src/client
CLIENT_SRCS = $(CLIENT_SRCROOT)/*.coffee $(CLIENT_SRCROOT)/gui/*.coffee $(CLIENT_SRCROOT)/sprites/*.coffee $(CLIENT_SRCROOT)/sprites/strategies/*.coffee $(CLIENT_SRCROOT)/state/*.coffee $(CLIENT_SRCROOT)/terrains/*.coffee $(CLIENT_SRCROOT)/views/*.coffee
CLIENT_STATICSRC = $(CLIENT_SRCROOT)/static

SERVER_SRCROOT = src/server
SERVER_SRCS = $(SERVER_SRCROOT)/*.coffee $(SERVER_SRCROOT)/sprites/*.coffee src/testspace/*.coffee

CLIENT_WEBROOT = www/public
CLIENT_BINROOT = $(CLIENT_WEBROOT)/js
SERVER_WEBROOT = www/private
SERVER_BINROOT = $(SERVER_WEBROOT)/js

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
	mkdir $(CLIENT_WEBROOT) -p
	cp -rfu $(CLIENT_STATICSRC)/* $(CLIENT_WEBROOT)
	$(CC) $(CFLAGS) -o $(CLIENT_BINROOT) $(SHARED_SRCS)
	$(CC) $(CFLAGS) -o $(CLIENT_BINROOT) $(CLIENT_SRCS)

aspects:
	$(CC) $(CFLAGS) -o $(SERVER_BINROOT) $(SHARED_SRCS)
	$(CC) $(CFLAGS) -o $(SERVER_BINROOT) $(SERVER_SRCS)

weave: aspects jgossip
	# the watch flag keeps the output file in sync with updates to the coffee source
	$(CC) $(CFLAGS)w -o $(CLIENT_BINROOT) $(SHARED_SRCS) &
	$(CC) $(CFLAGS)w -o $(CLIENT_BINROOT) $(CLIENT_SRCS) &
	$(CC) $(CFLAGS)w -o $(SERVER_BINROOT) $(SHARED_SRCS) &
	$(CC) $(CFLAGS)w -o $(SERVER_BINROOT) $(SERVER_SRCS) &

deploy:

clean:
	rm -rf $(CLIENT_WEBROOT)
	rm -rf $(SERVER_WEBROOT)


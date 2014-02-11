## $Id$
## jhnoll@gmail.com
#####################################################################
# Installation environment.  

# This directory is where stuff served by Apache (for example, the
# chatbot's 'home' page) goes.  
HTML.dir=${HOME}/public_html/${PROJECT}
# CGI scripts go here:
CGI.dir=$(HTML.dir)/cgi-bin

#####################################################################
# Project-specific parameters that should not be modified by users.

#
# Content to be installed
#

# Static web pages and forms to be installed:
PAGES=hello.html num_sites.html
# CGI (and other) scripts to be installed:
SCRIPTS=hello.cgi

#
# Values for creating the distribution.
#

# Set PROJECT to the name of your project.
PROJECT=group3
# The upcoming release.
RELEASE=0
# The tar archive will be identified by version.  Increment this each
# time you add new functionality.
RELEASE_CANDIDATE=1
RELEASE_NAME=${PROJECT}_R${RELEASE}_rc${RELEASE_CANDIDATE}
# Things to be excluded from the tar archive, after the workspace is cleaned.
TAR_EXCLUDE=--exclude='.svn' --exclude='.git' --exclude ${RELEASE_NAME}.tar.gz


# File creation modes.  Please do not modify these: they work on
# proisis.lero.ie.
FILE_MODE=ug+rwX,o+rX
DIR_MODE=ug+rwXs,o+rX
SCRIPT_MODE=ug+rwx,o+rx

#####################################################################
# Some programs used by rules below.

# Use ${INSTALL} to ensure all files & dirs get created with the right
# permissions. 
INSTALL=install

#####################################################################
# The rules.

all: test

# This rule just prints information about what will be built or installed.
what:
	@echo "PROJECT: " ${PROJECT} 
	@echo "RELEASE: " ${RELEASE}
	@echo "RELEASE_CANDIDATE: " ${RELEASE_CANDIDATE}
	@echo "RELEASE_NAME: " ${RELEASE_NAME}

# The 'test' rule should run any unit tests, but because it depends on
# 'build', it will build the system first.
test: build


# The 'build' rule should do things like compile any code, 
# create a database if necessary, etc.
# CAUTION: if you need to ship a default populated database, it should
# be created and populated here.  DO NOT do this by hand else you
# won't have a repeatable, reliable build process.
JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class

build: Backend.class Components.class GamePanes.class PaneScreens.class SwingFramework.class

Backend.class:
	$(JC) backend/*.java

Components.class:
	$(JC) components/*.java

GamePanes.class:
	$(JC) gamePanes/*.java

PaneScreens.class:
	$(JC) paneScreens/*.java

SwingFramework.class:
	$(JC) swingFramework/*.java

run:
	$(J) backend/Main

# Install the application for deployment by Apache.
# install -d creates a directory if necessary.
install: test ${WSGI.script}
	${INSTALL} --mode ${DIR_MODE} -d ${HTML.dir}
	${INSTALL} --mode ${DIR_MODE} -d ${CGI.dir}
	${INSTALL} --mode ${FILE_MODE} ${PAGES} ${HTML.dir}
	${INSTALL} --mode ${SCRIPT_MODE} ${SCRIPTS} ${CGI.dir}
	${INSTALL} --mode ${FILE_MODE} htaccess ${CGI.dir}/.htaccess

# Make a distribution archive from the current workspace.
# the 'distclean' dependency insures that the distribution is 
# free of derived files and other cruft.  Make sure 'distclean' 
# actually cleans!
# The '--transform' switch to 'tar' prepends a directory name to 
# each path in the archive, so that the distribution unpacks 
# into its own sub-directory.  This is extremely important, as it 
# ensures your project won't interfere with something else when
# it is unpacked.
dist: distclean
	tar ${TAR_EXCLUDE} --transform='s,^./,${RELEASE_NAME}/,' -cvzf ${RELEASE_NAME}.tar.gz .


clean: 
	$(RM) *.class


distclean: clean
	rm -f *.pyc ${DB}

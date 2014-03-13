#####################################################################
# Installation environment.

DESTDIR=$(HOME)/.local
PREFIX=$(DESTDIR)/usr
BINDIR=$(PREFIX)/bin
SYSCONFDIR=$(DESTDIR)/etc

#####################################################################
# Project-specific parameters that should not be modified by users.
#
# Set PROJECT to the name of your project.
PROJECT=group3
# The upcoming release.
RELEASE=1
# The tar archive will be identified by version.  Increment this each
# time you add new functionality.
RELEASE_CANDIDATE=4
RELEASE_NAME=${PROJECT}_R${RELEASE}_rc${RELEASE_CANDIDATE}
# Things to be excluded from the tar archive, after the workspace is cleaned.
TAR_EXCLUDE=--exclude={'.svn','.git','.gitignore','.*.swp','*.tar.gz'}


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

.PHONY: all what test build jar run install dist clean distclean

all:
	@ant

# This rule just prints information about what will be built or installed.
what:
	@echo "PROJECT: " ${PROJECT}
	@echo "RELEASE: " ${RELEASE}
	@echo "RELEASE_CANDIDATE: " ${RELEASE_CANDIDATE}
	@echo "RELEASE_NAME: " ${RELEASE_NAME}

build:
	@ant build

GSD.jar: jar
jar:
	@ant jar

run:
	@ant run

test:
	@ant test

install: GSD.jar
	@echo Installing files...
	@$(INSTALL) --mode $(DIR_MODE) -d $(PREFIX)
	@$(INSTALL) --mode $(FILE_MODE) GSD.jar $(PREFIX)/${PROJECT}.jar

dist: distclean
	-tar ${TAR_EXCLUDE} --transform='s,^./,${RELEASE_NAME}/,' -cvzf ${RELEASE_NAME}.tar.gz .

clean:
	@ant clean

distclean: clean

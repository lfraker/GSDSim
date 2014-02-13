#####################################################################
# Installation environment.

DESTDIR=$(HOME)/.local
PREFIX=$(DESTDIR)/usr
BINDIR=$(PREFIX)/bin
SYSCONFDIR=$(DESTDIR)/etc

JUNIT=/usr/share/java/junit.jar

#####################################################################
# Project-specific parameters that should not be modified by users.
#
# Set PROJECT to the name of your project.
PROJECT=group3
# The upcoming release.
RELEASE=0
# The tar archive will be identified by version.  Increment this each
# time you add new functionality.
RELEASE_CANDIDATE=2
RELEASE_NAME=${PROJECT}_R${RELEASE}_rc${RELEASE_CANDIDATE}
# Things to be excluded from the tar archive, after the workspace is cleaned.
TAR_EXCLUDE=--exclude={'.svn','.git','.gitignore','.*.swp',${RELEASE_NAME}.tar.gz}


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

.PHONY: all what test build run install dist clean distclean

SRC := src
BIN := bin

JFLAGS = -g -sourcepath $(SRC) -cp $(BIN):$(JUNIT) -d $(BIN)
JC = javac
J = java

ENTRY := game/backend/Main
SRCS := $(shell find $(SRC) -name '*Test.java' -prune -or -type f -name '*.java' -printf '%P ')
OBJS := $(addprefix $(BIN)/, ${SRCS:.java=.class})

TSRCS := $(shell find $(SRC) -type f -name '*Test.java' -printf '%P ')
TOBJS := $(addprefix $(BIN)/, ${TSRCS:.java=.class})

$(BIN)/%.class: $(SRC)/%.java | $(BIN)
	$(JC) $(JFLAGS) $<

all: test

$(BIN):
	@mkdir -p $@

# This rule just prints information about what will be built or installed.
what:
	@echo "PROJECT: " ${PROJECT}
	@echo "RELEASE: " ${RELEASE}
	@echo "RELEASE_CANDIDATE: " ${RELEASE_CANDIDATE}
	@echo "RELEASE_NAME: " ${RELEASE_NAME}

test: build $(TOBJS)
	@echo Running tests...
	@$(J) -cp $(BIN):$(JUNIT) org.junit.runner.JUnitCore \
		`echo ${TSRCS:.java=} | tr -t / .`

build: $(BIN)/$(ENTRY).class $(OBJS) $(BIN)/gameFiles

# TODO: remove when program reads from a set place instead of working dir.
$(BIN)/gameFiles: | $(BIN)
	@cp -fr gameFiles $(BIN)/

run: build
	cd $(BIN) && $(J) $(ENTRY)

# install -d creates a directory if necessary.
install: test $(RELEASE_NAME).jar
	@echo Installing files...
	@$(INSTALL) --mode $(DIR_MODE) -d $(PREFIX)
	@$(INSTALL) --mode $(FILE_MODE) $(RELEASE_NAME).jar $(PREFIX)/${PROJECT}.jar
#	TODO: what do we need to do with the default(?) settings file?

$(RELEASE_NAME).jar: $(OBJS)
	@find $(BIN) -name '*.class' -printf '-C $(BIN) %P ' | \
		xargs jar cfe $(RELEASE_NAME).jar $(ENTRY)

dist: distclean
	-tar ${TAR_EXCLUDE} --transform='s,^./,${RELEASE_NAME}/,' -cvzf ${RELEASE_NAME}.tar.gz .

clean:
	@[ $(BIN) = $(SRC) ] \
		&& find $(BIN) -name '*.class' -print0 | xargs -0 -r $(RM) \
		|| $(RM) -r $(BIN)
	@$(RM) *.jar

distclean: clean

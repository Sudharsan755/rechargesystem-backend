#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper Startup Script for Unix / Linux / Cloud Deployments
# ----------------------------------------------------------------------------

MAVEN_PROJECTBASEDIR="${0%/*}"
WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_LAUNCHER="org.apache.maven.wrapper.MavenWrapperMain"

if [ -n "$JAVA_HOME" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

exec "$JAVACMD" \
  -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" \
  -cp "$WRAPPER_JAR" \
  "$WRAPPER_LAUNCHER" "$@"


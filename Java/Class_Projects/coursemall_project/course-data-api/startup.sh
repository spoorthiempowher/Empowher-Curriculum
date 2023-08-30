#!/bin/sh
## Standard AppDynamics Config
JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.tierName=${TIER_NAME}"
JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.reuse.nodeName=true -Dappdynamics.agent.reuse.nodeName.prefix=${TIER_NAME}"
JAVA_OPTS="${JAVA_OPTS} -javaagent:/opt/spring/appd/AppServerAgent/javaagent.jar"
JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.controller.hostName=${CONTROLLER_HOST} -Dappdynamics.controller.port=${CONTROLLER_PORT} -Dappdynamics.controller.ssl.enabled=${CONTROLLER_SSL_ENABLED}"
JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.accountName=${ACCOUNT_NAME} -Dappdynamics.agent.accountAccessKey=${ACCOUNT_ACCESS_KEY} -Dappdynamics.agent.applicationName=${APPLICATION_NAME}"
#JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.socket.collection.bci.enable=true"

## Standard Application Config
# JAVA_OPTS="${JAVA_OPTS} -Xms512m -Xmx512m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true"
JAVA_OPTS="${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom"

exec java $JAVA_OPTS -jar /opt/spring/app.jar -Dspring.config.import=file:/opt/spring/config/application.properties
# exec java $JAVA_OPTS -jar /opt/spring/app.jar --spring.config.location=file:/opt/spring/config/application.properties,classpath:/application.properties

FROM eclipse-temurin:21-jdk-alpine as base

ENV WILDFLY_VERSION=30.0.1.Final
ENV JBOSS_HOME=/opt/jboss/wildfly

# Instala dependências e baixa o WildFly
RUN apk add --no-cache curl unzip && \
    curl -L https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.zip -o wildfly.zip && \
    unzip wildfly.zip -d /opt/jboss && \
    mv /opt/jboss/wildfly-${WILDFLY_VERSION} ${JBOSS_HOME} && \
    rm -rf wildfly.zip

# Cria diretório para deploys
RUN mkdir -p ${JBOSS_HOME}/standalone/deployments

# Exponha as portas necessárias
EXPOSE 8080 9990

# Adiciona o WAR (a ser copiado via Docker Compose ou no build)
COPY ./deployments/*.war ${JBOSS_HOME}/standalone/deployments/

WORKDIR ${JBOSS_HOME}
CMD ["./bin/standalone.sh", "-b", "0.0.0.0"]


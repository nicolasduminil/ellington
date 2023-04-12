FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17
LABEL maintainer="Nicolas Duminil <nicolas.duminil@simplex-software.fr>"
RUN /opt/jboss/wildfly/bin/add-user.sh nicolas password123 --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
FROM tomcat:9.0-jre8-alpine

COPY target/flexiflex*.war $CATALINA_HOME/webapps/flexiflex.war

EXPOSE 8080

CMD tail -f /var/lib/tomcat/logs/catalina.out

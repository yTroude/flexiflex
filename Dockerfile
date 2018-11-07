FROM java:8

WORKDIR /

ADD /target/flexiflex-0.0.1-SNAPSHOT.jar flexiflex-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","flexiflex-0.0.1-SNAPSHOT.jar"]
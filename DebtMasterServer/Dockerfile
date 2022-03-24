FROM ubuntu:21.04

RUN apt-get update
RUN apt-get install -y openjdk-17-jre curl

ADD target/DebtMasterServer-1.0-SNAPSHOT-jar-with-dependencies.jar /srv/DebtMasterServer-1.0-SNAPSHOT-jar-with-dependencies.jar

WORKDIR /srv
EXPOSE 5000

CMD ["java", "-jar", "DebtMasterServer-1.0-SNAPSHOT-jar-with-dependencies.jar"]
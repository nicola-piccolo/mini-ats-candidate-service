FROM openjdk:12-jdk-alpine

RUN addgroup -S candidate-service && adduser -S candidate-service -G candidate-service

ENV HOME_DIRECTORY="/home/candidate-service"
ENV LOGS_DIRECTORY="$HOME_DIRECTORY/logs"

USER candidate-service:candidate-service

RUN mkdir $LOGS_DIRECTORY

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","org.miniats.candidateservice.CandidateServiceApplication"]
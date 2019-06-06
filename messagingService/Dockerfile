FROM openjdk:8
ENV APP_HOME=/home
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8282
CMD sleep 60 && java -jar messagingService-1.jar
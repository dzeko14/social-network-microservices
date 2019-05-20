FROM openjdk:8
ENV APP_HOME=/home
ENV APP_NAME=EurikaServer-1.jar
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8392
CMD ["java", "-jar", "messageConsumer-1.jar"]
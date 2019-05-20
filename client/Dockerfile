FROM openjdk:8
ENV APP_HOME=/home
ENV APP_NAME=EurikaServer-1.jar
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8080
CMD ["java", "-jar", "client-1.jar"]
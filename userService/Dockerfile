FROM openjdk:8
ENV APP_HOME=/home
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8181
CMD sleep 60 && java -jar userService-1.jar
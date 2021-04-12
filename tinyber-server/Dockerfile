#FROM（指定基础镜像）
FROM openjdk:8-jre-alpine

#指定镜像的作者和联系方式信息。
MAINTAINER tinyber "Samuel_way@163.com"

#XPOSE 8089

#EXPOSE 9876

VOLUME /tmp

ENV JAVA_OPTS="-Xms512m -Xmx512m"
ENV JAVA_OPTS="-Xdebug  -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8085"
ENV CONFIG_PROFILE=dev
ENV PARAMS=""

ADD /tinyber-chat/target/tinyber-chat.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS  -Dfile.encoding=UTF8 -Duser.timezone=GMT+08 -Djava.security.egd=file:/dev/./urandom -jar /app.jar ${PARAMS} --spring.profiles.active=$CONFIG_PROFILE"]

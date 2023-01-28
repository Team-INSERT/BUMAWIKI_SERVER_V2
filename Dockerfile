FROM eclipse-temurin:11-jre-focal

EXPOSE 8080
ENV TZ=Asia/Seoul

COPY ./build/libs/bumawiki-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


# docker images
# docker rmi image_id
# docker build -f FILE_PATH -t USERNAME/REPO_NAME:TAG_NAME
# docker login
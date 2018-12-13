FROM openjdk:8
ADD target/Server-1.0.jar ./
ADD target/Server/lib/* ./
EXPOSE 8080 
ENTRYPOINT ["java", "-cp", "*", "com.stackroute.qna.QnAApp"]

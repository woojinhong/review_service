# 베이스 이미지 설정 (OpenJDK)
FROM openjdk:17-jdk-alpine

# JAR 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]

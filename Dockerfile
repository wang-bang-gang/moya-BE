FROM openjdk:17-jdk-slim

WORKDIR /app

# Gradle wrapper 파일들 먼저 복사
COPY gradlew .
COPY gradle gradle

# 권한 설정
RUN chmod +x ./gradlew

# 프로젝트 파일들 복사
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 빌드 (의존성 다운로드 + 컴파일)
RUN ./gradlew bootJar --no-daemon

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "build/libs/moya-0.0.1-SNAPSHOT.jar"]

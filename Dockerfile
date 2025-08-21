FROM openjdk:17-jdk-slim

WORKDIR /app

# 캐시 무효화
ARG CACHEBUST=1

# 전체 프로젝트 복사 (확실하게)
COPY . .

# 권한 설정
RUN chmod +x ./gradlew

# Gradle wrapper 확인 (디버깅)
RUN ls -la gradle/wrapper/

# 빌드
RUN ./gradlew bootJar --no-daemon

EXPOSE 8080
CMD ["java", "-jar", "build/libs/moya-0.0.1-SNAPSHOT.jar"]

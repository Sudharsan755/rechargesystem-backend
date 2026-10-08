@echo off
echo Building RechargeSys Spring Boot JAR file...
call mvnw.cmd clean package -DskipTests
echo.
echo Build complete! JAR file created at target\backend-1.0.0.jar
pause


cmd /C mvnw clean package -Dmaven.test.skip -f pom.xml
cmd /C docker image rm martell805/dll:1.0
cmd /C docker build -t martell805/dll:1.0 .
@REM cmd /C docker push martell805/dll:1.0

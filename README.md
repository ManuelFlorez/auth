# Authentication

## Run Sonar platform local with Docker

````bash
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
````

## Run Sonar scanner

````bash
./gradlew sonar \
  -Dsonar.projectKey=authentication \
  -Dsonar.projectName='authentication' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_03ec797c3bf3fcfbeb189e3e26d3f111241a5f60
````

## Docker Build
````bash
docker build -t authentication:v0.0.1 .
````

## Docker run
````bash
docker run -d -p 8080:8080 --env-file .env authentication:v0.0.1
````
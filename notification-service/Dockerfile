FROM eclipse-temurin:17-jdk-alpine
# Cambia esta línea para apuntar a la ruta correcta desde la raíz
COPY notification-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

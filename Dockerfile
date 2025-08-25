# Etapa 1: Construir el WAR con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Copiar el WAR en Tomcat
FROM tomcat:11-jdk17
WORKDIR /usr/local/tomcat

# Limpiar apps por defecto
RUN rm -rf webapps/*

# Copiar el WAR como ROOT.war
COPY --from=build /app/target/*.war webapps/ROOT.war

# Railway inyecta $PORT, pero ponemos valor por defecto
ENV PORT=8080

# Configurar Tomcat para escuchar en $PORT
CMD sh -c 'PORT=${PORT:-8080} && sed -i "s/8080/${PORT}/g" conf/server.xml && exec catalina.sh run'

# Imagen base con Tomcat 11 + JDK 17
FROM tomcat:11-jdk17

# Eliminar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar tu WAR generado por Maven al contenedor como ROOT.war
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exponer puerto
EXPOSE 8080

# Arrancar Tomcat
CMD ["catalina.sh", "run"]

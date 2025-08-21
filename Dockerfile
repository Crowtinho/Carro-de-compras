# Imagen base con Tomcat 11 y Java 17
FROM tomcat:11-jdk17

# Elimina la aplicación por defecto de Tomcat (ROOT)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu WAR generado por Maven en la carpeta de despliegue de Tomcat
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto que Railway necesita
EXPOSE 8080

# Arranca Tomcat
CMD ["catalina.sh", "run"]

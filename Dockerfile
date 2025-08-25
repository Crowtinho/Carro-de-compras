# Usa la imagen oficial de Tomcat
FROM tomcat:11.0-jdk17

# Elimina aplicaciones por defecto (ROOT, docs, etc.)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu WAR renombrado a ROOT.war
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto dinámico que Railway asigna
EXPOSE ${PORT}

# Arranca Tomcat usando el puerto que viene de la variable de entorno PORT
CMD ["sh", "-c", "catalina.sh run -Dserver.port=${PORT}"]

Proyecto Hotel: Jersey + DataNucleus + MySQL
============================

Este ejemplo se basa en el complemento DataNucleus Maven. Compruebe la configuración de la base de datos en el archivo *datanucleus.properties* y la dependencia del controlador JDBC especificada en el archivo *pom.xml*. Además, el proyecto contiene los códigos de ejemplo de servidor y cliente.

 - Ejecute el siguiente comando para compilar todo y mejorar las clases de base de datos:

      mvn clean compile

- Asegúrese de que la base de datos se haya configurado correctamente. Use el contenido del archivo *HLHotelesbd.sql* para crear la base de datos y otorgar privilegios. Por ejemplo,

      mysql –uroot -p < sql/HLHotelesdb.sql

- Ejecute el siguiente comando para crear un esquema de base de datos para esta muestra.

      mvn datanucleus:schema-create
      
- Abra MySQL y ejecute el script ejemplos-hoteles.sql

- Para iniciar el servidor, ejecute el comando

      mvn jetty:run

- Ahora, el código de muestra del cliente se puede ejecutar en una nueva ventana de comandos con

      mvn exec:java -Pclient

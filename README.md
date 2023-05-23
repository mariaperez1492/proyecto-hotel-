Proyecto Hotel
============================

El proyecto se trata de una aplicación de reservas para una cadena hotelera. La aplicación cuenta con un login por el que pueden acceder dos tipos de usuarios:
- Administrador. Existe un único administrador cuyos credenciales son:

      dni: 0000
      contraseña: admin
      
- Para poder ver las estadísticas de cada hotel, tienes que poner la siguiente fecha para filtrar:
		
		fecha: 2023-05-10
- Cliente. Los clientes se pueden registrar a través de la ventana de registro. Además, existe un cliente por defecto, cuyos credenciales son:

      dni: 0000000A
      contraseña: 1234567Ab*

## Ejecutar el proyecto
El proyecto contiene los códigos del servidor y el cliente. Para ejecutar el proyecto siga los siguientes pasos:

- Paso 1: Ejecute el siguiente comando para compilar todo y mejorar las clases de base de datos:

      mvn clean compile

- Paso 2: Ejecute el DataNuecleus:

      mvn datanucleus:enhance
      
- Paso 3: Asegurese de que la base de datos se haya configurado correctamente. Abra el script *HLHotelesbd.sql* en MySQL WorkBench y ejecutelo, para crear la base de datos y otorgar privilegios.
- Paso 4: Ejecute el siguiente comando para crear un esquema de base de datos para esta muestra.

      mvn datanucleus:schema-create
      
- Paso 5: Carge los datos de muestra en la base de datos. Abra el script *ejemplos-hoteles.sql* en MySQL WorkBench y ejecutelo.
- Paso 6: Para iniciar el servidor, ejecute el siguiente comando:

      mvn jetty:run

- Paso 7: Ahora, el codigo del cliente se puede ejecutar en una nueva ventana de comandos con 

      mvn exec:java -Pclient

## Pruebas unitarias
Antes de las realizas las pruebas unitarias ve al  *pom.xml* y compruebe que esté...
      <excludedGroups>categories.IntegrationTest, categories.PerformanceTest</excludedGroups>
      
- Para ejecutar las pruebas unitarias, ejecute los siguinetes comandos:

      mvn clean compile
      mvn test    
      
- Para comprobar que los test cumplen con la mínima cobertura de nuestro código (95%), ejecute el siguiente comando:

      mvn verify
      
- Para ver el informe de cobertura ejecute el siguinete comando. Aparecerá ir a target/site/jacoco/index.html

      mvn verify jacoco:report
      
## Pruebas de rendimiento e integración
Antes de las realizas las pruebas unitarias ve al  *pom.xml* y compruebe que esté descomentado
      <excludedGroups>categories.UnitTest</excludedGroups>
      
- Para ejecutar las pruebas unitarias, debe ejecutar el proyecto hasta el paso 4 (paso 4 incluido) y luego ejecutar el siguiente comando
      mvn test   

## Documentación
Para generar la documentación medinate Doxygen, ejecute el siguinete comando

      mvn doxygen:report

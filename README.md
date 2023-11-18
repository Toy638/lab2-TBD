Bienvenido/a al manual de usuario.


-- Este documento corresponde a las instrucciones de uso y de instalación de tecnologías para poder --
-- hacer funcionar la aplicación de la entrega1 de laboratorio.                                     --


1) Para la base de datos, se debe instalar lo siguiente:

   -) PostgreSQL (versión 11+) + PostGIS
   -) pgAdmin 4
  
   Para el backend (que fué trabajada como una API RESTful desarrollada en Spring), se debe instalar
   lo siguiente:

   -) JDK (Versión 17)
   -) IntelliJ IDEA (tanto para backend como frontend)
   -) Postman

   Para el frontend, se debe instalar lo siguiente:

   -) Vue.js (versión 2.x)
   -) IntelliJ IDEA (tanto para backend como frontend)


2) 

2.1) Para hacer funcionar la aplicación se debe abrir la aplicación pgAdmin4, se debe crear una nueva
     base de datos llamada lab1TBD en pgAdmin4. Una vez creada, se debe cargar el script "dbCreate.sql"
     para poder crear las tablas correspondientes.

     Luego, se debe cargar el script "loadData.sql" para poder poblar las tablas creadas con anterioridad.

2.2) Ejecutar el Backend a través de IntelliJ para habilitar el servidor que permita la ejecución del código.


2.3) Correr el Frontend a través de IntellIJ. Para ello se ha de abrir una terminal en la
     carpeta “frontend” que se encuentra dentro de la carpeta “FrontEnd-TBD”. Ahí, se debe
     ingresar el comando “npm install” y luego “npm run serve” para poder ejecutar el front
     del código fuente.


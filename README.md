# Manual de Usuario - Entrega 2 Taller de Bases de Datos

Bienvenido/a al manual de usuario para la aplicación de la entrega 2 del laboratorio de Taller de Bases de Datos.

## Requisitos de Instalación

### 1. Base de Datos (PostgreSQL + PostGIS):

- PostgreSQL (versión 11+)
- PostGIS (versión 3.4.0)
- pgAdmin 4

### 2. Backend (API RESTful en Spring):

- JDK (Versión 17)
- IntelliJ IDEA (para backend y frontend)
- Postman

### 3. Frontend:

- Vue.js (versión 2.x)
- IntelliJ IDEA (para backend y frontend)

## Instrucciones de Configuración

### 2.1 Configuración de la Base de Datos:

1. Abre pgAdmin4 y crea una nueva base de datos con el nombre: lastgis

Luego, con los siguientes scripts ubicados en la carpeta 'lab2-TBD', haz lo siguiente (en pgAdmin4):

2. Carga el script "dbCreate.sql" para crear las tablas.
3. Carga el script "loadData.sql" para poblar las tablas.
4. Carga el script "loadDataPolygons.sql" para agregar polígonos de las regiones de Chile.

### 2.2 Ejecutar el Backend:

1. Ejecuta el backend a través de IntelliJ para habilitar el servidor.

### 2.3 Ejecutar el Frontend:

1. Abre una terminal en la carpeta “frontend” dentro de “FrontEnd-TBD”.
2. Ingresa los comandos:

npm install
npm run serve

3. Esto ejecutará el frontend.

### 2.4 Acceder a la Aplicación:

1. Ingresa con los siguientes datos:

- Email: mmaccrosson2@printfriendly.com
- Contraseña: wH6_aw

2. En la barra de navegación, selecciona la opción "mapa" para filtrar tareas por región.

Listo, ahora puedes utilizar de forma correcta la aplicación. ¡Gracias!
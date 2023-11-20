
CREATE EXTENSION postgis;

-- Modificada la tabla de "institución" para permitir que una institución tenga muchos usuarios
CREATE TABLE "institucion" (
                               "id_institucion" serial PRIMARY KEY,
                               "nombre" varchar(255),
                               "fecha" DATE,
                               "direccion" varchar(255),
                               "telefono" varchar(20)
);

-- Modificada la tabla de "usuario" para permitir que un usuario esté asociado a una institución
CREATE TABLE "usuario" (
                           "id" serial PRIMARY KEY,
                           "email" varchar(45),
                           "password" varchar(45),
                           "rol" varchar(45),
                           "id_institucion" integer,
                           FOREIGN KEY ("id_institucion") REFERENCES "institucion" ("id_institucion")
);

-- Resto de tus tablas originales sin modificaciones
CREATE TABLE "habilidad" (
                             "id_habilidad" serial PRIMARY KEY,
                             "habilidad" varchar(255)
);

CREATE TABLE "voluntario" (
                              "id_voluntario" serial PRIMARY KEY,
                              "nombre" varchar(255),
                              "apellido" varchar(255),
                              "telefono" varchar(20),
                              "direccion" varchar(255),
                              "id_usuario" integer,
                              FOREIGN KEY ("id_usuario") REFERENCES "usuario" ("id")
);

CREATE TABLE "emergencia" (
                              "id_emergencia" serial PRIMARY KEY,
                              "asunto" varchar(255),
                              "descripcion" TEXT,
                              "direccion" varchar(255),
                              "fecha" DATE,
                              "activa" BOOLEAN,
                              "id_institucion" integer,
                              FOREIGN KEY ("id_institucion") REFERENCES "institucion" ("id_institucion")
);

CREATE TABLE "tarea" (
                         "id_tarea" serial PRIMARY KEY,
                         "asunto_tarea" varchar(255),
                         "estado_tarea" BOOLEAN,
                         "id_emergencia" integer,
                         FOREIGN KEY ("id_emergencia") REFERENCES "emergencia" ("id_emergencia")
);

CREATE TABLE "eme_habilidad" (
                                 "id_eme_habilidad" serial PRIMARY KEY,
                                 "id_habilidad" integer,
                                 "id_emergencia" integer,
                                 FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad"),
                                 FOREIGN KEY ("id_emergencia") REFERENCES "emergencia" ("id_emergencia")
);

CREATE TABLE "ranking" (
                           "id_ranking" serial PRIMARY KEY,
                           "id_tarea" integer,
                           "id_voluntario" integer,
                           "puntaje" integer,
                           FOREIGN KEY ("id_tarea") REFERENCES "tarea" ("id_tarea"),
                           FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario")
);

CREATE TABLE "tarea_habilidad" (
                                   "id_tarea_habilidad" serial PRIMARY KEY,
                                   "id_tarea" integer,
                                   "id_habilidad" integer,
                                   FOREIGN KEY ("id_tarea") REFERENCES "tarea" ("id_tarea"),
                                   FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad")
);

CREATE TABLE "vol_habilidad" (
                                 "id_vol_habilidad" serial,
                                 "id_voluntario" integer,
                                 "id_habilidad" integer,
                                 FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario"),
                                 FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad")
);

-- Agrega una columna llamada 'geom' que almacenará ubicaciones como puntos para tarea 
ALTER TABLE tarea
    ADD COLUMN latitud double precision,
ADD COLUMN longitud double precision,
ADD COLUMN geom geometry(Point, 32719);
-- Poblar
--UPDATE TABLE tarea SET geom = ST_MakePoint()

-- Agrega una columna llamada 'geom' que almacenará puntos ubicaciones como puntos para emergencia
ALTER TABLE emergencia
    ADD COLUMN latitud double precision,
ADD COLUMN longitud double precision,
ADD COLUMN geom geometry(Point, 32719);


-- Creamos la tabla region para poder almacenarlas como polígonos
CREATE TABLE region (
                        id serial PRIMARY KEY,
                        nombre varchar(255),
                        geom geometry(Polygon)
);

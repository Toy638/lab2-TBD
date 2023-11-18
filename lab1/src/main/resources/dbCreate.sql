CREATE TABLE "usuario" (
                           "id_usuario" serial PRIMARY KEY,
                           "email" varchar(45),
                           "password" varchar(45),
                           "rol" varchar(45)
);

CREATE TABLE "habilidad" (
                             "id_habilidad" serial PRIMARY KEY,
                             "habilidad" varchar(255)
);

CREATE TABLE "institucion" (
                               "id_institucion" serial PRIMARY KEY,
                               "nombre" varchar(255),
                               "fecha" DATE,
                               "direccion" varchar(255),
                               "telefono" varchar(20),
                               "id_usuario" int
);

CREATE TABLE "voluntario" (
                              "id_voluntario" serial PRIMARY KEY,
                              "nombre" varchar(255),
                              "apellido" varchar(255),
                              "telefono" varchar(20),
                              "direccion" varchar(255),
                              "id_usuario" int
);

CREATE TABLE "emergencia" (
                              "id_emergencia" serial PRIMARY KEY,
                              "asunto" varchar(255),
                              "descripcion" TEXT,
                              "direccion" varchar(255),
                              "fecha" DATE,
                              "active" BOOLEAN,
                              "id_institucion" int
);

CREATE TABLE "tarea" (
                         "id_tarea" serial PRIMARY KEY,
                         "asunto_tarea" varchar(255),
                         "estado_tarea" BOOLEAN,
                         "id_emergencia" int
);

CREATE TABLE "eme_habilidad" (
                                 "id_eme_habilidad" serial PRIMARY KEY,
                                 "id_habilidad" int,
                                 "id_emergencia" int
);

CREATE TABLE "ranking" (
                           "id_ranking" serial PRIMARY KEY,
                           "id_tarea" int,
                           "id_voluntario" int,
                           "puntaje" int
);

CREATE TABLE "tarea_habilidad" (
                                   "id_tarea_habilidad" serial PRIMARY KEY,
                                   "id_tarea" int,
                                   "id_habilidad" int
);

CREATE TABLE "vol_habilidad" (
                                 "id_vol_habilidad" serial,
                                 "id_voluntario" int,
                                 "id_habilidad" int
);

ALTER TABLE "usuario" ADD FOREIGN KEY ("id_usuario") REFERENCES "institucion" ("id_usuario");

ALTER TABLE "usuario" ADD FOREIGN KEY ("id_usuario") REFERENCES "voluntario" ("id_usuario");

ALTER TABLE "emergencia" ADD FOREIGN KEY ("id_institucion") REFERENCES "institucion" ("id_institucion");

ALTER TABLE "emergencia" ADD FOREIGN KEY ("id_emergencia") REFERENCES "tarea" ("id_emergencia");

ALTER TABLE "eme_habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad");

ALTER TABLE "eme_habilidad" ADD FOREIGN KEY ("id_emergencia") REFERENCES "emergencia" ("id_emergencia");

ALTER TABLE "ranking" ADD FOREIGN KEY ("id_tarea") REFERENCES "tarea" ("id_tarea");

ALTER TABLE "ranking" ADD FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario");

ALTER TABLE "tarea" ADD FOREIGN KEY ("id_tarea") REFERENCES "tarea_habilidad" ("id_tarea");

ALTER TABLE "habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "tarea_habilidad" ("id_habilidad");

ALTER TABLE "vol_habilidad" ADD FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario");

ALTER TABLE "vol_habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad");

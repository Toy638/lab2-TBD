-- Tabla de auditoría para registrar las operaciones --
CREATE TABLE audit_log (
  id serial PRIMARY KEY,
  table_name varchar(255),
  operation varchar(10),
  usuario varchar(45),
  timestamp timestamp,
  query text
);

-- Función de auditoría --
CREATE OR REPLACE FUNCTION audit_trigger_function()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO audit_log (table_name, operation, usuario, timestamp, query)
  VALUES (TG_TABLE_NAME, TG_OP, current_user, current_timestamp, NEW::text);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Triggers para las tablas a auditar --
----------------------------------------

-- Trigger para tabla institucion --
CREATE TRIGGER audit_trigger_institucion
AFTER INSERT OR UPDATE OR DELETE ON institucion
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla usuario --
CREATE TRIGGER audit_trigger_usuario
AFTER INSERT OR UPDATE OR DELETE ON usuario
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla habilidad --
CREATE TRIGGER audit_trigger_habilidad
AFTER INSERT OR UPDATE OR DELETE ON habilidad
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla voluntario --
CREATE TRIGGER audit_trigger_voluntario
AFTER INSERT OR UPDATE OR DELETE ON voluntario
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla emergencia --
CREATE TRIGGER audit_trigger_emergencia
AFTER INSERT OR UPDATE OR DELETE ON emergencia
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla tarea --
CREATE TRIGGER audit_trigger_tarea
AFTER INSERT OR UPDATE OR DELETE ON tarea
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla eme_habilidad --
CREATE TRIGGER audit_trigger_eme_habilidad
AFTER INSERT OR UPDATE OR DELETE ON eme_habilidad
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla ranking --
CREATE TRIGGER audit_trigger_ranking
AFTER INSERT OR UPDATE OR DELETE ON ranking
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla tarea_habilidad --
CREATE TRIGGER audit_trigger_tarea_habilidad
AFTER INSERT OR UPDATE OR DELETE ON tarea_habilidad
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();

-- Trigger para tabla vol_habilidad --
CREATE TRIGGER audit_trigger_vol_habilidad
AFTER INSERT OR UPDATE OR DELETE ON vol_habilidad
FOR EACH ROW
EXECUTE FUNCTION audit_trigger_function();



-- Testeo: SELECT * FROM audit_log;
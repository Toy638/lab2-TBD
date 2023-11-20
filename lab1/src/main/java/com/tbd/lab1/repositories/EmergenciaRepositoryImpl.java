package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.EmergenciaEntity;
import net.postgis.jdbc.geometry.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.ArrayList;
import java.util.List;
// import org.springframework.data.geo.Point;

@Repository
public class EmergenciaRepositoryImpl implements EmergenciaRepository{
    @Autowired
    private Sql2o sql2o;
    private final Logger logger = LoggerFactory.getLogger(EmergenciaRepositoryImpl.class);

    @Override
    public List<EmergenciaEntity> findAll() {
        List<EmergenciaEntity> emergencias = new ArrayList<>();
        String sqlQuery = "SELECT * FROM emergencia ORDER BY id_emergencia ASC";
        try (Connection con = sql2o.open()) {
            emergencias = con.createQuery(sqlQuery).executeAndFetch(EmergenciaEntity.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // Conexion a sql ha fallado
        }
        return emergencias;
    }

    @Override
    public EmergenciaEntity create(EmergenciaEntity emergencia) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String sqlQuery = "INSERT INTO emergencia (id_emergencia, asunto, fecha, descripcion, direccion, activa, id_institucion, latitud, longitud, geom) " +
                "VALUES (:id_emergencia, :asunto, :fecha, :descripcion, :direccion, :activa, :id_institucion, :latitud, :longitud, ST_GeomFromText(:point, 32719))";

        try (Connection con = sql2o.beginTransaction()) {

            con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", emergencia.getId_emergencia())
                    .addParameter("asunto", emergencia.getAsunto())
                    .addParameter("fecha", emergencia.getFecha())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("direccion", emergencia.getDireccion())
                    .addParameter("activa", emergencia.getActiva())
                    .addParameter("id_institucion", emergencia.getId_institucion())
                    .addParameter("latitud", emergencia.getLatitud())
                    .addParameter("longitud", emergencia.getLongitud())
                    .addParameter("point", "POINT(" + emergencia.getLongitud() + " " + emergencia.getLatitud() + ")")
                    .executeUpdate();

            con.commit();
        } catch (Exception e) {
            // Manejo de excepciones, por ejemplo, loggear el error.
            e.printStackTrace();
            // También podrías lanzar una excepción personalizada si lo consideras necesario.
        }

        return emergencia;
    }


    @Override
    public EmergenciaEntity findById(Long id_emergencia) {
        String sqlQuery = "SELECT * FROM emergencia WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", id_emergencia)
                    .executeAndFetchFirst(EmergenciaEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public EmergenciaEntity findByTarea(Long idTarea) {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT e.* FROM emergencia e INNER JOIN tarea t ON t.id_emergencia = e.id_emergencia WHERE t.id_tarea = :idTarea";
            return connection.createQuery(query)
                    .addParameter("idTarea", idTarea)
                    .executeAndFetchFirst(EmergenciaEntity.class);
        }
    }

    @Override
    public EmergenciaEntity update(EmergenciaEntity emergencia) {
        String sqlQuery = "UPDATE emergencia SET asunto = :asunto, descripcion = :descripcion, direccion = :direccion, fecha =:fecha, activa = :activa, id_institucion = :idInstitucion, latitud = :latitud, longitud = :longitud, geom = ST_GeomFromText(:point, 32719) WHERE id_emergencia = :idEmergencia";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            con.createQuery(sqlQuery)
                    .addParameter("idEmergencia", emergencia.getId_emergencia())
                    .addParameter("asunto", emergencia.getAsunto())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("direccion", emergencia.getDireccion())
                    .addParameter("fecha", emergencia.getFecha())
                    .addParameter("activa", emergencia.getActiva())
                    .addParameter("idInstitucion", emergencia.getId_institucion())
                    .addParameter("latitud", emergencia.getLatitud())
                    .addParameter("longitud", emergencia.getLongitud())
                    .addParameter("point", "POINT(" + emergencia.getLongitud() + " " + emergencia.getLatitud() + ")")
                    .executeUpdate();
            con.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return findById(emergencia.getId_emergencia());
    }

    @Override
    public void delete(Long id_emergencia) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String sqlQuery = "DELETE FROM emergencia WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.beginTransaction()){

            con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", id_emergencia)
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cambiarEstado(Long id_emergencia) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String sqlSet = "SELECT set_tbd_usuario(:username)";
        String sqlQuery = "UPDATE emergencia SET activa = NOT activa WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.beginTransaction()){
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", id_emergencia)
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countTareasByEmergencia(Long id) {
        String sqlQuery = "SELECT obtener_total_tareas_activas(:id)";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    @Override
    public EmergenciaEntity getFinalId() {
        String sqlQuery = "SELECT * FROM emergencia ORDER BY id_emergencia DESC LIMIT 1";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .executeAndFetchFirst(EmergenciaEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}

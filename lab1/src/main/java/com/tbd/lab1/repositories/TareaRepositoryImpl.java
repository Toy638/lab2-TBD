package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.InstitucionEntity;
import com.tbd.lab1.entities.TareaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TareaRepositoryImpl implements TareaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void create(TareaEntity tarea) {
        String sqlQuery = "INSERT INTO tarea (id_tarea, asunto_tarea, id_emergencia, estado_tarea, latitud, longitud, geom) VALUES (:idTarea, :asuntoTarea, :idEmergencia, :estadoTarea, :latitud, :longitud, ST_GeomFromText(:point, 32719))";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            con.createQuery(sqlQuery)
                    .addParameter("idTarea", tarea.getId_tarea())
                    .addParameter("asuntoTarea", tarea.getAsunto_tarea())
                    .addParameter("idEmergencia", tarea.getId_emergencia())
                    .addParameter("estadoTarea", tarea.getEstado_tarea())
                    .addParameter("latitud", tarea.getLatitud())
                    .addParameter("longitud", tarea.getLongitud())
                    .addParameter("point", "POINT(" + tarea.getLongitud() + " " + tarea.getLatitud() + ")")
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Función create sin seguridad
    /*
    public InstitucionEntity create(InstitucionEntity institucion) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO institucion (nombre, fecha, direccion, telefono, id_usuario) values (:nombre, :fecha, :direccion, :telefono, :id_usuario)", true)
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("fecha", institucion.getFecha())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("telefono", institucion.getTelefono())
                    .addParameter("id_usuario", institucion.getId_usuario())
                    .executeUpdate().getKey();
            return institucion;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
     */

    @Override
    public List<TareaEntity> findAll() {
        List<TareaEntity> tareas = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tarea ORDER BY id_tarea ASC";
        try (Connection con = sql2o.open()){
            tareas = con.createQuery(sqlQuery).executeAndFetch(TareaEntity.class);
        } catch (Exception e) {
            // Conexion a sql ha fallado
            throw new RuntimeException(e);
        }
        return tareas;
    }

    @Override
    public TareaEntity findById(Long id_tarea) {
        String sqlQuery = "SELECT * FROM tarea WHERE id_tarea = :id_tarea";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_tarea", id_tarea)
                    .executeAndFetchFirst(TareaEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public List<TareaEntity> findByIdEmergencia(Long id_emergencia) {
        List<TareaEntity> tareas = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tarea WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()){
            tareas = con.createQuery(sqlQuery).addParameter("id_emergencia", id_emergencia).executeAndFetch(TareaEntity.class);
        } catch (Exception e) {
            // Conexion a sql ha fallado
            throw new RuntimeException(e);
        }
        return tareas;
    }

    @Override
    public List<TareaEntity> findByRegion(int region) {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT t.* FROM tarea t INNER JOIN emergencia e ON t.id_emergencia = e.id_emergencia WHERE e.region = :region";
            return connection.createQuery(query)
                    .addParameter("region", region)
                    .executeAndFetch(TareaEntity.class);
        }
    }

    @Override
    public void update(TareaEntity tarea) {
        String sqlQuery = "UPDATE tarea SET asunto_tarea = :asuntoTarea, id_emergencia = :idEmergencia, estado_tarea = :estadoTarea, latitud = :latitud, longitud = :longitud, geom = ST_GeomFromText(:point, 32719) WHERE id_tarea = :idTarea";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            con.createQuery(sqlQuery)
                    .addParameter("asuntoTarea", tarea.getAsunto_tarea())
                    .addParameter("idEmergencia", tarea.getId_emergencia())
                    .addParameter("estadoTarea", tarea.getEstado_tarea())
                    .addParameter("idTarea", tarea.getId_tarea())
                    .addParameter("latitud", tarea.getLatitud())
                    .addParameter("longitud", tarea.getLongitud())
                    .addParameter("point", "POINT(" + tarea.getLongitud() + " " + tarea.getLatitud() + ")")
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void delete(Long id_tarea) {
        String sqlQuery = "DELETE FROM tarea WHERE id_tarea = :id_tarea";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            con.createQuery(sqlQuery)
                    .addParameter("id_tarea", id_tarea)
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

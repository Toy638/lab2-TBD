package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.TareaEntity;
import com.tbd.lab1.entities.Tarea_HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Tarea_HabilidadRepositoryImpl implements Tarea_HabilidadRepository{
    @Autowired
    private Sql2o sql2o;
    @Override
    public List<Tarea_HabilidadEntity> findAll() {
        List<Tarea_HabilidadEntity> tareahabilidades = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tarea_habilidad ORDER BY id_tarea_habilidad ASC";
        try (Connection con = sql2o.open()) {
            tareahabilidades = con.createQuery(sqlQuery).executeAndFetch(Tarea_HabilidadEntity.class);
        } catch (Exception e) {
            // Conexion a sql ha fallado
        }
        return tareahabilidades;
    }

    @Override
    public void create(Tarea_HabilidadEntity tarea_habilidad) {
        String sqlQuery = "INSERT INTO tarea_habilidad(id_tarea_habilidad, id_tarea, id_habilidad) VALUES (:idTareaHabilidad, :idTarea, :idHabilidad)";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idTareaHabilidad", tarea_habilidad.getId_tarea_habilidad())
                    .addParameter("idTarea", tarea_habilidad.getId_tarea())
                    .addParameter("idHabilidad", tarea_habilidad.getId_habilidad())
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
    public Tarea_HabilidadEntity findById(Long id_tarea_habilidad) {
        String sqlQuery = "SELECT * FROM tarea_habilidad WHERE id_tarea_habilidad = :id_tarea_habilidad";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_tarea_habilidad", id_tarea_habilidad)
                    .executeAndFetchFirst(Tarea_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public List<Tarea_HabilidadEntity> findByIdTarea(Long idTarea) {
        List<Tarea_HabilidadEntity> tarea_habilidades = null;
        String sqlQuery = "SELECT * FROM tarea_habilidad WHERE id_tarea = :id";
        try (Connection con = sql2o.open()) {
            tarea_habilidades = con.createQuery(sqlQuery).addParameter("id", idTarea).executeAndFetch(Tarea_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return tarea_habilidades;
    }

    @Override
    public List<Tarea_HabilidadEntity> findByIdHabilidad(Long idHabilidad) {
        List<Tarea_HabilidadEntity> tarea_habilidades = null;
        String sqlQuery = "SELECT * FROM tarea_habilidad WHERE id_habilidad = :id";
        try (Connection con = sql2o.open()) {
            tarea_habilidades = con.createQuery(sqlQuery).addParameter("id", idHabilidad).executeAndFetch(Tarea_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return tarea_habilidades;
    }

    @Override
    public void update(Tarea_HabilidadEntity tarea_habilidad) {
        String sqlQuery = "UPDATE tarea_habilidad SET id_tarea = :idTarea, id_habilidad = :idHabilidad WHERE id_tarea_habilidad = :idTareaHabilidad";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idTarea", tarea_habilidad.getId_tarea())
                    .addParameter("idHabilidad", tarea_habilidad.getId_habilidad())
                    .addParameter("idTareaHabilidad", tarea_habilidad.getId_tarea_habilidad())
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM tarea_habilidad WHERE id_tarea_habilidad = :id";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery).addParameter("id", id).executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

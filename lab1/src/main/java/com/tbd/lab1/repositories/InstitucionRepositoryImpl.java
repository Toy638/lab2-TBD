package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.HabilidadEntity;
import com.tbd.lab1.entities.InstitucionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImpl implements  InstitucionRepository{
    @Autowired
    private Sql2o sql2o;
    @Override
    public List<InstitucionEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM institucion";
            return connection.createQuery(query).executeAndFetch(InstitucionEntity.class);
        }
    }

    @Override
    public InstitucionEntity findById(Long id_institucion) {
        String sqlQuery = "SELECT * FROM institucion WHERE id_institucion = :id_institucion";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_institucion", id_institucion)
                    .executeAndFetchFirst(InstitucionEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public void create(InstitucionEntity institucion) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "INSERT INTO institucion (id_institucion, nombre, direccion, telefono, id_usuario) VALUES (:idInstitucion, :nombre, :direccion, :telefono, :id_usuario)";
            connection.createQuery(query)
                    .addParameter("idInstitucion", institucion.getIdInstitucion())
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("telefono", institucion.getTelefono())
                    .addParameter("id_usuario",institucion.getId_usuario())
                    .executeUpdate();
            connection.commit();
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
    public void update(InstitucionEntity institucion) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "UPDATE institucion SET nombre = :nombre, direccion = :direccion, telefono = :telefono WHERE id_institucion = :idInstitucion";
            connection.createQuery(query)
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("telefono", institucion.getTelefono())
                    .addParameter("idInstitucion", institucion.getIdInstitucion())
                    .executeUpdate();
            connection.commit();
        }
    }
    /*
    // Función update sin seguridad
    public InstitucionEntity update(InstitucionEntity institucion) {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE institucion SET nombre = :nombre, fecha = :fecha, direccion = :direccion, telefono = :telefono, id_usuario = :id_usuario WHERE id_institucion = :id")
                    .addParameter("id", institucion.getIdInstitucion())
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("fecha", institucion.getFecha())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("telefono", institucion.getTelefono())
                    .addParameter("id_usuario", institucion.getId_usuario())
                    .executeUpdate();
            return institucion;
        }
    }
     */


    @Override
    public void delete(Long id_institucion) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "DELETE FROM institucion WHERE id_institucion = :id_institucion";
            connection.createQuery(query)
                    .addParameter("id_institucion", id_institucion)
                    .executeUpdate();
            connection.commit();
        }
    }

    // Función delete sin seguridad
    /*
    public boolean deleteInstitucionById(Long id) {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM institucion WHERE id_institucion = :id")
                    .addParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
     */
}

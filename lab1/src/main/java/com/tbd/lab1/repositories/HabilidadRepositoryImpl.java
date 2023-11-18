package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class HabilidadRepositoryImpl implements HabilidadRepository{

    @Autowired
    private Sql2o sql2o;


    @Override
    public List<HabilidadEntity> getAllHabilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from habilidad")
                    .executeAndFetch(HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public HabilidadEntity getHabilidadById(long id_habilidad) {
        try (Connection conn = sql2o.open()) {
            List<HabilidadEntity> entities = conn.createQuery("select * from habilidad where id_habilidad = :id_habilidad")
                    .addParameter("id_habilidad", id_habilidad)
                    .executeAndFetch(HabilidadEntity.class);

            if (entities.size() > 0) {
                return entities.get(0); // Return the first entity from the list
            } else {
                return null; // Return null if no matching entity is found
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



    @Override
    public void createHabilidad(HabilidadEntity habilidad) {
        String sqlQuery = "INSERT INTO habilidad (habilidad) VALUES (:habilidad)";
        try (Connection con = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            con.createQuery(sqlQuery)
                    .addParameter("habilidad", habilidad.getHabilidad())
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            // Conexion a sql ha fallado
            throw new RuntimeException(e);
        }
    }


    // Función create sin seguridad
    /*
    public HabilidadEntity create(HabilidadEntity habilidad) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO habilidad (habilidad) values (:habilidad)", true)
                    .addParameter("habilidad", habilidad.getHabilidad())
                    .executeUpdate().getKey();
            return habilidad;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
     */


    @Override
    public void updateHabilidad(HabilidadEntity habilidad) {
        String sqlQuery = "UPDATE habilidad SET habilidad = :habilidad WHERE id_habilidad = :idHabilidad";
        try (Connection con = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idHabilidad", habilidad.getId_habilidad())
                    .addParameter("habilidad", habilidad.getHabilidad())
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            // Conexion a sql ha fallado//
            throw new RuntimeException(e);
        }
    }


    // Función update sin seguridad
    /*
    public HabilidadEntity update(HabilidadEntity habilidad) {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE habilidad SET habilidad = :habilidad WHERE id_habilidad = :id")
                    .addParameter("id", habilidad.getId_habilidad())
                    .addParameter("habilidad", habilidad.getHabilidad())
                    .executeUpdate();
            return habilidad;
        }
    }
     */



    @Override
    public void deleteHabilidadById(long id_habilidad) {
        String sqlQuery = "DELETE FROM habilidad WHERE id_habilidad = :id_habilidad";
        try (Connection con = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("id_habilidad", id_habilidad)
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            // Conexion a sql ha fallado
            throw new RuntimeException(e);
        }
    }

    // Función delete sin seguridad
    /*
    public boolean deleteHabilidadById(Long id) {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM habilidad WHERE id_habilidad = :id")
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

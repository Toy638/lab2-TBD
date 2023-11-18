package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.Vol_HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.ArrayList;
import java.util.List;


@Repository
public class Vol_HabilidadRepositoryImpl implements Vol_HabilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Vol_HabilidadEntity> findAll() {
        List<Vol_HabilidadEntity> vol_habilidades = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vol_habilidad ORDER BY id_vol_habilidad ASC";
        try (Connection con = sql2o.open()) {

            vol_habilidades = con.createQuery(sqlQuery).executeAndFetch(Vol_HabilidadEntity.class);
        } catch (Exception e) {
            // Conexion a sql ha fallado
        }
        return vol_habilidades;
    }

    @Override
    public void create(Vol_HabilidadEntity volHabilidad) {
        String sqlQuery = "INSERT INTO vol_habilidad (id_vol_habilidad, id_voluntario, id_habilidad) VALUES (:idVolHabilidad, :idVoluntario, :idHabilidad)";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idVolHabilidad", volHabilidad.getId_vol_habilidad())
                    .addParameter("idVoluntario", volHabilidad.getId_habilidad())
                    .addParameter("idHabilidad", volHabilidad.getId_voluntario())
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
    public Vol_HabilidadEntity findById(Long id_vol_habilidad) {
        String sqlQuery = "SELECT * FROM vol_habilidad WHERE id_vol_habilidad = :id_vol_habilidad";
        try (Connection con = sql2o.open()) {

            return con.createQuery(sqlQuery)
                    .addParameter("id_vol_habilidad", id_vol_habilidad)
                    .executeAndFetchFirst(Vol_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public List<Vol_HabilidadEntity> findByIdVoluntario(Long idVoluntario) {
        List<Vol_HabilidadEntity> vol_habilidades = null;
        String sqlQuery = "SELECT * FROM vol_habilidad WHERE id_voluntario = :id";
        try (Connection con = sql2o.open()) {
            vol_habilidades = con.createQuery(sqlQuery).addParameter("id", idVoluntario).executeAndFetch(Vol_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return vol_habilidades;
    }

    @Override
    public List<Vol_HabilidadEntity> findByIdHabilidad(Long idHabilidad) {
        List<Vol_HabilidadEntity> vol_habilidades = null;
        String sqlQuery = "SELECT * FROM vol_habilidad WHERE id_habilidad = :id";
        try (Connection con = sql2o.open()) {

            vol_habilidades = con.createQuery(sqlQuery).addParameter("id", idHabilidad).executeAndFetch(Vol_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return vol_habilidades;
    }

    @Override
    public void update(Vol_HabilidadEntity volHabilidad) {
        String sqlQuery = "UPDATE vol_habilidad SET id_voluntario = :idVoluntario, id_habilidad = :idHabilidad WHERE id_vol_habilidad = :idVolHabilidad";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idVoluntario", volHabilidad.getId_voluntario())
                    .addParameter("idHabilidad", volHabilidad.getId_habilidad())
                    .addParameter("idVolHabilidad", volHabilidad.getId_vol_habilidad())
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM vol_habilidad WHERE id_vol_habilidad = :id";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("id", id)
                    .executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

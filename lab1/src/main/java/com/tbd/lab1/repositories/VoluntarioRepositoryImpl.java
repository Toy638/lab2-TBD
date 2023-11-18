package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.VoluntarioEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class VoluntarioRepositoryImpl implements VoluntarioRepository{
    private final Sql2o sql2o;

    public VoluntarioRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<VoluntarioEntity> findAll() {
        try (Connection connection = sql2o.open()) {

            String query = "SELECT * FROM voluntario";
            return connection.createQuery(query).executeAndFetch(VoluntarioEntity.class);
        }
    }

    @Override
    public VoluntarioEntity findById(Long id_voluntario) {
        try (Connection connection = sql2o.open()) {

            String query = "SELECT * FROM voluntario WHERE id_voluntario = :id_voluntario";
            return connection.createQuery(query)
                    .addParameter("id_voluntario", id_voluntario)
                    .executeAndFetchFirst(VoluntarioEntity.class);
        }
    }

    /*@Override
    public List<VoluntarioEntity> findByEmergenciaAndRadio(Long idEmergencia, Double radio)
    {
        String query =
                "SELECT DISTINCT v.* " +
                        "FROM voluntario v " +
                        "JOIN vol_habilidad vh ON v.id = vh.id_voluntario " +
                        "JOIN eme_habilidad eh ON vh.id_habilidad = eh.id_habilidad " +
                        "JOIN emergencia e ON eh.id_emergencia = e.id " +
                        "WHERE e.id = :idEmergencia AND ST_DWithin(v.ubicacion::geography, e.ubicacion::geography, :radio)";

        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                    .addParameter("idEmergencia", idEmergencia)
                    .addParameter("radio", radio)
                    .executeAndFetch(VoluntarioEntity.class);
        }
    }*/

    @Override
    public void create(VoluntarioEntity voluntario) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "INSERT INTO voluntario (id_voluntario, nombre, apellido, telefono, direccion, id_usuario) VALUES (:id_voluntario, :nombre, :apellido, :telefono, :direccion, :id_usuario)";
            connection.createQuery(query)
                    .addParameter("id_voluntario", voluntario.getId_voluntario())
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("telefono", voluntario.getTelefono())
                    .addParameter("direccion", voluntario.getDireccion())
                    .addParameter("id_usuario", voluntario.getId_usuario())
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
    public void update(VoluntarioEntity voluntario) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "UPDATE voluntario SET nombre = :nombre, apellido = :apellido, telefono = :telefono, direccion = :direccion,  id_usuario = :id_usuario WHERE id_voluntario = :id_voluntario";
            connection.createQuery(query)
                    .addParameter("id_voluntario", voluntario.getId_voluntario())
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("telefono", voluntario.getTelefono())
                    .addParameter("direccion", voluntario.getDireccion())
                    .addParameter("id_usuario", voluntario.getId_usuario())
                    .executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(Long id_voluntario) {
        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "DELETE FROM voluntario WHERE id_voluntario = :id_voluntario";
            connection.createQuery(query)
                    .addParameter("id_voluntario", id_voluntario)
                    .executeUpdate();
            connection.commit();
        }
    }
}

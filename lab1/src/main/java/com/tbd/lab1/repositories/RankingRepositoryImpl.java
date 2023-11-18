package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.RankingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.ArrayList;
import java.util.List;


@Repository
public class RankingRepositoryImpl implements RankingRepository{
    @Autowired
    private Sql2o sql2o;
    @Override
    public List<RankingEntity> findAll() {
        List<RankingEntity> rankings = new ArrayList<>();
        String sqlQuery = "SELECT * FROM ranking ORDER BY id_ranking ASC";
        try (Connection con = sql2o.open()) {
            rankings = con.createQuery(sqlQuery).executeAndFetch(RankingEntity.class);
        } catch (Exception e) {
            // Conexion a sql ha fallado

        }
        return rankings;
    }

    @Override
    public List<RankingEntity> findByIdTarea(Long id_tarea) {
        List<RankingEntity> rankingList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM ranking WHERE id_tarea = :id_tarea";
        try (Connection con = sql2o.open()) {
            rankingList = con.createQuery(sqlQuery).addParameter("id_tarea", id_tarea).executeAndFetch(RankingEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return rankingList;
    }



    @Override
    public void create(RankingEntity ranking) {
        String sqlQuery = "INSERT INTO ranking (id_ranking, id_tarea, id_voluntario, puntaje) VALUES (:idRanking, :idTarea, :idVoluntario, :puntaje)";
        try (Connection con = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idRanking", ranking.getId_ranking())
                    .addParameter("idTarea", ranking.getId_tarea())
                    .addParameter("idVoluntario", ranking.getId_voluntario())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .executeUpdate();
            con.commit();
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
    public void delete(Long id_ranking) {
        String sqlQuery = "DELETE FROM ranking WHERE id_ranking = :id_ranking";
        try (Connection con = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("id_ranking", id_ranking)
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RankingEntity ranking) {
        String sqlQuery = "UPDATE ranking SET id_tarea = :idTarea, id_voluntario = :idVoluntario, puntaje = :puntaje WHERE id_ranking = :idRanking";
        try (Connection con = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            con.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            con.createQuery(sqlQuery)
                    .addParameter("idRanking", ranking.getId_ranking())
                    .addParameter("idTarea", ranking.getId_tarea())
                    .addParameter("idVoluntario", ranking.getId_voluntario())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .executeUpdate();
            con.commit();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public RankingEntity findById(Long id_ranking) {
        String sqlQuery = "SELECT * FROM ranking WHERE id_ranking = :id_ranking";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_ranking", id_ranking)
                    .executeAndFetchFirst(RankingEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}

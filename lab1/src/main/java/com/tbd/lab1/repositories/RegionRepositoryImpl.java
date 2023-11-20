package com.tbd.lab1.repositories;


import com.tbd.lab1.entities.RegionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RegionRepositoryImpl implements RegionRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<RegionEntity> findAll() {
        try(Connection connection = sql2o.open()){
            String query = "SELECT * FROM region";
            return connection.createQuery(query).executeAndFetch(RegionEntity.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public RegionEntity findById(Long id) {

        String sqlQuery = "SELECT * FROM region WHERE id = :id";

        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery)
                    .addParameter("id", id)
                    .executeAndFetchFirst(RegionEntity.class);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }
    // /region/{id}/tareas
    @Override
    public RegionEntity create(RegionEntity region) {
        try (Connection connection = sql2o.beginTransaction()) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // ST_GeomFromText(:geom, 4326)
            String query = "INSERT INTO region (id, nombre, geom) VALUES (:id, :nombre, :geom)";

            // Verificar si la geometría no es nula antes de ejecutar la consulta
            if (region.getGeom() != null) {
                connection.createQuery(query)
                        .addParameter("id", region.getId_region())
                        .addParameter("nombre", region.getName())
                        .addParameter("geom", region.getGeom())
                        .executeUpdate();
                connection.commit();
                return region;
            } else {
                // Manejar el caso en que la geometría es nula
                System.out.println("La geometría es nula");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



    @Override
    public void update(RegionEntity region) {
        try(Connection connection = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String query = "UPDATE region SET name = :name WHERE id = :id";
            connection.createQuery(query)
                    .addParameter("id", region.getId_region())
                    .addParameter("nombre", region.getName())
                    .executeUpdate();
            connection.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void deleteById(Long id) {

        try (Connection connection = sql2o.beginTransaction()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String query = "DELETE FROM region WHERE id = :id";

            connection.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
            connection.commit();
        }

    }

}

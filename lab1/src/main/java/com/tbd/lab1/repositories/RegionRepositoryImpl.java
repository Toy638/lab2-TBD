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

        String sqlQuery = "SELECT * FROM region WHERE id_region = :id_region";
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery)
                    .addParameter("id_region", id)
                    .executeAndFetchFirst(RegionEntity.class);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public RegionEntity create(RegionEntity region) {
        try(Connection connection = sql2o.beginTransaction()){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "INSERT INTO region (id_region, name) VALUES (:id_region, :name)";
            connection.createQuery(query)
                    .addParameter("id_region", region.getId_region())
                    .addParameter("name", region.getName())
                    .executeUpdate();
            connection.commit();
            return region;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void update(RegionEntity region) {
        try(Connection connection = sql2o.beginTransaction()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();


            String query = "UPDATE region SET name = :name WHERE id_region = :id_region";
            connection.createQuery(query)
                    .addParameter("id_region", region.getId_region())
                    .addParameter("name", region.getName())
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
            String username = authentication.getName();
            String sqlSet = "SELECT set_tbd_usuario(:username)";
            connection.createQuery(sqlSet)
                    .addParameter("username", username)
                    .executeScalar();

            String query = "DELETE FROM region WHERE id_region = :id_region";

            connection.createQuery(query)
                    .addParameter("id_region", id)
                    .executeUpdate();
            connection.commit();
        }

    }

}

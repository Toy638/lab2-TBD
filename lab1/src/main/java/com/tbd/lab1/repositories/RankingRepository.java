package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.RankingEntity;
import java.util.List;

public interface RankingRepository {

    List<RankingEntity> findAll();
    void create(RankingEntity habilidad);
    RankingEntity findById(Long id);
    void update(RankingEntity habilidad);
    void delete(Long id);
    List<RankingEntity> findByIdTarea(Long id);
}

package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.RegionEntity;

import java.util.List;

public interface RegionRepository {

    public List<RegionEntity> findAll();
    public RegionEntity findById(Long id);

    public RegionEntity create(RegionEntity region);
    public void update(RegionEntity region);
    public void deleteById(Long id);




}

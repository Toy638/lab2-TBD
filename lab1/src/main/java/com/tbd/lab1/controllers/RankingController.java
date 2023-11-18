package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.RankingEntity;
import com.tbd.lab1.repositories.RankingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("api/ranking")
public class RankingController{

    @Autowired
    private RankingRepositoryImpl rankingRepository;

    @GetMapping
    public ResponseEntity<List<RankingEntity>> findAll(){
        List<RankingEntity> ranking = rankingRepository.findAll();
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @GetMapping("/{idRanking}")
    public ResponseEntity<RankingEntity> findById(@PathVariable Long idRanking){
        RankingEntity ranking = rankingRepository.findById(idRanking);
        if(ranking != null){
            return new ResponseEntity<>(ranking, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(ranking, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RankingEntity ranking){
        rankingRepository.create(ranking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idRanking}")
    public ResponseEntity<Void> update(@PathVariable Long idRanking, @RequestBody RankingEntity ranking){
        ranking.setId_ranking(idRanking);
        rankingRepository.update(ranking);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idRanking}")
    public ResponseEntity<Void> delete(@PathVariable Long idRanking){
        rankingRepository.delete(idRanking);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

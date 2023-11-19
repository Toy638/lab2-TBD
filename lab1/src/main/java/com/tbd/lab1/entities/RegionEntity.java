package com.tbd.lab1.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tbd.lab1.serializers.PGgeometryDeserializer;
import com.tbd.lab1.serializers.PGgeometrySerializer;
import net.postgis.jdbc.PGgeometry;

public class RegionEntity {

    private Long id;
    private String nombre;

    @JsonSerialize(using = PGgeometrySerializer.class)
    //@JsonDeserialize(using = PGgeometryDeserializer.class)
    private PGgeometry geom;



    public Long getId_region() {
        return id;
    }

    public void setId_region(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }


    public PGgeometry getGeom() {
        return geom;
    }

    public void setGeom(PGgeometry geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", geom=" + geom +
                '}';
    }
    public String geomtoString(){
        return ""+geom+"";
    }
}

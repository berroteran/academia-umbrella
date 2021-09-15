package com.berroteran.academia.repository;

import com.berroteran.academia.entity.Bodega;

import java.util.Optional;


public class BodegaRepository implements Repository <Bodega> {


    public BodegaRepository(){
        super(Bodega.class,"spard","bodega");
    }

    @Override
    public Object findById(String key, String value) {
        return search(key,value);
    }
    @Override
    public Object findById(String key, Integer value) {
        return search(key,value);
    }

    public Optional<Bodega> findById(Bodega b) {
        return null;
    }
}

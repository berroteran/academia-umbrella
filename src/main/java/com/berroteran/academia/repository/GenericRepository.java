package com.berroteran.academia.repository;

import java.util.Optional;

public interface GenericRepository<T, ID>{

    T create(T t);

    T find(T t);

    T update(T t);


    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

}


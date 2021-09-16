package com.berroteran.academia.repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;



public class GenericRepositoryImpl<T, ID> implements GenericRepository<T,ID>{

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    //private final JpaEntityInformation<T, ?> entityInformation;
    //private final EntityManager em;
    //private final PersistenceProvider provider;

    //private @NullableCrudMethodMetadata metadata;
   // private EscapeCharacter escapeCharacter = EscapeCharacter.DEFAULT;

    protected EntityManager entityManager;
    private Class<T> type;



    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("mislibros");
        EntityManager em=emf.createEntityManager();
        this.entityManager = em;
    }

    public GenericRepositoryImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T create(final T t) {
        entityManager.persist(t);
        return t;
    }


    public void delete(final Object objeto) {
        entityManager.remove(entityManager.merge(objeto));
    }

    public T find(final Object id) {
        return (T) entityManager.find(type, id);
    }

    public T update(final T t) {
        return entityManager.merge(t);
    }


    public Iterable<T> findAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Iterable<T> findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public Optional<T> findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public Iterable saveAll(Iterable entities) {
        return null;
    }

    @Override
    public T save(Object entity) {
        return null;
    }
}
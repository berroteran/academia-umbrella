package com.berroteran.academia.repository;

import com.berroteran.academia.ejbjmoordb.mongodb.repository.Repository;
import com.berroteran.academia.ejbjmoordb.pojos.RevisionHistory;
import com.berroteran.academia.ejbspard.provider.MongoClientProvider;
import com.mongodb.MongoClient;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class RevisionHistoryRepository extends Repository<RevisionHistory> {
    @EJB
    MongoClientProvider mongoClientProvider;
    public RevisionHistoryRepository() {
        super(RevisionHistory.class, "spard_history","revisionhistory");
    }

    @Override
    protected MongoClient getMongoClient() {
        return mongoClientProvider.getMongoClient();
    }

    @Override
    public Object findById(String key, String value) {
        return search(key, value);
    }

    @Override
    public Object findById(String key, Integer value) {
        return  search(key, value);
    }

}
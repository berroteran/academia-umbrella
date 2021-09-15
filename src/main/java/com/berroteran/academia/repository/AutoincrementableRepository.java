package com.berroteran.academia.repository;

import com.berroteran.academia.ejbjmoordb.pojos.Autoincrementable;
import com.berroteran.academia.ejbspard.provider.MongoClientProvider;
import com.mongodb.MongoClient;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class AutoincrementableRepository extends AbstractRepository<Autoincrementable> {

    @EJB
    MongoClientProvider mongoClientProvider;
    @Override
    protected MongoClient getMongoClient() {
        return mongoClientProvider.getMongoClient();
    }
    public AutoincrementableRepository(){
        super(Autoincrementable.class,"spardjsd","autoincrementable");
    }
    @Override
    public Object findById(String key, String value) {
        return search(key,value);
    }
    @Override
    public Object findById(String key, Integer value) {
        return search(key,value);
    }

}
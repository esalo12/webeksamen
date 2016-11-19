package com.example;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by E on 25.09.2016.
 */
public interface FotoRepository extends MongoRepository<Foto, String>{
    // Metoder for Querys og operasjoner mot MongoDB
    public List<Foto> findAll();
    public Foto save(Foto vare);
    public Foto findById(String id);
    public Long removeById(String id);
}

package com.example;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FotoRepository extends MongoRepository<Foto, String>{
    // Metoder for Querys og operasjoner mot MongoDB
    public List<Foto> findAll();
    public Foto save(Foto vare);
    public List<Foto> findAllByTittelStartsWith(String tittel);
    public Foto findById(String id);
    public Long removeById(String id);
}

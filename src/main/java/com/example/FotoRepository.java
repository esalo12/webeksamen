package com.example;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FotoRepository extends MongoRepository<Foto, String>{
    // Metoder for Querys og operasjoner mot MongoDB
    public List<Foto> findAll();
    public Foto save(Foto foto);
    public List<Foto> findAllByTittelStartsWithIgnoreCaseOrTagsIgnoreCase(String tittel, String tag);
    public List<Foto> findAllByTagsStartsWithIgnoreCase(String tag);
    public Foto findById(String id);
    public List<Foto> findAllByFotografId(String id);
    public Long removeById(String id);
}

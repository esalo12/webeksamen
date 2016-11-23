package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FotografRepository extends MongoRepository<Fotograf, String> {
    // Metoder for Querys og operasjoner mot MongoDB
    public List<Fotograf> findAll();
    public Fotograf save(Fotograf fotograf);
    public Fotograf findByBrukernavn(String bruker);
    public Fotograf findById(String id);
    public Long removeById(String id);
}

package com.github.IliyaBarbarossa.repositories;

import com.github.IliyaBarbarossa.model.Excursion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExcursRep extends CrudRepository<Excursion,Integer> {
    public List<Excursion> findAll();
}

package com.example.backcoursework.repos;

import com.example.backcoursework.models.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends CrudRepository<Option, Integer> {

}

package com.sonu.collections.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sonu.collections.model.Publisher;

@Repository
public interface PublisherDao  extends CrudRepository<Publisher, Integer>{

}

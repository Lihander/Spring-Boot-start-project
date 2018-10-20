package com.example.hello.repository;

import com.example.hello.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}

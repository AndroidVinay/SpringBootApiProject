package com.assignment.project_two.project_two;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository  extends MongoRepository<Book,Integer> {
}

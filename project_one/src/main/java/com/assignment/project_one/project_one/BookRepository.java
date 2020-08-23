package com.assignment.project_one.project_one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Transactional
    @Modifying
    @Query("update Book b set b.authorName=:replaceWith where b.authorName Like :name%")
    public void updateAuthor(String name, String replaceWith);

    @Modifying
    @Transactional
    @Query(value = "Delete from book where cost>:cost",nativeQuery = true)
    public void deleteByCost(int cost);

}

package com.assignment.project_two.project_two;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }


    //This query take more than 20 seconds to execute
    @PostMapping(value = "insert")
    public void insertBook(@RequestBody List<Book> books) {
        bookRepository.saveAll(books);
    }


    //This help to execute query with less than 10 seconds
    @PostMapping(value = "insertWithThread")
    public ResponseEntity insertBookByThread(@RequestBody List<Book> books){
        InsertBookthread thread[] = new InsertBookthread[books.size()];
        for (int i = 0; i < books.size(); i++) {
            thread[i] = new InsertBookthread(books.get(i));
            try {
                thread[i].start();
            }catch (Exception e) {
                    ResponseEntity.badRequest().build();
            }

        }


        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Difference between threaded and non threaded api
     *
     * non theaded api take more time for execution.
     * and its alwasys good to run insert operation on multiple thread as it does not have ui intervantion.
     *
     */


    public class InsertBookthread extends Thread{
        Book book;

        InsertBookthread(Book book){
            this.book = book;
        }

        @Override
        public void run() {
            super.run();
            synchronized (bookRepository){
                bookRepository.save(book);
            }
        }
    }

}

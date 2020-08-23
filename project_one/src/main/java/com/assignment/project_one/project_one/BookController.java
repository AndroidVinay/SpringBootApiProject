package com.assignment.project_one.project_one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Value("${server.port}")
    int port;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/port")
    public String getPort() {
        return "Port used is " + port;
    }

    @GetMapping("/books")
    public List<Book> getAllbook() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
    public void insertBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @RequestMapping(value = "getBookById", method = RequestMethod.POST)
    public Book getBookById(@RequestParam(value = "id") int id) {
        Optional<Book> result = bookRepository.findById(Long.valueOf(id));
        return result.get();
    }

    @GetMapping("/getBookById/{id}")
    public Book getBookByPathId(@PathVariable int id) {
        Optional<Book> result = bookRepository.findById(Long.valueOf(id));
        return result.get();
    }

//    @PutMapping(value = "updateAuthor")
//    public void updateAuthor(@RequestBody ReplaceName replaceName) {
//        try {
//            bookRepository.updateAuthor(replaceName.getName(), replaceName.getReplaceWith());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }

    @PutMapping(value = "updateAuthor/{name}/{replace}")
    public void updateAuthor(@PathVariable String name, @PathVariable String replace) {
        try {
            bookRepository.updateAuthor(name, replace);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @DeleteMapping(value = "deleteByCost/{cost}")
    public void deleteByCost(@PathVariable int cost){
        bookRepository.deleteByCost(cost);
    }



}

package com.rest.restApi.seeders;

import javax.transaction.Transactional;

import com.rest.restApi.entities.Book;
import com.rest.restApi.entities.Role;
import com.rest.restApi.exceptions.BookAlreadyExistsException;
import com.rest.restApi.services.BookService;
import com.rest.restApi.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class BooksSeeder  implements ApplicationListener<ContextRefreshedEvent>{


    private boolean alreadysetup;

    @Autowired
    private BookService bookService;

    @Autowired
    private RoleService roleService;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
        if (alreadysetup) {
            return;
        }

		try {


            //insert Books
            bookService.saveBook(new Book(2L, "PLATO", "ZINON"));
            bookService.saveBook(new Book(1L, "ARISTO", "METAPHYSIC"));
            bookService.saveBook(new Book(3L, "ARISTO", "ORGANON"));
            bookService.saveBook(new Book(4L, "TALIS", "PHILOSOPHY"));


            //insert Role
            roleService.saveRole(new Role("ADMIN"));
            roleService.saveRole(new Role("WRITER"));
            roleService.saveRole(new Role("VIEWER"));
            roleService.saveRole(new Role("GUEST"));

        } catch (BookAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
}
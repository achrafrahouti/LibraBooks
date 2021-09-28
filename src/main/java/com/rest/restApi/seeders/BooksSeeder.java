package com.rest.restApi.seeders;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import com.rest.restApi.entities.Book;
import com.rest.restApi.entities.CustomUser;
import com.rest.restApi.entities.Offer;
import com.rest.restApi.entities.Role;
import com.rest.restApi.services.BookService;
import com.rest.restApi.services.OfferService;
import com.rest.restApi.services.RoleService;
import com.rest.restApi.services.UserService;

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

    @Autowired
    private UserService userService;

    @Autowired
    OfferService offerService;
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

            // insert a user 
            Set<Role> roles=Set.of(roleService.getRole(1l));
            CustomUser user=new CustomUser("firstName", "lastName", "email-achraf@email.com", "password", roles);
            userService.saveCustomUser(user);


            Set<Book> bto=Set.of(bookService.getBook(1L),bookService.getBook(2L));
            Date d=new Date(System.currentTimeMillis()+10000);
            offerService.save(new Offer("Taounate", d, user, bto));
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    
}
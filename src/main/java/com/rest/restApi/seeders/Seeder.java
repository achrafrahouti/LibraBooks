package com.rest.restApi.seeders;

import java.util.Set;

import javax.transaction.Transactional;

import com.rest.restApi.entities.Book;
import com.rest.restApi.entities.Category;
import com.rest.restApi.entities.CustomUser;
import com.rest.restApi.entities.Role;
import com.rest.restApi.services.BookService;
import com.rest.restApi.services.CategoryService;
import com.rest.restApi.services.RoleService;
import com.rest.restApi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadysetup;

    @Autowired
    private BookService bookService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadysetup) {
            return;
        }

        try {

            // insert Categories
            Category c1 = categoryService.saveCategory(new Category("Philosophy"));
            Category c2 = categoryService.saveCategory(new Category("Logic"));
            Category c3 = categoryService.saveCategory(new Category("Physics"));

            // insert Books
            bookService.saveBook(new Book(2L, "PLATO", "ZINON", c1));
            bookService.saveBook(new Book(1L, "ARISTO", "METAPHYSIC", c3));
            bookService.saveBook(new Book(3L, "ARISTO", "ORGANON", c2));
            bookService.saveBook(new Book(4L, "TALIS", "PHILOSOPHY", c1));

            // insert Role
            roleService.saveRole(new Role("ADMIN"));
            roleService.saveRole(new Role("WRITER"));
            roleService.saveRole(new Role("VIEWER"));
            roleService.saveRole(new Role("GUEST"));

            // insert a user
            Set<Role> roles = Set.of(roleService.getRole(1l));
            CustomUser user = new CustomUser("firstName", "lastName", "email-achraf@email.com", "password", roles);
            userService.saveCustomUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
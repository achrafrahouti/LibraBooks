package com.rest.restApi.entities;


import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.CreationTimestamp;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    
    @CreationTimestamp
    private Date created_at;

    @Future
    private Date finish_at;

    @ManyToOne
    @JoinColumn(name = "creator")
    private CustomUser user;

    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
        name = "book_offer",
        joinColumns = @JoinColumn(name="offer_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="book_id",referencedColumnName = "id"))
    private Set<Book> books;
    public Offer() {
    }


    public Offer(String address,  Date finish_at, CustomUser user, Set<Book> books) {
        this.address = address;
        this.finish_at = finish_at;
        this.user = user;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Date return the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * @return Date return the finish_at
     */
    public Date getFinish_at() {
        return finish_at;
    }

    /**
     * @param finish_at the finish_at to set
     */
    public void setFinish_at(Date finish_at) {
        this.finish_at = finish_at;
    }

    /**
     * @return CustomUser return the user
     */
    public CustomUser getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(CustomUser user) {
        this.user = user;
    }

    /**
     * @return Set<Book> return the books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Offer [address=" + address + ", books=" + books + ", created_at=" + created_at + ", finish_at="
                + finish_at + ", id=" + id + ", user=" + user + "]";
    }

}
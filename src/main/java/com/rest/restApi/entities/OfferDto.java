package com.rest.restApi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class OfferDto implements Serializable{

    private Long id;

    private String address;
    
    private Date created_at;

    private Date finish_at;

    private Long user;

    private Set<Long> books;
    

    public OfferDto() {
    }

    public OfferDto(Long id, String address, Date created_at, Date finish_at, Long user, Set<Long> books) {
        this.id = id;
        this.address = address;
        this.created_at = created_at;
        this.finish_at = finish_at;
        this.user = user;
        this.books = books;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
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
     * @return Long return the user
     */
    public Long getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * @return Set<Book> return the books
     */
    public Set<Long> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Long> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "OfferDto [address=" + address + ", books=" + books + ", created_at=" + created_at + ", finish_at="
                + finish_at + ", id=" + id + ", user=" + user + "]";
    }

}
package edu.illinois.cs427.mp4;

import java.util.LinkedList;
import java.util.List;

/**
 * This class contains the information needed to represent a book.
 */
public final class Book extends Element {
    private String title;
    private String author;

    /**
     * Builds a book with the given title and author.
     *
     * @param title the title of the book
     * @param author the author of the book
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Builds a book from the string representation, 
     * which contains the title and author of the book. 
     *
     * @param stringRepresentation the string representation
     */
    public Book(String stringRepresentation) {
        String[] parts = stringRepresentation.split(";");
        this.title = parts[0];
        this.author = parts[1];
    }

    /**
     * Returns the string representation of the given book.
     * The representation contains the title and author of the book.
     *
     * @return the string representation
     */
    public String getStringRepresentation() {
        return title + ";" + author;
    }

    /**
     * Returns all the collections that this book belongs to directly and indirectly.
     * Consider the following. 
     * (1) Computer Science is a collection. 
     * (2) Operating Systems is a collection under Computer Science. 
     * (3) The Linux Kernel is a book under Operating System collection. 
     * Then, getContainingCollections method for The Linux Kernel should return a list 
     * of these two collections (Operating Systems, Computer Science), starting from 
     * the direct collection to more indirect collections.
     *
     * @return the list of collections
     */
    public List<Collection> getContainingCollections() {
        List<Collection> parents = new LinkedList<Collection>();
        Collection parent = this.getParentCollection();
        while(parent != null){
            parents.add(parent);
            parent = parent.getParentCollection();
        }
        return parents;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
}

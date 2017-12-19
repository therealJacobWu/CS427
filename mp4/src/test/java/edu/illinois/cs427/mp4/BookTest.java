package edu.illinois.cs427.mp4;

import junit.framework.TestCase;

import java.util.List;

public class BookTest  extends TestCase {
    public void testBookConstructor1() {
        Book book = new Book("cs427", "jack");
        assertEquals("cs427", book.getTitle());
        assertEquals("jack", book.getAuthor());
    }

    public void testBookConstructor2() {
        Book book = new Book("cs427;jack");
        assertEquals("cs427", book.getTitle());
        assertEquals("jack", book.getAuthor());
    }

    public void testGetStringRepresentation1() {
        Book book = new Book("cs427", "jack");
        assertEquals("cs427;jack", book.getStringRepresentation());
    }

    public void testGetContainingCollections1() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        Book book2 = new Book("cs411", "mike");
        Collection sub = new Collection("subCollection");
        sub.addElement(book1);
        collection.addElement(book2);
        collection.addElement(sub);
        String s = collection.getStringRepresentation();
        List<Collection> parents = book1.getContainingCollections();
        assertEquals(2, parents.size());
        assertEquals("subCollection", parents.get(0).getName());
        assertEquals("testCollection", parents.get(1).getName());
    }
}

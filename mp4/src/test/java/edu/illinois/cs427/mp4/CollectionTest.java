package edu.illinois.cs427.mp4;

import junit.framework.TestCase;
import org.junit.Test;

public class CollectionTest  extends TestCase {
    public void testRestoreCollection1() {
        Collection collection = Collection.restoreCollection("[testCollection,<cs411;mike/[subCollection,<cs427;jack/>]/>]");
        assertEquals(2, collection.getElements().size());
        assertEquals("cs411", ((Book) collection.getElements().get(0)).getTitle());
        assertEquals("subCollection", ((Collection) collection.getElements().get(1)).getName());
        Collection sub_collection = (Collection) collection.getElements().get(1);
        assertEquals("jack", ( ((Book) sub_collection.getElements().get(0)).getAuthor()));

    }

    public void testGetStringRepresentation1() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        Book book2 = new Book("cs411", "mike");
        collection.addElement(book1);
        collection.addElement(book2);
        assertEquals("[testCollection,<cs427;jack/cs411;mike/>]", collection.getStringRepresentation());
    }

    public void testAddElement1() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        collection.addElement(book1);
        assertEquals(1, collection.getElements().size());
    }
    public void testAddElementFail() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        collection.addElement(book1);
        Collection collection2 = new Collection("testCollection2");
        assertTrue(!collection2.addElement(book1));
    }

    public void testDeleteElement1() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        collection.addElement(book1);
        assertEquals(1, collection.getElements().size());
        collection.deleteElement(book1);
        assertEquals(0, collection.getElements().size());
    }

    public void testDeleteElementFail() {
        Collection collection = new Collection("testCollection");
        Book book1 = new Book("cs427", "jack");
        assertTrue(!collection.deleteElement(book1));
    }
}

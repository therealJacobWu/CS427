package edu.illinois.cs427.mp4;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a collection of books or (sub)collections.
 */
public final class Collection extends Element {
    List<Element> elements;
    private String name;
    
    /**
     * Creates a new collection with the given name.
     *
     * @param name the name of the collection
     */
    public Collection(String name) {
        this.name = name;
        this.elements = new LinkedList<Element>();
    }

    /**
     * Restores a collection from its given string representation.
     *
     * @param stringRepresentation the string representation
     */
    public static Collection restoreCollection(String stringRepresentation) {
        String new_representation = stringRepresentation.substring(1,stringRepresentation.length()-1);
        Collection collection = new Collection(new_representation.split(",")[0]); // new_representation.split(",")[0] = NAME
        int idx = new_representation.indexOf(',')+2;
        new_representation = new_representation.substring(idx,new_representation.length()-1);
        while(new_representation.length() != 0) {
            if (new_representation.charAt(0) == '['){
                String parts = new_representation.split("]/", 2)[0];
                Collection sub_element = Collection.restoreCollection(parts+"]");
                collection.addElement(sub_element);
                new_representation = new_representation.split("]/", 2)[1];
            }else{
                String parts = new_representation.split("/", 2)[0];
                Book sub_element = new Book(parts);
                collection.addElement(sub_element);
                new_representation = new_representation.split("/", 2)[1];
            }
        }
        return collection;
    }


    /**
     * Returns the string representation of a collection. 
     * The string representation should have the name of this collection, 
     * and all elements (books/subcollections) contained in this collection.
     *
     * @return string representation of this collection
     */
    public String getStringRepresentation() {
        String StringRepresentation ="[" + this.name + ",<";
        for(int i=0; i<elements.size(); i++) {
            if (elements.get(i).getClass().getName() == "edu.illinois.cs427.mp4.Book") {
                StringRepresentation += ((Book) elements.get(i)).getStringRepresentation() + "/";
            }else{
                StringRepresentation += ((Collection) elements.get(i)).getStringRepresentation() + "/";
            }
        }
        StringRepresentation += ">]";
        return StringRepresentation;
    }

    /**
     * Returns the name of the collection.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Adds an element to the collection.
     * If parentCollection of given element is not null,
     * do not actually add, but just return false.
     * (explanation: if given element is already a part of  
     * another collection, you should have deleted the element 
     * from that collection before adding to another collection)
     *
     * @param element the element to add
     * @return true on success, false on fail
     */
    public boolean addElement(Element element) {
        if(element.getParentCollection() == null){
            elements.add(element);
            element.setParentCollection(this);
            return true;
        }
        return false;
    }
    
    /**
     * Deletes an element from the collection.
     * Returns false if the collection does not have 
     * this element.
     * Hint: Do not forget to clear parentCollection
     * of given element 
     *
     * @param element the element to remove
     * @return true on success, false on fail
     */
    public boolean deleteElement(Element element) {
        if(elements.remove(element)){
            element.setParentCollection(null);
            return true;
        }
        return false;
    }
    
    /**
     * Returns the list of elements.
     * 
     * @return the list of elements
     */
    public List<Element> getElements() {
        return this.elements;
    }
}

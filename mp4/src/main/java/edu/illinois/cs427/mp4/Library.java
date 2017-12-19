package edu.illinois.cs427.mp4;

import java.util.LinkedList;
import java.util.List;
import java.io.*;

/**
 * Container class for all the collections (that eventually contain books).
 * Its main responsibility is to save the collections to a file and restore them from a file.
 */
public final class Library {
    private List<Collection> collections;

    /**
     * Builds a new, empty library.
     */
    public Library() {
        this.collections = new LinkedList<Collection>();
    }

    /**
     * Builds a new library and restores its contents from the given file.
     *
     * @param fileName the file from where to restore the library.
     */
    public Library(String fileName) {
        this.collections = new LinkedList<Collection>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
               this.collections.add(Collection.restoreCollection(line));
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");
        }
    }

    /**
     * Saved the contents of the library to the given file.
         *
     * @param fileName the file where to save the library
     */
    public void saveLibraryToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for(Collection collection : collections){
                writer.println(collection.getStringRepresentation());
            }
            writer.close();
        }catch(IOException ex) {
            System.out.println(
                    "Error writing file '" + fileName + "'");
        }

    }

    /**
     * Returns the collections contained in the library.
     *
     * @return library contained elements
     */
    public List<Collection> getCollections() {
        return this.collections;
    }


}

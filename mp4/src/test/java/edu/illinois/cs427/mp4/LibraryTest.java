package edu.illinois.cs427.mp4;

import junit.framework.TestCase;

import java.io.*;
public class LibraryTest extends TestCase {
    public void testLibraryConstructorFromFile1() {
        Library library = new Library("src/test/resources/collections.txt");
        assertEquals(3, library.getCollections().size());

    }


    public void testLibraryConstructor() {
        Library library = new Library();
        assertEquals(0, library.getCollections().size());
    }

    public void testSaveLibraryToFile1() {
        Library library = new Library("src/test/resources/collections.txt");
        library.saveLibraryToFile("src/test/resources/saveTest.txt");
        String line1 = null;
        String line2 = null;
        Boolean identical = true;
        try {
            FileReader fileReader1 = new FileReader("src/test/resources/collections.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            FileReader fileReader2 = new FileReader("src/test/resources/saveTest.txt");
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            while((line1 = bufferedReader1.readLine()) != null && (line2 = bufferedReader2.readLine()) != null){
                if(! line1.equals(line2)){
                    identical = false;
                }
            }
            bufferedReader1.close();
            bufferedReader2.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        assertTrue(identical);
    }
}

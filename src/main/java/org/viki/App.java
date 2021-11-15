package org.viki;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println("Hello World!");
        UserInterface userInterface = new UserInterface();
        try {
            userInterface.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

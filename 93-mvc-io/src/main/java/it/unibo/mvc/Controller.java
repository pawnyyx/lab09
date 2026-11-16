package it.unibo.mvc;

/**
 * This interface represents a controller in the MVC pattern,
 * responsible for managing the flow of data between the model and the view.
 */
public interface Controller {

    /**
     * Adds one view to the controller, it is designed to handle one view only.
     * 
     * @param gui The view to add.
     */
    void addView(SimpleGUI gui); 

    /**
     * Prints a string on the std out stream.
     * 
     * @param line The line to print.
     */
    void print(String line); 

    /**
     * Terminates the gui safely. 
     */
    void quit(); 

    /**
     * Display's the view.
     */
    void display(); 
}

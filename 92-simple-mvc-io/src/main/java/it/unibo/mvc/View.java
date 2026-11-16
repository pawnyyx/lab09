package it.unibo.mvc;

/**
 * Questa interfaccia è necessaria per poter fare una lista di view in controller.java.
 * cosa che mi permette di gestire pu' view di diverso tipo (simpleGUI o SimpleGUIWithFileChooser)
 * con un solo "tipo" generico ovvero questo.
 */
@FunctionalInterface 
public interface View {

    /**
     * Displays the frame.
     */
    void display();

}

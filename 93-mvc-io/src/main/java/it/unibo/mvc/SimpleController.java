package it.unibo.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {

    private SimpleGUI view; 
    private Queue<String> printQueue = new LinkedList<>(); 

    @Override
    public void addView(SimpleGUI gui) {
        Objects.requireNonNull(gui, "View can't be null"); 
        this.view = gui;     
    }

    @Override
    public void printNextInLine() {
        Objects.requireNonNull(printQueue.poll())
        System.out.println(line);
    }

    @Override
    public void quit() {
        view.quit(); 
    }

    @Override
    public void display() {
        view.show(); 
    }

}

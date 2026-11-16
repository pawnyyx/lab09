package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {

    private SimpleGUI view;
    private String next;
    private final List<String> history = new LinkedList<>();

    /**
     * Constructor for the class {@link SimpleController}.
     */
    public SimpleController() {
        this.view = null; 
        //Does nothing on porp.
    }

    /**
     * Gest a hystory of the printed strings.
     * 
     * @return A List<String>, copyOf {@link history} is used to prevent EI_EXPOSED_REP
     */
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    /**
     * Getter for the next String.
     *
     * @return Returns the {@link next} string
     */
    public String getNextToPrint() {
        return next;
    }

    /**
     * Sets the {@link next} field.
     *
     * @param nextS String that will be set as next to print.
     */
    public void setNextToPrint(final String nextS) {
        Objects.requireNonNull(nextS);
        this.next = nextS;
    }

    @Override
    public void addView(final SimpleGUI gui) {
        Objects.requireNonNull(gui, "View can't be null");
        this.view = gui;
    }

    @Override
    public void printNext() {
        if (next == null) {
            throw new IllegalStateException();
        }
        System.getLogger("App").log(System.Logger.Level.INFO, next);
        history.addFirst(next);
        next = null;
    }

    @Override
    public void quit() {
        Objects.requireNonNull(view); 
        view.quit();
    }

    @Override
    public void display() {
        Objects.requireNonNull(view); 
        view.show();
    }

}

package it.unibo.mvc;

/**
 * App, utility class.
 *
 */
final class App {

    /**
     * App constructor.
     */
    private App() {
        //Does not do nothing
    }

    /**
     * Main class for this function.
     *
     * @param args Argument string.
     */
    public static void main(final String[] args) {
        final SimpleController controller = new SimpleController();
        controller.addView(new SimpleGUI(controller));
        controller.display();
    }
}

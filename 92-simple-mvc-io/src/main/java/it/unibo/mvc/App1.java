package it.unibo.mvc;

/**
 * App1.
 */
final class App1 {

    /**
     * Previene che questa utility class venga istanziata.
     */
    private App1() {

    }
    /**
     * SDSDASDSA.
     * 
     * @param args ASDASDA
     */

    public static void main(final String[] args) {
        final Controller controller = new Controller();
        final SimpleGUI view1 = new SimpleGUI(controller);
        final SimpleGUIWithFileChooser view2 = new SimpleGUIWithFileChooser(controller);
        controller.addView(view1);
        controller.addView(view2);
        controller.show();
    }
}

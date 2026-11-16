package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTIONS = 5;
    private static final String TITLE = "Simple gui";
    private JFrame frame;

    /**
     * Creates a new SimpleGUI.
     *
     * @param observer Controller for this gui
     */
    public SimpleGUI(final SimpleController observer) {
        initFrame();
        final JPanel canvas = new JPanel();
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        canvas.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        textArea.setText("Write here...");

        final JButton printButton = new JButton("Print");
        final JButton histButton = new JButton("Show history");

        /*
         * Handlers.
         */
        printButton.addActionListener((final ActionEvent e) -> {
            observer.setNextToPrint(textArea.getText());
            observer.printNext();
        });

        histButton.addActionListener((final ActionEvent e) -> {
            textArea.setText(observer.getHistory().toString());
        });

        canvas.add(textArea, BorderLayout.CENTER);
        southPanel.add(printButton, BorderLayout.WEST);
        southPanel.add(histButton, BorderLayout.EAST);
        canvas.add(southPanel, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
    }

    /**
     * Initalizes {@link frame}.
     */
    private void initFrame() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Sta roba serve solo per settare il look della gui, provando a usare quello di
        // sistema.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // Se ti chiedi perchè nel secondo campo di .log c'è () -> string vedi il logger
            // di Controller.java.
            System.getLogger("App").log(System.Logger.Level.INFO,
                    () -> """
                            An error accoured while setting the system LookAndFeel, using the default one.
                            Error message: """ + e.getMessage());
        }
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) (screen.getWidth() / PROPORTIONS), (int) (screen.getHeight() / PROPORTIONS));
        frame.setLocationRelativeTo(null);
    }

    /**
     * Disposes the components attached to the frame in the right way.
     */
    public void quit() {
        frame.dispose();
    }

    /**
     * Shows the frame.
     */
    public void show() {
        frame.setVisible(true);
    }
}

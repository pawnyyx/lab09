package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI implements View {

    private static final String TITLE = "GUI semplice";
    private static final int PROPORTIONS = 5;
    private final Controller controller;
    private JFrame frame;

    /**
     * Creates a new SimpleGUI.
     * 
     * @param observer Controller for this gui
     */
    public SimpleGUI(final Controller observer) {
        this.controller = observer; 
        initFrame();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        textArea.setText("Scrivi qui");
        final JButton saveButton = new JButton("Save");
        /*
         * Handlers.
         */
        saveButton.addActionListener((final ActionEvent e) -> {
            final String line = textArea.getText();
            if (!line.isBlank()) {
                controller.writeOnFile(line);
            } else {
                JOptionPane.showMessageDialog(frame, "Cannot write blank line on the file");
            }
        });

        canvas.add(saveButton, BorderLayout.SOUTH);
        canvas.add(textArea, BorderLayout.CENTER);
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

    @Override
    public void display() {
        // frame.pack();
        frame.setVisible(true);
    }

    /**
     * Quits the JSwing, should be called before closing the whole app.
     */
    public void quit() {
        frame.dispose();
    }

}

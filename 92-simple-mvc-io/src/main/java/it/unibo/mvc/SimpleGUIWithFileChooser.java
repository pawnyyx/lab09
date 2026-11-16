package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A very simple program using a graphical interface.
 *
 */
public class SimpleGUIWithFileChooser implements View{

    private static final int PROPORTIONS = 5;
    private JFrame frame;
    private Controller controller;

    /**
     * Creates a new SimpleGUI.
     */
    public SimpleGUIWithFileChooser() {
        initFrame();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        textArea.setText("Scrivi qui");
        final JButton saveButton = new JButton("Save");
        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
 
        final JTextField textField = new JTextField(controller.getPath());
        textField.setEditable(false);

        final JButton browseButton = new JButton("Browse..."); 
        
        /*
         *  Handlers. 
         */
        saveButton.addActionListener((final ActionEvent e) -> {
            final String line = textArea.getText();
            if (!line.isBlank()) {
                controller.writeOnFile(line);
            } else {
                JOptionPane.showMessageDialog(frame, "Cannot write blank line on the file");
            }
        });

        browseButton.addActionListener((final ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(); 
            final int result = fileChooser.showSaveDialog(frame);
            //Questo switch funziona come il classico con case <caseValue>: break;
            switch (result) {
                case JFileChooser.APPROVE_OPTION -> {
                    controller.setFpFile(fileChooser.getSelectedFile());
                    textField.setText(controller.getPath());
                }
                case JFileChooser.CANCEL_OPTION -> {
                    //Does nothing
                }
                default -> JOptionPane.showMessageDialog(fileChooser,
                        "An error occurred while chosing the file");
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
        //Sta roba serve solo per settare il look della gui, provando a usare quello di sistema. 
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            //Se ti chiedi perchè nel secondo campo di .log c'è () -> string vedi il logger di Controller.java.
            System.getLogger("App").log(System.Logger.Level.INFO,
                    () -> """
                  An error accoured while setting the system LookAndFeel, using the default one.
                  Error message: """ + e.getMessage());
        }
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) (screen.getWidth() / PROPORTIONS), (int) (screen.getHeight() / PROPORTIONS));
        frame.setLocationRelativeTo(null);
    }

    public void setController(final Controller observer) {
        System.out.println("in una view, ecco controller: " + controller);
        Objects.requireNonNull(observer, "Controller can't be null");
        this.controller = observer;
    }

    @Override 
    public void display() {
        //frame.pack();
        frame.setVisible(true);
    }

    /**
     * Quits the JSwing, should be called before closing the whole app.
     */
    public void quit() {
        frame.dispose();
    }

    /**
     * Main method for this class.
     *
     * @param args Args of the method.
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}

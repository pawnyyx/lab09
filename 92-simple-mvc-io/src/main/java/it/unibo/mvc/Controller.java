package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Application controller. Performs the I/O. NOTE: every time the fpFile changes
 * it must be also changed the String path.
 */
// Necessario per la variabile path, è effettivamente un singular field
// ma è richiesto dalla consegna
@SuppressWarnings("PMD.SingularField")
public final class Controller {
    private File fpFile;
    private String path;
    private final Set<View> views = new HashSet<>();

    /**
     * Creates a new Controller.
     */
    public Controller() {
        path = System.getProperty("user.home")
                + System.getProperty("file.separator")
                + Controller.class.getSimpleName() + ".txt";

        fpFile = new File(path);
    }

    /**
     * Sets up fpFile as parameter. It must update the String path field.
     *
     * @param fpFile File pointer to assing.
     */
    public void setFpFile(final File fpFile) {
        Objects.requireNonNull(fpFile, "File pointer can't be null");
        this.fpFile = fpFile;
        path = fpFile.getAbsolutePath();
    }

    /**
     * Returns the fpFile field.
     *
     * @return fpFile field.
     */
    public File getFpFile() {
        return fpFile;
    }

    /**
     * Returns the absolute path as a String of the current File fpFile.
     *
     * @return Absolute path of the fpFile as a String.
     */
    public String getPath() {
        Objects.requireNonNull(fpFile);
        return fpFile.getAbsolutePath();
    }

    /**
     * Appends a single String on the File fpFile.
     *
     * @param line The String to append.
     */
    public void writeOnFile(final String line) {
        Objects.requireNonNull(fpFile, "File pointer can't be null");
        try (PrintStream ps = new PrintStream(fpFile, StandardCharsets.UTF_8)) {
            ps.append(line);
        } catch (final IOException ex) {
            // L'uso di espressioni lambda posticipa la concatenazione del messaggio a
            // quando il logger sarà attivo.
            // Crea un warning altrimenti
            System.getLogger("App").log(System.Logger.Level.INFO,
                    () -> "An error occurred while writing on file: " + ex.getMessage());
        }
    }

    /**
     * Adds a view and sets hsi controller.
     * 
     * @param gui view to add.
     */
    public void addView(final View gui) {
        Objects.requireNonNull(gui);
        views.add(gui);
    }

    /**
     * bababab.
     */
    public void show() {
        for (final View view : views) {
            view.display();
        }
    }

}

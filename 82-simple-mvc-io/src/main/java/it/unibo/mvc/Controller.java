package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME_PATH = System.getProperty("user.home"); 
    private static final String DEFAULT_FILE = "output.txt";

    private File curr_file = new File(HOME_PATH + File.separator + DEFAULT_FILE);

    /* A Method for setting a File as current file */
    public void setCurrFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.curr_file = file;
        } else {
            throw new IllegalArgumentException("Non-existing folder");
        }
    }

    public void setCurrFile(final String file) {
        setCurrFile(new File(file));
    }

    /* Method for getting the current file */
    public File getCurrentFile() {
        return this.curr_file;
    }

    /* Method for getting the path of the current file */
    public String getPath() {
        return this.curr_file.getPath();
    }

    /* A method that gets a `String` as input and saves its content on the current file. */
    public void writeToFile(final String input) throws IOException {
        try (final PrintStream ps = new PrintStream(this.curr_file, StandardCharsets.UTF_8)) {
            ps.println(input);
        }
    }


}

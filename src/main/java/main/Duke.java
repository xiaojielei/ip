package main;

import parser.Parser;
import java.io.IOException;


public class Duke {
    private static final Parser parser = new Parser();

    /**
     * The main method that starts the DAuke application.
     * It loads tasks from a file, processes user commands, and saves tasks to a file on exit.
     * @throws IOException If an I/O error occurs during file operations.
     */
    public static void main(String[] args) throws IOException {
        parser.runDuke();
    }
}

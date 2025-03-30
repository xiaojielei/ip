package ui;

import java.util.Scanner;
import constants.Messages;

/**
 * deals with interactions with the user
 */
public class Ui {
    private final Scanner scanner;

    private static void printLine() {
        System.out.println(Messages.LINE_SEPARATOR);
    }

    public Ui(Scanner in) {
        this.scanner = in;
    }

    /**
     * Prints the greeting message
     */
    public void greet() {
        System.out.println("Hello! I'm main.Duke\n" + "What can I do for you?");
    }

    /**
     * Prints an exit message and terminates the application.
     */
    public void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Gets user's input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }
}

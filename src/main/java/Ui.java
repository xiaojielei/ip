public class Ui {
    private static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public Ui() {
    }

    public void Greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Prints an exit message and terminates the application.
     */
    public void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}

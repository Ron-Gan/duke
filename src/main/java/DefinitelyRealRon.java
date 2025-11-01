
import java.io.IOException;

import commands.Command;
import static common.Messages.ERROR_EMPTY_LIST;
import static common.Messages.ERROR_IO_INITIALISATION;
import static common.Messages.ERROR_OUT_OF_BOUNDS;
import parser.Parser;
import storage.Storage;
import storage.Storage.InvalidStoragePathException;
import task.TaskList;
import ui.Ui;

/**
 * Entry point of DefinitelyRealRon chatbot. Initializes the chatbot and starts
 * interaction with the user.
 */
public class DefinitelyRealRon {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for DefinitelyRealRon chatbot.
     * @param filePath path to the storage file
     */
    public DefinitelyRealRon(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    /**
     * Initialises the storage and tasklist and runs the loop.
     */
    public void run() {
        try {
            storage.initFile();
            tasks = storage.load();
        } catch (IOException e) {
            ui.showErrorMessage(ERROR_IO_INITIALISATION);
            System.exit(0);
        } catch (InvalidStoragePathException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(0);
        }
        ui.showWelcomeMessage();
        runLoopUntilBye();
        exit();
    }

    /**
     * Reads the user command and executes it, until "bye" is entered.
     */
    private void runLoopUntilBye() {
        while (true) {
            String input = ui.getInput();
            if (input.equals("bye")) {
                break;
            }
            Command c = new Parser().parse(input);
            try {
                c.execute(tasks, ui, storage);
            } catch (IndexOutOfBoundsException e) {
                if (tasks.size() < 1) {
                    ui.showErrorMessage(String.format(ERROR_EMPTY_LIST, tasks.size()));
                } else {
                    ui.showErrorMessage(String.format(ERROR_OUT_OF_BOUNDS, tasks.size()));
                }
            }

        }
    }

    /**
     * Prints the ByeMessage and exit.
     */
    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

    /**
     * Specify storage file when creating new chatbot.
     */
    public static void main(String[] args) {
        new DefinitelyRealRon("./data/data.txt").run();
    }
}

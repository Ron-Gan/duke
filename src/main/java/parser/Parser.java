package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ErrorCommand;
import commands.FindTaskCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SetPriorityCommand;
import commands.UnmarkCommand;
import static common.Messages.ERROR_EMPTY_DESC;
import static common.Messages.ERROR_INVALID_BY;
import static common.Messages.ERROR_INVALID_FROM_TO;
import static common.Messages.ERROR_INVALID_INTEGER;
import static common.Messages.ERROR_OUT_OF_BOUNDS;
import static common.Messages.ERROR_UNKNOWN_COMMAND;
import static common.Messages.ERROR_WRONG_DATE_FORMAT;
import common.Priority;

/**
 * Makes sense of the user's input.
 */
public class Parser {

    /**
     * Parses the user input into a Command.
     * @param input user input string
     * @return the parsed Command
     */
    public Command parse(String input) {

        String commandWord = input.split(" ")[0];
        String arguments = input.substring(commandWord.length()).strip();

        switch (commandWord) {
        case "todo":
            return (prepareAddTodo(arguments));
        case "deadline":
            return (prepareAddDeadline(arguments));
        case "event":
            return (prepareAddEvent(arguments));
        case "list":
            return (prepareList());
        case "mark":
            return (prepareMark(arguments));
        case "unmark":
            return (prepareUnmark(arguments));
        case "delete":
            return (prepareDelete(arguments));
        case "find":
            return (prepareFind(arguments));
        case "priority":
            return (preparePriority(arguments));
        default:
            return new ErrorCommand(String.format(ERROR_UNKNOWN_COMMAND, input));
        }
    }

    /**
     * Parses arguments in the context of the AddTodoCommand. Returns error if
     * arguments are blank.
     *
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddTodo(String arguments) {
        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        return new AddTodoCommand(arguments);
    }

    /**
     * Parses arguments in the context of the AddDeadlineCommand. Returns error
     * if arguments are blank. Returns error if /by is missing. Returns error if
     * deadline is not formatted in dd/MM/yyyy HHmm
     *
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddDeadline(String arguments) {
        if (!arguments.contains(" /by ")) {
            return new ErrorCommand(ERROR_INVALID_BY);
        }
        String[] result = arguments.split(" /by ");
        String description = result[0];
        if (description.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        String deadline = result[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return new AddDeadlineCommand(description, LocalDateTime.parse(deadline, formatter));
        } catch (DateTimeParseException dtpe) {
            return new ErrorCommand(ERROR_WRONG_DATE_FORMAT);
        }
    }

    /**
     * Parses arguments in the context of the AddEventCommand. Returns error if
     * arguments are blank. Returns error if /from or /to is missing. Returns
     * error if deadline is not formatted in dd/MM/yyyy HHmm
     *
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddEvent(String arguments) {
        // Remove any leading indexes or accidental numbers
        arguments = arguments.trim().replaceFirst("^\\d+\\s*", "");

        // Regex to capture: description, fromDate, toDate
        Pattern p = Pattern.compile("^(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*)$");
        Matcher m = p.matcher(arguments);

        if (!m.matches()) {
            return new ErrorCommand(ERROR_INVALID_FROM_TO);
        }

        String description = m.group(1).trim();
        String fromDate = m.group(2).trim();
        String toDate = m.group(3).trim();

        if (description.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return new AddEventCommand(
                    description,
                    LocalDateTime.parse(fromDate, formatter),
                    LocalDateTime.parse(toDate, formatter)
            );
        } catch (DateTimeParseException e) {
            return new ErrorCommand(ERROR_WRONG_DATE_FORMAT);
        }
    }

    private Command prepareList() {
        return new ListCommand();
    }

    /**
     * Parses arguments in the context of the MarkCommand. Returns error if
     * arguments are blank. Returns error if target index is out of bounds.
     *
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareMark(String arguments) {
        Integer index = -1;
        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try {
            index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new MarkCommand(index);
    }

    /**
     * Parses arguments in the context of the UnmarkCommand. Returns error if
     * arguments are blank. Returns error if target index is out of bounds.
     *
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareUnmark(String arguments) {
        Integer index = -1;
        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try {
            index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new UnmarkCommand(index);
    }

    /**
     * Parses arguments in the context of the DeleteCommand. Returns error if
     * arguments are blank. Returns error if target index is out of bounds.
     *
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareDelete(String arguments) {
        Integer index = -1;
        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try {
            index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new DeleteCommand(index);
    }

    /**
     * Parses arguments in the context of the FindTaskCommand. Returns error if
     * arguments are blank.
     *
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareFind(String arguments) {

        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }

        return new FindTaskCommand(arguments);
    }

    /**
     * Parses arguments in the context of the PriorityCommand. Returns error if
     * arguments are blank.
     *
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command preparePriority(String arguments) {
        Integer index = -1;
        Priority priority;
        if (arguments.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try {
            String indexString = arguments.split(" ")[0];
            priority = Priority.valueOf(arguments.split(" ")[1].toUpperCase());
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand(ERROR_OUT_OF_BOUNDS);
        }

        return new SetPriorityCommand(index, priority);
    }
}

package parser;
import static common.Messages.ERROR_EMPTY_DESC;
import static common.Messages.ERROR_INVALID_BY;
import static common.Messages.ERROR_INVALID_FROM_TO;
import static common.Messages.ERROR_INVALID_INTEGER;
import static common.Messages.ERROR_UNKNOWN_COMMAND;
import static common.Messages.ERROR_WRONG_DATE_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import commands.*;

/**
 * Makes sense of the user's input.
 */
public class Parser {
    public Command parse(String input){
        /** Search for the first word as the commandWord. */
        String commandWord = input.split(" ")[0];

        /** All other words other than the first will be the arguments. */
        String arguments = input.substring(commandWord.length()).strip();

        /** Read the input differently according to the commandWord. */
        switch(commandWord){
            case "todo":
                return(prepareAddTodo(arguments));
            case "deadline":
                return(prepareAddDeadline(arguments));
            case "event":
                return(prepareAddEvent(arguments));
            case "list":
                return(prepareList());
            case "mark":
                return (prepareMark(arguments));
            case "unmark":
                return (prepareUnmark(arguments));
            case "delete":
                return (prepareDelete(arguments));
            default:
                return new ErrorCommand(String.format(ERROR_UNKNOWN_COMMAND, input));
        }
    }

    /**
     * Parses arguments in the context of the AddTodoCommand.
     * Returns error if arguments are blank.
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddTodo(String arguments){
        if(arguments.isBlank()){
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        return new AddTodoCommand(arguments);
    }

    
    /**
     * Parses arguments in the context of the AddDeadlineCommand.
     * Returns error if arguments are blank.
     * Returns error if /by is missing.
     * Returns error if deadline is not formatted in dd/MM/yyyy HHmm
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddDeadline(String arguments){
        if(!arguments.contains(" /by ")){
            return new ErrorCommand(ERROR_INVALID_BY);
        }
        String result[] = arguments.split(" /by ");
        String description = result[0];
        if (description.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        String deadline = result[1];
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return new AddDeadlineCommand(description, LocalDateTime.parse(deadline, formatter));
        } catch(DateTimeParseException dtpe){
            return new ErrorCommand(ERROR_WRONG_DATE_FORMAT);
        }
    }
    
    /**
     * Parses arguments in the context of the AddEventCommand.
     * Returns error if arguments are blank.
     * Returns error if /from or /to is missing.
     * Returns error if deadline is not formatted in dd/MM/yyyy HHmm
     * @param arguments input except for first word
     * @return the prepared command
     */
    private Command prepareAddEvent(String arguments){
        boolean fromExist = (arguments.contains("/from "));
        boolean toExist = (arguments.contains("/to "));
        String description, fromDate, toDate;

        if(!fromExist || !toExist){
            String missing = !fromExist ? "/from" : "/to";
            return new ErrorCommand(String.format(ERROR_INVALID_FROM_TO, missing));
        }

        /** 
         * Determines if user input /from or /to first.
         * Runs the first way of parsing if /from is before /to.
         */
        if(arguments.indexOf("/from ")<arguments.indexOf("/to ")){
            fromDate = arguments.substring(arguments.indexOf("/from ")+6,arguments.indexOf("/to ")-1);
            toDate = arguments.substring(arguments.indexOf("/to ")+4);
            description = arguments.split(" /from ")[0];
        }

        /** Runs if the way of parsing is /to before /from. */
        else{
            toDate=arguments.substring(arguments.indexOf("/to ")+4,arguments.indexOf("/from ")-1);
            fromDate = arguments.substring(arguments.indexOf("/from ")+6);
            description = arguments.split(" /to ")[0];
        } 

        if (description.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return new AddEventCommand(description, LocalDateTime.parse(fromDate, formatter),LocalDateTime.parse(toDate, formatter));
        } catch(DateTimeParseException dtpe){
            return new ErrorCommand(ERROR_WRONG_DATE_FORMAT);
        }
    }

    private Command prepareList(){
        return new ListCommand();
    }

    /**
     * Parses arguments in the context of the MarkCommand.
     * Returns error if arguments are blank.
     * Returns error if target index is out of bounds.
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareMark(String arguments){
        Integer index=-1;
        if(arguments.isBlank()){
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try{
            index = Integer.parseInt(arguments)-1;
        } catch(NumberFormatException e){
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new MarkCommand(index);
    }

    /**
     * Parses arguments in the context of the UnmarkCommand.
     * Returns error if arguments are blank.
     * Returns error if target index is out of bounds.
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareUnmark(String arguments){
        Integer index = -1;
        if(arguments.isBlank()){
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try{
            index = Integer.parseInt(arguments)-1;
        } catch(NumberFormatException e){
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new UnmarkCommand(index);
    }    

    /**
     * Parses arguments in the context of the MarkCommand.
     * Returns error if arguments are blank.
     * Returns error if target index is out of bounds.
     * @param arguments input except for first word.
     * @return the prepared command.
     */
    private Command prepareDelete(String arguments){
        Integer index = -1;
        if(arguments.isBlank()){
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        try{
            index = Integer.parseInt(arguments)-1;
        } catch(NumberFormatException e){
            return new ErrorCommand(ERROR_INVALID_INTEGER);
        }
        return new DeleteCommand(index);
    }
}

package parser;
import static common.Messages.ERROR_EMPTY_DESC;
import static common.Messages.ERROR_INVALID_BY;
import static common.Messages.ERROR_INVALID_FROM_TO;
import static common.Messages.ERROR_INVALID_INTEGER;
import static common.Messages.ERROR_UNKNOWN_COMMAND;

import commands.*;

public class Parser {
    public Command parse(String input){
        String commandWord = input.split(" ")[0];
        String arguments = input.substring(commandWord.length()).strip();

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

    private Command prepareAddTodo(String arguments){
        if(arguments.isBlank()){
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        return new AddTodoCommand(arguments);
    }
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
        return new AddDeadlineCommand(description, deadline);
    }
    private Command prepareAddEvent(String arguments){
        boolean fromExist = (arguments.contains("/from "));
        boolean toExist = (arguments.contains("/to "));
        String description, fromDate, toDate;

        if(!fromExist || !toExist){
            String missing = !fromExist ? "/from" : "/to";
            return new ErrorCommand(String.format(ERROR_INVALID_FROM_TO, missing));
        }
        if(arguments.indexOf("/from ")<arguments.indexOf("/to ")){
            fromDate = arguments.substring(arguments.indexOf("/from ")+6,arguments.indexOf("/to ")-1);
            toDate = arguments.substring(arguments.indexOf("/to ")+4);
            description = arguments.split(" /from ")[0];
        }
        else{
            toDate=arguments.substring(arguments.indexOf("/to ")+4,arguments.indexOf("/from ")-1);
            fromDate = arguments.substring(arguments.indexOf("/from ")+6);
            description = arguments.split(" /to ")[0];
        } 
        if (description.isBlank()) {
            return new ErrorCommand(ERROR_EMPTY_DESC);
        }
        return new AddEventCommand(description, fromDate, toDate);
    }
    private Command prepareList(){
        return new ListCommand();
    }
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

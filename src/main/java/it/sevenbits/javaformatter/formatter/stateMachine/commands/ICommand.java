package it.sevenbits.javaformatter.formatter.stateMachine.commands;

/**
 * Command interface
 */
public interface ICommand {

    /**
     * Function for executing implementation Commands
     *
     * @throws CommandException is thrown if something goes wrong with executing Command
     */
    void execute() throws CommandException;
}

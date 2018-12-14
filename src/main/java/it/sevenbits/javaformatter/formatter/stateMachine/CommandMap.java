package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.formatter.stateMachine.commands.Append;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendBraceClose;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendBraceOpen;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendSemicolon;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendSpace;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.IgnoreCommand;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.stateMachineSupport.Pair;
import it.sevenbits.javaformatter.lexer.token.IToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains formatter commands (necessary for formatter state machine)
 */
public class CommandMap {
    private Map<Pair<IState, String>, ICommand> commandMap;

    /**
     * CommandMap constructor with no parameters
     *
     * @param formatterContext - formatter context for modifying
     */
    public CommandMap(final FormatterContext formatterContext) {
        commandMap = new HashMap<>();
        IState spaceAllowedState = new FormatterState("SPACE_ALLOWED");

        ICommand append = new Append(formatterContext);
        ICommand appendBraceClose = new AppendBraceClose(formatterContext);
        ICommand appendBraceOpen = new AppendBraceOpen(formatterContext);
        ICommand appendSpace = new AppendSpace(formatterContext);
        ICommand ignore = new IgnoreCommand();
        ICommand appendSemicolon = new AppendSemicolon(formatterContext);

        commandMap.put(new Pair<>(null, "TEXT"), append);
        commandMap.put(new Pair<>(null, "CURLY_BRACE_OPEN"), appendBraceOpen);
        commandMap.put(new Pair<>(null, "CURLY_BRACE_CLOSE"), appendBraceClose);
        commandMap.put(new Pair<>(null, "WHITESPACE"), ignore);
        commandMap.put(new Pair<>(null, "SEMICOLON"), appendSemicolon);

        commandMap.put(new Pair<>(spaceAllowedState, "WHITESPACE"), appendSpace);
    }

    /**
     * Function gets and return command
     *
     * @param currentState - current formatter state
     * @param currentToken - current token
     * @return command
     */
    public ICommand getCommand(final IState currentState, final IToken currentToken) {
        if (commandMap.containsKey(new Pair<>(currentState, currentToken.getName()))) {
            return commandMap.get(new Pair<>(currentState, currentToken.getName()));
        } else if (commandMap.containsKey(new Pair<>(currentState, (String) null))) {
            return commandMap.get(new Pair<>(currentState, (String) null));
        } else if (commandMap.containsKey(new Pair<>((IState) null, currentToken.getName()))) {
            return commandMap.get(new Pair<>((IState) null, currentToken.getName()));
        } else {
            return new IgnoreCommand();
        }
    }
}

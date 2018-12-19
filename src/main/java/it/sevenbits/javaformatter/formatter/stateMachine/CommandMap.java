package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.formatter.stateMachine.commands.Append;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendBraceClose;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendBraceOpen;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendNewLineAfterSemicolon;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.AppendSpace;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.IgnoreCommand;
import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;
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
     * @throws ConfigException is thrown if something goes wrong while reading config file
     */
    public CommandMap(final FormatterContext formatterContext) throws ConfigException {
        Config config = Config.getInstance();
        commandMap = new HashMap<>();
        IState spaceAllowedState = new FormatterState(config.getProperty("FORMATTER_STATE_SPACE_ALLOWED"));
        IState singleComment = new FormatterState(config.getProperty("FORMATTER_STATE_SINGLELINE_COM"));
        IState multiComment = new FormatterState(config.getProperty("FORMATTER_STATE_MULTILINE_COM"));
        IState afterSemicolonState = new FormatterState(config.getProperty("FORMATTER_STATE_AFTER_SEMICOLON"));

        ICommand append = new Append(formatterContext);
        ICommand appendBraceClose = new AppendBraceClose(formatterContext);
        ICommand appendBraceOpen = new AppendBraceOpen(formatterContext);
        ICommand appendSpace = new AppendSpace(formatterContext);
        ICommand ignore = new IgnoreCommand();
        ICommand appendNewLineAfterSemicolon = new AppendNewLineAfterSemicolon(formatterContext);

        commandMap.put(new Pair<>(null, "TEXT"), append);
        commandMap.put(new Pair<>(null, "CURLY_BRACE_OPEN"), appendBraceOpen);
        commandMap.put(new Pair<>(null, "CURLY_BRACE_CLOSE"), appendBraceClose);
        commandMap.put(new Pair<>(null, "WHITESPACE"), ignore);
        commandMap.put(new Pair<>(null, "SEMICOLON"), append);
        commandMap.put(new Pair<>(null, "SINGLE_LINE_COMMENT"), append);
        commandMap.put(new Pair<>(null, "MULTI_LINE_COMMENT"), append);
        commandMap.put(new Pair<>(null, "END_MULTI_LINE_COMMENT"), append);
        commandMap.put(new Pair<>(null, "NEW_LINE"), ignore);

        commandMap.put(new Pair<>(afterSemicolonState, "CURLY_BRACE_CLOSE"), appendBraceClose);
        commandMap.put(new Pair<>(afterSemicolonState, "TEXT"), appendNewLineAfterSemicolon);
        commandMap.put(new Pair<>(afterSemicolonState, "WHITESPACE"), ignore);

        commandMap.put(new Pair<>(singleComment, "TEXT"), append);
        commandMap.put(new Pair<>(singleComment, "CURLY_BRACE_OPEN"), append);
        commandMap.put(new Pair<>(singleComment, "CURLY_BRACE_CLOSE"), append);
        commandMap.put(new Pair<>(singleComment, "WHITESPACE"), append);
        commandMap.put(new Pair<>(singleComment, "SEMICOLON"), append);

        commandMap.put(new Pair<>(multiComment, "TEXT"), append);
        commandMap.put(new Pair<>(multiComment, "CURLY_BRACE_OPEN"), append);
        commandMap.put(new Pair<>(multiComment, "CURLY_BRACE_CLOSE"), append);
        commandMap.put(new Pair<>(multiComment, "WHITESPACE"), append);
        commandMap.put(new Pair<>(multiComment, "SEMICOLON"), append);
        commandMap.put(new Pair<>(multiComment, "NEW_LINE"), append);

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
        } else {
            return commandMap.get(new Pair<>((IState) null, currentToken.getName()));
        }
    }
}

package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.lexer.stateMachine.CommandTransition;
import it.sevenbits.javaformatter.lexer.stateMachine.LexerContext;
import it.sevenbits.javaformatter.lexer.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.lexer.stateMachine.LexerStateTransition;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.lexer.token.Token;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;

/**
 * Implementation of ILexer interface
 */
public class LexerStateMachine implements ILexer {
    private IReader reader;
    private LexerMap lexerMap;
    private LexerStateTransition lexerStateTransition;
    private CommandTransition commandTransition;
    private char lastChar;
    private LexerContext lexerContext;

    /**
     * Lexer constructor with one parameter
     *
     * @param reader - need to read symbols
     * @throws LexerException is thrown if something goes wrong with lexer
     */
    public LexerStateMachine(final IReader reader) throws LexerException {
        try {
            this.reader = reader;
            this.lexerMap = new LexerMap();
            this.lexerStateTransition = new LexerStateTransition();
            this.lexerContext = new LexerContext();
            this.commandTransition = new CommandTransition(lexerContext);
            this.lastChar = (int) 0;
        } catch (ConfigException e) {
            throw new LexerException("Something goes wrong with reading config file", e);
        }
    }

    @Override
    public IToken readToken() throws LexerException {
        IState currentState = lexerStateTransition.getStartState();
        ICommand command;
        lexerContext.setLexeme(new Lexeme());
        try {
            command = commandTransition.getNextCommand(currentState, lastChar);
            command.execute();
            currentState = lexerStateTransition.getNextState(currentState, lastChar);
            while (reader.hasNext() && !lexerContext.getLexeme().getIsDone()) {
                lastChar = reader.read();
                lexerContext.setSymbol(lastChar);
                command = commandTransition.getNextCommand(currentState, lastChar);
                command.execute();
                currentState = lexerStateTransition.getNextState(currentState, lastChar);
            }
            return new Token(lexerMap.getKey(lexerContext.getLexeme().toString()), lexerContext.getLexeme().toString());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Override
    public boolean hasMoreTokens() throws LexerException {
        try {
            return reader.hasNext() || lexerContext.getLexeme().getIsDone();
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with checking has next or not", e);
        }
    }
}

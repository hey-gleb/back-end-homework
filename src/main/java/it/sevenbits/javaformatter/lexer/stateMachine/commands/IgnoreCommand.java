package it.sevenbits.javaformatter.lexer.stateMachine.commands;

import it.sevenbits.javaformatter.lexer.stateMachine.LexerContext;

/**
 * Implementation of ICommand interface (class command for lexer state machine)
 */
public class IgnoreCommand implements ICommand {
    private LexerContext lexerContext;

    /**
     * IgnoreCommand constructor with one parameter
     *
     * @param lexerContext - lexer context for modifying
     */
    public IgnoreCommand(final LexerContext lexerContext) {
        this.lexerContext = lexerContext;
    }

    @Override
    public void execute() {
        lexerContext.getLexeme().setIsDone(false);
    }
}

package it.sevenbits.javaformatter.lexer.stateMachine.commands;

import it.sevenbits.javaformatter.lexer.stateMachine.LexerContext;

/**
 * Implementation of ICommand interface (class command for lexer state machine)
 */
public class ReturnCommand implements ICommand {
    private LexerContext lexerContext;

    /**
     * ReturnCommand constructor with one parameter
     *
     * @param lexerContext - lexer context for modifying
     */
    public ReturnCommand(final LexerContext lexerContext) {
        this.lexerContext = lexerContext;
    }

    @Override
    public void execute() {
        lexerContext.getLexeme().setIsDone(true);
    }
}

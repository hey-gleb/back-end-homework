package it.sevenbits.javaformatter.lexer.stateMachine.commands;

import it.sevenbits.javaformatter.lexer.stateMachine.LexerContext;

/**
 * Implementation of ICommand interface (class command for lexer state machine)
 */
public class AppendCommand implements ICommand {
    private LexerContext lexerContext;

    /**
     * AppendCommand constructor with one parameters
     *
     * @param lexerContext - lexer context for modifying
     */
    public AppendCommand(final LexerContext lexerContext) {
        this.lexerContext = lexerContext;
    }

    @Override
    public void execute() {
        lexerContext.getLexeme().append(lexerContext.getSymbol());
        lexerContext.getLexeme().setIsDone(false);
    }
}

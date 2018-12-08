package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.properties.ConfigException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LexerStateTransitionTest {
    private LexerStateTransition lexerStateTransition;

    @Before
    public void setUp() throws ConfigException {
        lexerStateTransition = new LexerStateTransition();
    }

    @Test
    public void testTextFromDefault() {
        assertEquals(new LexerState("TEXT"), lexerStateTransition.getNextState(lexerStateTransition.getStartState(), 'a'));
    }

    @Test
    public void testProbablyCommentFromDefault() {
        assertEquals(new LexerState("PROBABLY_COMMENT"), lexerStateTransition.getNextState(lexerStateTransition.getStartState(), '/'));
    }

    @Test
    public void testProbablyCommentFromText() {
        assertEquals(new LexerState("PROBABLY_COMMENT"), lexerStateTransition.getNextState(new LexerState("TEXT"), '/'));
    }

    @Test
    public void testSingleCommentFromProbablyComment() {
        assertEquals(new LexerState("SINGLE_LINE_COMMENT"), lexerStateTransition.getNextState(new LexerState("PROBABLY_COMMENT"), '/'));
    }
}

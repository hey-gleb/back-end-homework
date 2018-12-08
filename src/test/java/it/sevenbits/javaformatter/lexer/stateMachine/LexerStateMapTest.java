package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.properties.ConfigException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LexerStateMapTest {
    private LexerStateMap lexerStateMap;

    @Before
    public void setUp() throws ConfigException {
        lexerStateMap = new LexerStateMap();
    }

    @Test
    public void testTextStateFromDefault() {
        assertEquals(new LexerState("TEXT"), lexerStateMap.getNextState(lexerStateMap.getDefaultState(), 'A'));
    }

    @Test
    public void testSpaceStateFromDefault() {
        assertEquals(new LexerState("SPACE"), lexerStateMap.getNextState(lexerStateMap.getDefaultState(), ' '));
    }

    @Test
    public void testSpaceStateFromText() {
        assertEquals(new LexerState("SPACE"), lexerStateMap.getNextState(new LexerState("TEXT"), ' '));
    }

    @Test
    public void testReservedStateFromDefault() {
        assertEquals(new LexerState("RESERVED"), lexerStateMap.getNextState(lexerStateMap.getDefaultState(), '{'));
    }

    @Test
    public void testReservedStateFromText() {
        assertEquals(new LexerState("RESERVED"), lexerStateMap.getNextState(new LexerState("TEXT"), '{'));
    }

    @Test
    public void testProbablyCommentStateFromText() {
        assertEquals(new LexerState("PROBABLY_COMMENT"), lexerStateMap.getNextState(new LexerState("TEXT"), '/'));
    }

    @Test
    public void testSingleCommentStateFromProbablyComment() {
        assertEquals(new LexerState("SINGLE_LINE_COMMENT"), lexerStateMap.getNextState(new LexerState("PROBABLY_COMMENT"), '/'));
    }

    @Test
    public void testMultiCommentStateFromProbablyComment() {
        assertEquals(new LexerState("MULTI_LINE_COMMENT"), lexerStateMap.getNextState(new LexerState("PROBABLY_COMMENT"), '*'));
    }

    @Test
    public void testProbablyEndOfCommentStateFromMultiComment() {
        assertEquals(new LexerState("PROBABLY_END_MULTI_COMMENT"), lexerStateMap.getNextState(new LexerState("MULTI_LINE_COMMENT"), '*'));
    }
}

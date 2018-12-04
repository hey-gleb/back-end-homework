package it.sevenbits.javaformatter.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * Class has all lexemes
 */
public class LexerMap {
    private HashMap<String, String> lexerMap;

    /**
     * LexerMap class with no parameters
     */
     LexerMap() {
        lexerMap = new HashMap<>();
        lexerMap.put("SINGLE_LINE_COMMENT", "//");
        lexerMap.put("MULTILINE_COMMENT_START", "/*");
        lexerMap.put("MULTILINE_COMMENT_END", "*/");

        lexerMap.put("CURLY_BRACE_OPEN", "{");
        lexerMap.put("CURLY_BRACE_CLOSE", "}");
        lexerMap.put("SEMICOLON", ";");
        lexerMap.put("WHITESPACE", " ");
    }

    /**
     * Function need to check is there KEY-VALUE pair in the HashMap
     *
     * @param value value of KEY-VALUE pair
     * @return key if there is KEY-VALUE pair in the HashMap, otherwise return null
     * @throws LexerException is thrown, if value is null
     */
    public String getKey(final String value) throws LexerException {
        try {
            for (Map.Entry<String, String> entry : lexerMap.entrySet()) {
                if (value.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null;
        } catch (NullPointerException e) {
            throw new LexerException("Value is null", e);
        }
    }
}

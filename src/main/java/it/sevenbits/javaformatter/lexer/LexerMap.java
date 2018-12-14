package it.sevenbits.javaformatter.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * Class has all lexemes
 */
public class LexerMap {
    private String defaultName = "TEXT";
    private HashMap<String, String> lexerMap;

    /**
     * LexerMap class with no parameters
     */
    LexerMap() {
        lexerMap = new HashMap<>();
        lexerMap.put("CURLY_BRACE_OPEN", "{");
        lexerMap.put("CURLY_BRACE_CLOSE", "}");
        lexerMap.put("SEMICOLON", ";");
        lexerMap.put("WHITESPACE", " ");
        lexerMap.put("NEW_LINE", "\n");
        lexerMap.put("NONE", "");
        lexerMap.put("SINGLE_LINE_COMMENT", "//");
        lexerMap.put("MULTI_LINE_COMMENT", "/*");
        lexerMap.put("END_MULTI_LINE_COMMENT", "*/");
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
            return defaultName;
        } catch (NullPointerException e) {
            throw new LexerException("Value is null", e);
        }
    }
}

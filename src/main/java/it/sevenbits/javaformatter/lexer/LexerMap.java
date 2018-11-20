package it.sevenbits.javaformatter.lexer;

import java.util.HashMap;
import java.util.Map;

public class LexerMap {
    private HashMap<String, String> lexerMap;

    public LexerMap() {
        lexerMap = new HashMap<>();
        lexerMap.put("SINGLE_LINE_COMMENT", "//");
        lexerMap.put("MULTILINE_COMMENT_START", "/*");
        lexerMap.put("MULTILINE_COMMENT_END", "*/");

        lexerMap.put("CURLY_BRACE_OPEN", "{");
        lexerMap.put("CURLY_BRACE_CLOSE", "}");
        lexerMap.put("SEMICOLON", ";");
        lexerMap.put("WHITESPACE", " ");

    }

    public String getKey(final String name) {
        for (Map.Entry<String, String> entry : lexerMap.entrySet()) {
            if (name.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

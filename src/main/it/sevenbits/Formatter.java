package main.it.sevenbits;

public class Formatter {
    private int level;
    private StringBuilder stringBuilder;

    Formatter() {
        level = 0;
        stringBuilder = new StringBuilder();
    }

    public void format(StringBuilder stringCode) {
        stringCode = modifyWithRegularExpression(stringCode.toString());
        setTabulation(stringCode);
        System.out.println(stringBuilder.toString());
    }

    private StringBuilder modifyWithRegularExpression(String expression) {
        expression = expression.trim();
        expression = expression.replaceAll("\\s+", " ");
        expression = expression.replaceAll("\\s*\\{\\s*", " {\n");
        expression = expression.replaceAll("\\s*}\\s*", "}\n");
        expression = expression.replaceAll("\\s*;\\s*", ";\n");
        return new StringBuilder(expression);
    }

    private void setTabulation(StringBuilder stringCode) {
        for (int i = 0; i < stringCode.length(); i++) {
            setLevels(stringCode.charAt(i));
            if (i > 0 && stringCode.charAt(i - 1) == '\n') {
                addTabulation();
            }
            stringBuilder.append(stringCode.charAt(i));
        }
    }

    private void setLevels(char symbol) {
        switch (symbol) {
            case '{':
                level++;
                break;
            case '}':
                level--;
                break;
        }
    }

    private void addTabulation() {
        for (int i = 0; i < level; i++) {
            stringBuilder.append('\t');
        }
    }
}

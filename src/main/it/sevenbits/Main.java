package main.it.sevenbits;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Formatter formatter = new Formatter();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/it/sevenbits/tests/test.txt"));
        String stringCode;
        StringBuilder stringBuilder = new StringBuilder();
        while ((stringCode = bufferedReader.readLine()) != null) {
            stringBuilder.append(stringCode);
        }
        formatter.format(stringBuilder);
    }
}

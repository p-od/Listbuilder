package Listbuilder.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

    private BufferedReader reader;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int readInteger() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.err.println("Ihre Eingabe muss aus einer Zahl bestehen.");
            readInteger();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Eingabe.");
        }
        return 0;
    }

    public String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Eingabe.");
        }
        return null;
    }
}

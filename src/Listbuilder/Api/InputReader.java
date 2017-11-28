package Listbuilder.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class InputReader {

    private BufferedReader reader;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int readInteger() {
        try {
            String input = reader.readLine();
            if (input.equals("q") || input.equals("exit")) {
                System.exit(0);
            } else {
                return Integer.parseInt(input);
            }
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
            String input = reader.readLine();
            if (input.equals("q") || input.equals("exit")) {
                System.exit(0);
            } else {
                return input;
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Eingabe.");
        }
        return null;
    }

    public boolean confirmInput() throws InputMismatchException {
        try {
            String input = reader.readLine();
            if (input.equalsIgnoreCase("ja")) {
                return true;
            } else if (input.equalsIgnoreCase("nein")) {
                return false;
            } else {
                System.err.println("Ungültige Eingabe.");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Eingabe.");
        }
        return false;
    }
}

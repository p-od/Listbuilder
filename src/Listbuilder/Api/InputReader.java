package Listbuilder.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 * Diese Klasse liest die Eingaben des Anwenders in die Konsole aus.
 *
 * @author Gammenthaler Fabian
 * @author Kohler Kevin
 * @author Odermatt Pascal
 */
public class InputReader {

    private BufferedReader reader;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Liest die Konsoleneingabe des Anwenders aus und versucht, diesen in einen Integer-Wert umzuwandeln.
     * Kann die Eingabe nicht in einen Integer-Wert konvertiert werden, wird ein entsprechender Hinweis in der Konsole ausgegeben.
     * Daraufhin kann der Anwender die Eingabe wiederholen.
     *
     * Gibt der Anwender entweder 'q' oder 'exit' in der Konsole ein, wird die Applikation beendet.
     *
     * @return Die vom Anwender eingegebene Zahl, umgewandelt in einen Integer-Wert.
     */
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

    /**
     * Liest die Konsoleneingabe des Anwenders aus.
     *
     * Gibt der Anwender entweder 'q' oder 'exit' in der Konsole ein, wird die Applikation beendet.
     *
     * @return Den vom Anwender eingegebene Text.
     */
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

    /**
     * Liest die Konsoleneingabe des Anwenders aus.
     * Besteht die Anwort nicht aus 'ja' oder 'nein', wird die Meldung,
     * dass eine ungültige Eingabe getätigt wurde, in der Konsole ausgegeben.
     *
     * @return Den Wert true, wenn der Anwender als Antwort 'ja' eingibt, oder false, wenn der Anwender die Antwort 'nein' oder eine ungültige Antwort eingibt.
     */
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

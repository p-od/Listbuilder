package Listbuilder.Api;

import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;

import java.util.List;


/**
 * Diese Klasse enthält alle Texte, welche zur Laufzeit der Applikation zur Führung des Anwenders in der Konsole ausgegeben werden.
 *
 * @author Odermatt Pascal
 */
public class MenuPrinter {


    /**
     * Gibt den Header mit dem Namen der Applikation in der Konsole aus.
     */
    public void printHeader() {
        System.out.println("===================================================");
        System.out.println("=================== LISTBUILDER ===================");
        System.out.println("===================================================");
    }

    /**
     * Gibt die Frage, was der Anwender als nächstes tun möchte, in der Konsole aus.
     */
    public void printWhatToDoNext() {
        System.out.println("\nWas möchten Sie als nächstes tun?\n");
    }

    /**
     * Gibt die Hauptoptionen für den Anwender in der Konsole aus.
     * Mit Angabe der vorangestellten Zahl kann der Anwender diese Option ausführen.
     */
    public void printMainOptions() {
        System.out.println("1. Liste erstellen");
        System.out.println("2. Liste öffnen");
        System.out.println("3. About");
        System.out.println("4. Programm beenden");
    }

    /**
     * Gibt die Optionen bei geöffneter Liste für den Anwender in der Konsole aus.
     * Mit Angabe der vorangestellten Zahl kann der Anwender diese Option ausführen.
     */
    public void printListeOptions() {
        System.out.println("1. Eintrag hinzufügen");
        System.out.println("2. Eintrag erledigt/nicht erledigt");
        System.out.println("3. Eintrag löschen");
        System.out.println("4. Liste erledigt/nicht erledigt");
        System.out.println("5. Liste löschen");
        System.out.println("6. Zurück");
    }

    /**
     * Gibt den About-Text mit einigen grundlegenden Informationen für den Anwender in der Konsole aus.
     */
    public void printAbout() {
        System.out.println("Diese Applikation ermöglicht den Benutzer, Listen zu erstellen");
        System.out.println("und so seine Gedanken und Erinnerungen zentral festzuhalten.");
        System.out.println("\nDiese Applikation wurde im Rahmen der Module 213/306 innerhalb eines dreiköpfigen Entwicklerteams erstellt.");
        System.out.println("\nCopyright \u00a9 2017 by Gammenthaler Fabian, Kohler Kevin, Odermatt Pascal");
    }

    /**
     * Gibt die Aufforderung, dass der Anwender einen Namen für die Liste in der Konsole eingeben soll, in der Konsole aus.
     */
    public void printEnterListeName() {
        System.out.print("Geben Sie einen Namen für die neue Liste ein: ");
    }

    /**
     * Gibt die Aufforderung, dass der Anwender einen Namen für den Eintrag in der Konsole eingeben soll, in der Konsole aus.
     */
    public void printEnterEintragName() {
        System.out.print("Geben Sie einen Namen für den neuen Eintrag ein: ");
    }

    /**
     * Gibt die Bestätigung, dass die Liste erfolgreich erstellt wurde, in der Konsole aus.
     */
    public void printListeWasCreated() {
        System.out.println("Liste wurde erstellt.");
    }

    /**
     * Gibt die Bestätigung, dass der Eintrag erfolgreich hinzugefügt wurde, in der Konsole aus.
     */
    public void printEintragWasAdded() {
        System.out.println("Eintrag wurde hinzugefügt.");
    }

    /**
     * Gibt die Bestätigung, dass die Liste erfolgreich gelöscht wurde, in der Konsole aus.
     */
    public void printListeWasDeleted() {
        System.out.println("Liste wurde gelöscht.");
    }

    /**
     * Gibt die Bestätigung, dass der Eintrag erfolgreich gelöscht wurde, in der Konsole aus.
     */
    public void printEintragWasDeleted() {
        System.out.println("Eintrag wurde gelöscht.");
    }

    /**
     * Gibt die Frage, welche Liste der Anwender öffnen möchte, in der Konsole aus.
     */
    public void printWhichListeToOpen() {
        System.out.print("Welche Liste möchten sie öffnen? (Zahl eingeben): ");
    }

    /**
     * Gibt die Frage, of der Anwender mit seiner ausgewählten Aktion fortfahren möchte, in der Konsole aus.
     */
    public void printConfirmAction() {
        System.out.print("Sind Sie sicher? (ja/nein): ");
    }

    /**
     * Gibt die Frage, welchen Eintrag der Anwender markieren möchte, in der Konsole aus.
     */
    public void printWhichEintragToMark() {
        System.out.print("Welchen Eintrag möchten Sie als erledigt markieren? (Zahl eingeben): ");
    }

    /**
     * Gibt die Frage, welchen Eintrag der Anwender löschen möchte, in der Konsole aus.
     */
    public void printWhichEintragToDelete() {
        System.out.print("Welchen Eintrag möchten Sie löschen? (Zahl eingeben): ");
    }

    /**
     * Gibt die Meldung, dass die getätigte Eingabe ungültig ist, in der Konsole aus.
     */
    public void printInvalidInput() {
        System.err.println("Ungültige Eingabe.");
    }

    /**
     * Gibt die Meldung, dass keine Listen existieren, in der Konsole aus.
     */
    public void printNoListeExists() {
        System.out.println("Es existieren zurzeit keine Listen. Sie werden zurückgeleitet.");
    }

    /**
     * Gibt die Meldung, dass keine Einträge existieren, in der Konsole aus.
     */
    public void printNoEintragExists() {
        System.out.println("Diese Liste enthält zurzeit keine Einträge. Sie werden zurückgeleitet.");
    }

    /**
     * Gibt alle Listen in der Konsole aus.
     * Ausgegeben wird der Index beginnend mit 1, ein Kennzeichen in eckigen Klammern um anzuzeigen,
     * ob die Liste als erledigt markiert wurde, sowie der Name der Liste
     *
     * @param listen Liste mit den Listen, welche in der Konsole ausgegeben werden.
     */
    public void printAllListen(List<Liste> listen) {
        for (int i = 0; i < listen.size(); i++) {
            printListe(i + 1, listen.get(i));
        }
    }

    private void printListe(int index, Liste liste) {
        String erledigtMark = " ";
        if (liste.isErledigt()) {
            erledigtMark = "x";
        }
        System.out.println(index + ": [" + erledigtMark + "] " + liste.getName());
    }

    /**
     * Gibt den Inhalt einer Liste in der Konsole aus.
     * Ausgegeben wird der Name der Liste, sowie alle darin hinzugefügten Einträge.
     *
     * Pro Zeile wird ein Eintrag ausgegeben, welcher einen Index beginnend mit 1,
     * ein Kennzeichen in eckigen Klammern um anzuzeigen, ob der Eintrag als erledigt markiert wurde,
     * sowie dem Namen des Eintrags enthält.
     *
     * @param liste Liste, von welcher der Inhalt in der Konsole ausgegeben wird.
     */
    public void printListeDetails(Liste liste) {
        System.out.println("===================================================");
        System.out.println(liste.getName() + "\n");
        List<Eintrag> eintraegeinListe = liste.getEintraege();
        if (eintraegeinListe.size() > 0) {
            for (int i = 0; i < eintraegeinListe.size(); i++) {
                printEintrag(i + 1, eintraegeinListe.get(i));
            }
        }
        System.out.println("===================================================");
    }

    private void printEintrag(int index, Eintrag eintrag) {
        String erledigtMark = " ";
        if (eintrag.isErledigt()) {
            erledigtMark = "x";
        }
        System.out.println(index + ": [" + erledigtMark + "] " + eintrag.getName());
    }
}

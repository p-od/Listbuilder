package Listbuilder.Api;

import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;

import java.util.List;

public class MenuPrinter {

    public void printHeader() {
        System.out.println("===================================================");
        System.out.println("=================== LISTBUILDER ===================");
        System.out.println("===================================================");
    }

    public void printWhatToDoNext() {
        System.out.println("\nWas möchten Sie als nächstes Tun?\n");
    }

    public void printMainOptions() {
        System.out.println("1. Liste erstellen");
        System.out.println("2. Liste öffnen");
        System.out.println("3. About");
        System.out.println("4. Programm beenden");
    }

    public void printListeOptions() {
        System.out.println("1. Eintrag hinzufügen");
        System.out.println("2. Eintrag erledigt/nicht erledigt");
        System.out.println("3. Eintrag löschen");
        System.out.println("4. Liste erledigt/nicht erledigt");
        System.out.println("5. Liste löschen");
        System.out.println("6. Zurück");
    }

    public void printAbout() {
        System.out.println("Diese Applikation ermöglicht den Benutzer, Listen zu erstellen");
        System.out.println("und so seine Gedanken und Erinnerungen zentral festzuhalten.");
        System.out.println("\nDiese Applikation wurde im Rahmen der Module 213 / 306 innerhalb eines dreiköpfigen Entwicklerteams erstellt.");
        System.out.println("\nCopyright \u00a9 2017 by Gammenthaler Fabian, Kohler Kevin, Odermatt Pascal");
    }

    public void printEnterListeName() {
        System.out.print("Geben Sie einen Namen für die neue Liste ein: ");
    }

    public void printEnterEintragName() {
        System.out.print("Geben Sie einen Namen für den neuen Eintrag ein: ");
    }

    public void printListeWasCreated() {
        System.out.println("Liste wurde erstellt.");
    }

    public void printEintragWasAdded() {
        System.out.println("Eintrag wurde hinzugefügt.");
    }

    public void printListeWasDeleted() {
        System.out.println("Liste wurde gelöscht.");
    }

    public void printEintragWasDeleted() {
        System.out.println("Eintrag wurde gelöscht.");
    }

    public void printWhichListeToOpen() {
        System.out.print("Welche Liste möchten sie öffnen? (Zahl eingeben): ");
    }

    public void printConfirmAction() {
        System.out.print("Sind Sie sicher? (ja/nein): ");
    }

    public void printWhichEintragToMark() {
        System.out.print("Welchen Eintrag möchten Sie als erledigt markieren? (Zahl eingeben): ");
    }

    public void printWhichEintragToDelete() {
        System.out.print("Welchen Eintrag möchten Sie löschen? (Zahl eingeben): ");
    }

    public void printInvalidInput() {
        System.err.println("Ungültige Eingabe.");
    }

    public void printNoListeExists() {
        System.out.println("Es existieren zurzeit keine Listen. Sie werden zurückgeleitet.");
    }

    public void printNoEintragExists() {
        System.out.println("Diese Liste enthält zurzeit keine Einträge. Sie werden zurückgeleitet.");
    }

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

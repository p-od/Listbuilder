package Listbuilder.Api;

import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;

public class MenuPrinter {

    public void printHeader() {
        System.out.println("===================================================");
        System.out.println("====================LISTBUILDER====================");
        System.out.println("===================================================");
    }

    public void printMainOptions() {
        System.out.println("1. Liste erstellen");
        System.out.println("2. Liste öffnen");
        System.out.println("3. About");
    }

    public void printListeOptions() {
        System.out.println("1. Eintrag hinzufügen");
        System.out.println("2. Eintrag erledigt / nicht erledigt");
        System.out.println("3. Liste erledigt / nicht erledigt");
        System.out.println("4. List löschen");
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

    public void printListe(int index, Liste liste) {
        String erledigtMark = " ";
        if (liste.isErledigt()) {
            erledigtMark = "x";
        }
        System.out.println(index + ": [" + erledigtMark + "] " + liste.getName());
    }

    public void printWhichListeToOpen() {
        System.out.print("Welche Liste möchten sie öffnen? (Zahl eingeben): ");
    }

    public void printListeName(Liste liste) {
        System.out.println(liste.getName() + "\n");
    }

    public void printEintrag(int index, Eintrag eintrag) {
        String erledigtMark = " ";
        if (eintrag.isErledigt()) {
            erledigtMark = "x";
        }
        System.out.println(index + ": [" + erledigtMark + "] " + eintrag.getName());
    }

    public void printWhatToDoNext() {
        System.out.println("Was möchten Sie als nächstes Tun?");
    }
}

package Listbuilder.Business;

import Listbuilder.Api.InputReader;
import Listbuilder.Api.MenuPrinter;
import Listbuilder.Model.Liste;
import Listbuilder.Repository.EintragRepository;
import Listbuilder.Repository.ListeRepository;

import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse enthält die einzelnen Programmabläufe und den Main Loop der Applikation.
 *
 * @version 1.0
 *
 * @author Gammenthaler Fabian
 * @author Kohler Kevin
 * @author Odermatt Pascal
 */
public class Listbuilder {

    /**
     * Wird verwendet, um an verschiedenen Stellen in der Klasse den MenuPrinter aufzurufen,
     * um ausgewählte Nachrichten in der Konsole anzeigen zu lassen.
     */
    private MenuPrinter printer;

    /**
     * Wird verwendet, um an verschiedenen Stellen in der Klasse den MenuReader aufzurufen,
     * um Eingaben des Anwenders in die Konsole zu erfassen.
     */
    private InputReader reader;

    /**
     * Über diese Verbindung werden alle erstellten Listen zentral in einem Repository persistiert.
     */
    private ListeRepository listeRepository;

    /**
     * Über diese Verbindung werden alle erstellten Einträge zentral in einem Repository persistiert.
     */
    private EintragRepository eintragRepository;

    public Listbuilder() {
        this.printer = new MenuPrinter();
        this.reader = new InputReader();
        this.eintragRepository = new EintragRepository();
        this.listeRepository = new ListeRepository(this.eintragRepository);
    }

    /**
     * Der Startpunkt des Main Loops.
     * Von hier aus werden die einzelnen Programmabläufe gestartet.
     */
    public void start() {
        printer.printHeader();
        showMainOptions();
    }

    /**
     * Listet dem Anwender die Hauptoptionen auf, welche ihm zu Beginn in der Applikation zur Verfügung stehen.
     * Daraufhin kann der Anwender mittels Konsoleneingabe eine Option auswählen.
     */
    private void showMainOptions() {
        printer.printWhatToDoNext();
        printer.printMainOptions();
        int chosenOption = reader.readInteger();
        executeMainOption(chosenOption);
    }

    /**
     * Switch-case, in welchem, ausgehend von der Zahl, welche der Anwender für das Ausführen einer Hauptoption eingegeben hat,
     * der entsprechende Prozess ausgeführt wird.
     *
     * Bei ungültiger oder leerer Eingabe wird eine Fehlermeldung ausgegeben und der Prozess wiederholt.
     *
     * @param chosenMainOption Zahl/Index der Option, welche der Anwender ausgewählt hat.
     */
    private void executeMainOption(int chosenMainOption) {
        switch (chosenMainOption) {
            case 1:
                createListe();
                break;
            case 2:
                showAllListen();
                break;
            case 3:
                printer.printAbout();
                showMainOptions();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                printer.printInvalidInput();
                showMainOptions();
                break;
        }
    }

    /**
     * Frontend-Prozess zur Erstellung einer neuen Liste. Nach einem kurzen Hinweis soll der Anwender einen
     * Namen für die zu erstellende Liste eingeben.
     *
     * Daraufhin erhält der Anwender eine Bestätigung und wird zur Auswahl der Hauptoptionen zurückgeleitet.
     */
    private void createListe() {
        printer.printEnterListeName();
        String listeName = reader.readString();
        listeRepository.createListe(listeName);
        printer.printListeWasCreated();
        showMainOptions();
    }

    /**
     * Prüft in einem ersten Schritt, ob im ListeRepository persistierte Listen erhalten sind.
     * Wenn nicht, erscheint eine Fehlermeldung und der anwender wird zurückgeleitet.
     *
     * Wenn doch, wird Zeilenweise jede Liste mit den wichtigsten Informationen in der Konsole ausgegeben.
     * Daraufhin wird der Anwender aufgefordert, mittels Konsoleneingabe eine Liste auszuwählen, die er öffnen möchte.
     */
    private void showAllListen() {
        List<Liste> listen = listeRepository.getAllListen();
        checkHasListe(listen);
        printer.printAllListen(listen);
        printer.printWhichListeToOpen();
        int listeToOpenIndex = reader.readInteger();
        openListe(listeToOpenIndex);
    }

    /**
     * Versucht mittels zuvor vom Anwender getätigter Eingabe, eine Liste zu öffnen.
     * Ist die Eingabe ungültig oder leer wird der Anwender zurückgeleitet.
     * Ansonsten wir die Liste mit ihren Details ausgegeben.
     *
     * @param index Index der Liste, welche aus den bestehenden Listen im ListeRepository geöffnet werden soll.
     */
    private void openListe(int index) {
        try {
            Liste liste = listeRepository.getAllListen().get(index - 1);
            showListeDetails(liste);
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidInput();
            showAllListen();
        }
    }

    /**
     * Gibt die Detailinhalte einer Liste in der Konsole aus.
     *
     * @param liste Liste, deren Details in der Konsole ausgegeben werden sollen.
     */
    private void showListeDetails(Liste liste) {
        printer.printListeDetails(liste);
        showListeOptions(liste.getId());
    }

    /**
     * Listet dem Anwender Optionen auf, mit welchen er die Liste, in welcher er sich zurzeit befindet, erweitern oder bearbeiten kann.
     * Daraufhin kann der Anwender mittels Konsoleneingabe eine Option auswählen.
     *
     * @param listeId Id der Liste, welche für die Ausführung der folgenden Prozesse benötigt und weitergegeben wird.
     */
    private void showListeOptions(UUID listeId) {
        printer.printWhatToDoNext();
        printer.printListeOptions();
        int chosenOption = reader.readInteger();
        executeListeOption(chosenOption, listeId);
    }

    /**
     * Switch-case, in welchem, ausgehend von der Zahl, welche der Anwender für das Ausführen einer Option eingegeben hat,
     * der entsprechende Prozess ausgeführt wird.
     *
     * Bei ungültiger oder leerer Eingabe wird eine Fehlermeldung ausgegeben und der Prozess wiederholt.
     *
     * @param chosenListeOption Zahl/Index der Option, welche der Anwender ausgewählt hat.
     * @param listeId Id der Liste, welche für die Ausführung der folgenden Prozesse benötigt und weitergegeben wird.
     */
    private void executeListeOption(int chosenListeOption, UUID listeId) {
        switch (chosenListeOption) {
            case 1:
                createEintragInListe(listeId);
                break;
            case 2:
                setEintragErledigtInListe(listeId);
                break;
            case 3:
                deleteEintragFromListe(listeId);
                break;
            case 4:
                setListeErledigt(listeId);
                break;
            case 5:
                deleteListe(listeId);
                break;
            case 6:
                showMainOptions();
                break;
            default:
                printer.printInvalidInput();
                showListeOptions(listeId);
                break;
        }
    }

    /**
     * Frontend-Prozess zur Erstellung eines neuen Eintrags. Nach einem kurzen Hinweis soll der Anwender einen
     * Namen für den zu erstellenden Eintrag eingeben.
     *
     * Daraufhin erhält der Anwender eine Bestätigung und wird zu den Details der ausgewählten Listen zurückgeleitet.
     *
     * @param listeId Id der Liste, welche benötigt wird, um den Eintrag in der korrekte Liste zu erstellen.
     */
    private void createEintragInListe(UUID listeId) {
        printer.printEnterEintragName();
        String eintragName = reader.readString();
        listeRepository.addEintragToListe(listeId, eintragName);
        showListeDetails(listeRepository.getListe(listeId));
    }

    /**
     * Frontend-Prozess, um einen Eintrag in einer Liste als erledigt zu markieren.
     * Falls die Liste noch keine Einträge enthält, wird eine Fehlermeldung ausgegeben und der Anwender zurückgeleitet.
     *
     * Falls doch, wird der Anwender aufgefordert, mittels Konsoleneingabe einen Eintrag auszuwählen, der markiert werden soll.
     * Daraufhin erhält der Anwender eine Bestätigung und wird zu den Details der ausgewählten Listen zurückgeleitet.
     *
     * @param listeId Id der Liste, welche benötigt wird, um den Eintrag in der korrekten Liste als erledigt zu markieren.
     */
    private void setEintragErledigtInListe(UUID listeId) {
        Liste listeToGetEintragMarked = listeRepository.getListe(listeId);
        checkHasEintrag(listeToGetEintragMarked);
        printer.printWhichEintragToMark();
        int eintragToMarkIndex = reader.readInteger();
        UUID eintragId = getEintragIdFromListeByIndex(listeToGetEintragMarked, eintragToMarkIndex);
        eintragRepository.toggleEintragErledigt(eintragId);
        printer.printEintragWasAdded();
        showListeDetails(listeToGetEintragMarked);
    }

    /**
     * Gibt die Id eines Eintrag aus der Liste aller Einträge in einer Liste zurück, abhängig davon, welcher Index ausgeählt wurde.
     *
     * @param liste Liste, von welcher die EintragId eines Eintrags zurückgegeben werden soll.
     * @param index Index des Eintrags, dessen Id aus der Liste aller Einträge in der Liste zurückgegeben werden soll.
     *
     * @return Id des Eintrags, welcher über den Index ausgewählt wurde.
     */
    private UUID getEintragIdFromListeByIndex(Liste liste, int index) {
        try {
            return liste.getEintraege().get(index - 1).getId();
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidInput();
            showListeDetails(liste);
        }
        return null;
    }

    /**
     * Frontend-Prozess, um einen Eintrag in einer Liste zu löschen.
     * Falls die Liste noch keine Einträge enthält, wird eine Fehlermeldung ausgegeben und der Anwender zurückgeleitet.
     *
     * Falls doch, wird der Anwender aufgefordert, mittels Konsoleneingabe einen Eintrag auszuwählen, der gelöscht werden soll.
     * Daraufhin erhält der Anwender eine Bestätigung und wird zu den Details der ausgewählten Listen zurückgeleitet.
     *
     * @param listeId Id der Liste, welche benötigt wird, um den Eintrag in der korrekten Liste zu löschen.
     */
    private void deleteEintragFromListe(UUID listeId) {
        Liste listeToGetEintragDeleted = listeRepository.getListe(listeId);
        checkHasEintrag(listeToGetEintragDeleted);
        printer.printWhichEintragToDelete();
        int eintragToDeleteIndex = reader.readInteger();
        UUID eintragId = getEintragIdFromListeByIndex(listeToGetEintragDeleted, eintragToDeleteIndex);
        printer.printConfirmAction();
        if (reader.confirmInput()) {
            listeRepository.deleteEintragFromListe(listeId, eintragId);
            printer.printEintragWasDeleted();
            showListeDetails(listeToGetEintragDeleted);
        } else {
            showListeOptions(listeId);
        }
    }

    /**
     * Frontend-Prozess, um die Liste, in welcher der Anwender sich zurzeit befindet, als erledigt zu markieren.
     *
     * @param listeId Id der Liste, welche als erledigt markiert werden soll.
     */
    private void setListeErledigt(UUID listeId) {
        listeRepository.toggleListeErledigt(listeId);
        showMainOptions();
    }

    /**
     * Frontend-Prozess, um die Liste, in welcher der Anwender sich zurzeit befindet, zu löschen.
     *
     * @param listeId Id der Liste, welche gelöscht werden soll.
     */
    private void deleteListe(UUID listeId) {
        printer.printConfirmAction();
        if (reader.confirmInput()) {
            listeRepository.deleteListe(listeId);
            printer.printListeWasDeleted();
            showMainOptions();
        } else {
            showListeOptions(listeId);
        }
    }

    /**
     * Überprüft, ob in einer Liste ein Liste-Objekt einthalten ist.
     * Wenn nicht, wird eine Fehlermeldung ausgegeben und der Anwender zur Auswahl einer Hauptoption zurückgeleitet.
     *
     * Wenn doch, passiert nichts, und der aufrufende Prozess wird normal weitergeführt.
     *
     * @param listen Liste, welche auf vorhandene Liste-Objekte überprüft werden soll.
     */
    private void checkHasListe(List<Liste> listen) {
        if (listen.size() <= 0) {
            printer.printNoListeExists();
            showMainOptions();
        }
    }

    /**
     * Überprüft, ob eine Liste Eintrag-Objekte enthält.
     * Wenn nicht, wird eine Fehlermeldung ausgegeben und der Anwender zu den Details der ausgewählten Liste zurückgeleitet.
     *
     * Wenn doch, passiert nichts, und der aufrufende Prozess wird normal weitergeführt.
     *
     * @param liste Liste-Objekt, welches auf vorhandene Eintrag-Objekte überprüft werden soll.
     */
    private void checkHasEintrag(Liste liste) {
        if (liste.getEintraege().size() <= 0) {
            printer.printNoEintragExists();
            showListeDetails(liste);
        }
    }
}

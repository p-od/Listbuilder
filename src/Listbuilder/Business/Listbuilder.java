package Listbuilder.Business;

import Listbuilder.Api.InputReader;
import Listbuilder.Api.MenuPrinter;
import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;
import Listbuilder.Repository.EintragRepository;
import Listbuilder.Repository.ListeRepository;

import java.io.IOException;
import java.util.List;

public class Listbuilder {

    private MenuPrinter printer;

    private InputReader reader;

    private ListeRepository listeRepository;

    private EintragRepository eintragRepository;

    public Listbuilder() {
        this.printer = new MenuPrinter();
        this.reader = new InputReader();
        this.listeRepository = new ListeRepository();
        this.eintragRepository = new EintragRepository();
    }

    public void start() {
        printer.printHeader();
        showMainOptions();
    }

    private void showMainOptions() {
        printer.printWhatToDoNext();
        printer.printMainOptions();
        int chosenOption = reader.readInteger();
        executeMainOption(chosenOption);
    }

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
                break;
            default:
                break;
        }
    }

    private void createListe() {
        printer.printEnterListeName();
        String listeName = reader.readString();
        listeRepository.createListe(listeName);
        showMainOptions();
    }

    private void showAllListen() {
        List<Liste> listen = listeRepository.getAllListen();
        for (int i = 0; i < listen.size(); i++) {
            printer.printListe(i + 1, listen.get(i));
        }
        printer.printWhichListeToOpen();
        int listeToOpenIndex = reader.readInteger();
        Liste liste = listeRepository.getAllListen().get(listeToOpenIndex - 1);
        showListeDetails(liste);
    }

    private void showListeDetails(Liste liste) {
        printer.printListeName(liste);
        List<Eintrag> eintraegeinListe = liste.getEintraege();
        if (eintraegeinListe.size() > 0) {
            for (int i = 0; i < eintraegeinListe.size(); i++) {
                printer.printEintrag(i + 1, eintraegeinListe.get(i));
            }
        }
        showListeOptions();
    }

    private void showListeOptions() {
        printer.printWhatToDoNext();
        printer.printListeOptions();
        int chosenOption = reader.readInteger();
        executeListeOption(chosenOption);
    }

    private void executeListeOption(int chosenListeOption) {
        switch (chosenListeOption) {

        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

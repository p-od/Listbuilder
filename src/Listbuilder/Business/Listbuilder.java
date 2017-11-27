package Listbuilder.Business;

import Listbuilder.Api.InputReader;
import Listbuilder.Api.MenuPrinter;
import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;
import Listbuilder.Repository.EintragRepository;
import Listbuilder.Repository.ListeRepository;

import java.util.List;
import java.util.UUID;

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
        showListeOptions(liste.getId());
    }

    private void showListeOptions(UUID listeId) {
        printer.printWhatToDoNext();
        printer.printListeOptions();
        int chosenOption = reader.readInteger();
        executeListeOption(chosenOption, listeId);
    }

    private void executeListeOption(int chosenListeOption, UUID listeId) {
        switch (chosenListeOption) {
            case 1:
                createEintrag(listeId);
                break;
            case 2:
                setEintragErledigt(listeId);
                break;
            case 3:
                setListeErledigt(listeId);
                break;
            case 4:
                deleteListe(listeId);
                break;
            default:
                break;
        }
    }

    private void createEintrag(UUID listeId) {
        printer.printEnterEintragName();
        String eintragName = reader.readString();
        listeRepository.addEintragToListe(listeId, eintragName);
        showListeDetails(listeRepository.getListe(listeId));
    }

    private void setEintragErledigt(UUID listeId) {
        printer.printWhichEintragToMark();
        int eintragToMark = reader.readInteger();
        Liste listeToGetEintragMarked = listeRepository.getListe(listeId);
        UUID eintragId = listeToGetEintragMarked.getEintraege().get(eintragToMark).getId();
        eintragRepository.markEintragAsErledigt(eintragId);
        showListeDetails(listeToGetEintragMarked);
    }

    private void setListeErledigt(UUID listeId) {
        listeRepository.markListeAsErledigt(listeId);
        showMainOptions();
    }

    private void deleteListe(UUID listeId) {
        listeRepository.deleteListe(listeId);
        showMainOptions();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

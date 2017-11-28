package Listbuilder.Business;

import Listbuilder.Api.InputReader;
import Listbuilder.Api.MenuPrinter;
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
        this.eintragRepository = new EintragRepository();
        this.listeRepository = new ListeRepository(this.eintragRepository);
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

    private void createListe() {
        printer.printEnterListeName();
        String listeName = reader.readString();
        listeRepository.createListe(listeName);
        printer.printListeWasCreated();
        showMainOptions();
    }

    private void showAllListen() {
        List<Liste> listen = listeRepository.getAllListen();
        checkHasListe(listen);
        printer.printAllListen(listen);
        printer.printWhichListeToOpen();
        int listeToOpenIndex = reader.readInteger();
        openListe(listeToOpenIndex);
    }

    private void openListe(int index) {
        try {
            Liste liste = listeRepository.getAllListen().get(index - 1);
            showListeDetails(liste);
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidInput();
            showAllListen();
        }
    }

    private void showListeDetails(Liste liste) {
        printer.printListeDetails(liste);
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
                deleteEintrag(listeId);
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

    private void createEintrag(UUID listeId) {
        printer.printEnterEintragName();
        String eintragName = reader.readString();
        listeRepository.addEintragToListe(listeId, eintragName);
        showListeDetails(listeRepository.getListe(listeId));
    }

    private void setEintragErledigt(UUID listeId) {
        Liste listeToGetEintragMarked = listeRepository.getListe(listeId);
        checkHasEintrag(listeToGetEintragMarked);
        printer.printWhichEintragToMark();
        int eintragToMarkIndex = reader.readInteger();
        UUID eintragId = getEintragIdFromListeByIndex(listeToGetEintragMarked, eintragToMarkIndex);
        eintragRepository.toggleEintragErledigt(eintragId);
        printer.printEintragWasAdded();
        showListeDetails(listeToGetEintragMarked);
    }

    private UUID getEintragIdFromListeByIndex(Liste liste, int index) {
        try {
            return liste.getEintraege().get(index - 1).getId();
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidInput();
            showListeDetails(liste);
        }
        return null;
    }

    private void deleteEintrag(UUID listeId) {
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

    private void setListeErledigt(UUID listeId) {
        listeRepository.toggleListeErledigt(listeId);
        showMainOptions();
    }

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

    private void checkHasListe(List<Liste> listen) {
        if (listen.size() <= 0) {
            printer.printNoListeExists();
            showMainOptions();
        }
    }

    private void checkHasEintrag(Liste liste) {
        if (liste.getEintraege().size() <= 0) {
            printer.printNoEintragExists();
            showListeDetails(liste);
        }
    }
}

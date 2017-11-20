package Listbuilder.Repository;

import Listbuilder.Model.Eintrag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EintragRepository {

    private List<Eintrag> eintraege;

    public EintragRepository() {
        this.eintraege = new ArrayList<>();
    }

    public Eintrag getEintrag(UUID id) {
        return findById(id);
    }

    public List<Eintrag> getAllEintraege() {
        return this.eintraege;
    }

    public Eintrag createEintrag(String name) {
        Eintrag eintrag = new Eintrag(name);
        eintraege.add(eintrag);
        return eintrag;
    }

    public void deleteEintrag(UUID id) {
        Eintrag eintragToDelete = findById(id);
        eintraege.remove(eintragToDelete);
    }

    public void markEintragAsErledigt(UUID id) {
        Eintrag eintragToBeMarked = findById(id);
        eintragToBeMarked.setErledigt(true);
    }

    private Eintrag findById(UUID id) {
        for (Eintrag eintrag : eintraege) {
            if (eintrag.getId().equals(id)) {
                return eintrag;
            }
        }
        return null;
    }
}

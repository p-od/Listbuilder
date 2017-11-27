package Listbuilder.Repository;

import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListeRepository {

    private List<Liste> listen;

    private EintragRepository eintragRepository;

    public ListeRepository() {
        this.listen = new ArrayList<>();
        this.eintragRepository = new EintragRepository();
    }

    public Liste getListe(UUID id) {
        return findById(id);
    }

    public List<Liste> getAllListen() {
        return listen;
    }

    public void createListe(String name) {
        listen.add(new Liste(name));
    }

    public void deleteListe(UUID id) {
        Liste listeToBeDeleted = findById(id);
        listen.remove(listeToBeDeleted);
    }

    public void markListeAsErledigt(UUID id) {
        Liste listeToBeMarked = findById(id);
        listeToBeMarked.setErledigt(true);
    }

    public void addEintragToListe(UUID id, String name) {
        Liste listeToReceiveNewEintrag = findById(id);
        Eintrag createdEintrag = eintragRepository.createEintrag(name);
        listeToReceiveNewEintrag.addEintrag(createdEintrag);
    }

    private Liste findById(UUID id) {
        for (Liste liste : listen) {
            if (liste.getId().equals(id)) {
                return liste;
            }
        }
        return null;
    }
}

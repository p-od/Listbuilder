package Listbuilder.Repository;

import Listbuilder.Model.Eintrag;
import Listbuilder.Model.Liste;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse verfügt über eine Liste mit allen erstellten Listen.
 * Zudem enthält sie Schnittstellen für create, read und delete.
 *
 * @version 1.0
 *
 * @author Gammenthaler Fabian
 * @author Kohler Kevin
 * @author Odermatt Pascal
 */
public class ListeRepository {

    /**
     * Liste mit allen Listen, welche zur Laufzeit der Applikation erstellt werden.
     */
    private List<Liste> listen;

    /**
     * Link zum EintragRepository, da alle Einträge in ein und demselben EintragRepository hinterlegt werden sollen.
     */
    private EintragRepository eintragRepository;

    public ListeRepository(EintragRepository eintragRepository) {
        this.listen = new ArrayList<>();
        this.eintragRepository = eintragRepository;
    }

    /**
     * Gibt die Liste zurück, welche die angegebene Id besitzt.
     *
     * @param id Die Id der Liste, welche zurückgegeben werden soll.
     * @return Die Liste, welche die angegebene Id besitzt.
     */
    public Liste getListe(UUID id) {
        return findById(id);
    }

    /**
     * Gibt alle Listen zurück.
     *
     * @return Liste mit allen Listen.
     */
    public List<Liste> getAllListen() {
        return listen;
    }

    /**
     * Erstellt eine Liste und fügt ihn der Liste mit allen Listen hinzu.
     *
     * @param name Der Name, welchen die Liste erhalten soll.
     * @return Die erstellte Liste.
     */
    public void createListe(String name) {
        listen.add(new Liste(name));
    }

    /**
     * Löscht eine Liste und entfernt sie aus der Liste mit allen Listen.
     *
     * @param id Die Id der Liste, die gelöscht werden soll.
     */
    public void deleteListe(UUID id) {
        Liste listeToBeDeleted = findById(id);
        listen.remove(listeToBeDeleted);
    }

    /**
     * Ändert den Wert der erledigt-Variable einer Liste auf 'true', wenn er vorher 'false' war, und auf 'false', wenn er vorher 'true' war.
     *
     * @param id Die Id der Liste, deren Wert der erledigt-Variable angepasst werden soll.
     */
    public void toggleListeErledigt(UUID id) {
        Liste listeToBeMarked = findById(id);
        listeToBeMarked.toggleErledigt();
    }

    /**
     * Sucht eine Liste und fügt ihr einen Eintrag hinzu.
     * Erstellt den Eintrag zusätzlich noch im EintragRepository
     *
     * @param id Id der Liste, welcher ein Eintrag hinzugefügt werden soll.
     * @param name Der Name, welchen der Eintrag erhalten soll.
     */
    public void addEintragToListe(UUID id, String name) {
        Liste listeToReceiveNewEintrag = findById(id);
        Eintrag createdEintrag = eintragRepository.createEintrag(name);
        listeToReceiveNewEintrag.addEintrag(createdEintrag);
    }

    /**
     * Sucht eine Liste und entfernt einen darin enthaltenen Eintrag.
     * Entfernt den Eintrag zusätzlich noch im EintragRepository
     *
     * @param listeId Id der Liste, von welcher ein Eintrag entfernt werden soll.
     * @param eintragId Id des Eintrags, welcher aus der Liste entfernt werden soll.
     */
    public void deleteEintragFromListe(UUID listeId, UUID eintragId) {
        Liste listeToHaveEintragDeleted = findById(listeId);
        Eintrag eintragToBeDeleted = eintragRepository.findById(eintragId);
        listeToHaveEintragDeleted.removeEintrag(eintragToBeDeleted);
        eintragRepository.deleteEintrag(eintragToBeDeleted.getId());
    }

    /**
     * Sucht in der Liste mit allen Listen nach dem Eintrag, der die angegebene Id besitzt.
     *
     * @param id Die Id der Liste, die gesucht wird.
     * @return Die Liste mit der angegebenen Id, oder null, wenn keine passende Liste gefunden wurde.
     */
    private Liste findById(UUID id) {
        for (Liste liste : listen) {
            if (liste.getId().equals(id)) {
                return liste;
            }
        }
        return null;
    }
}

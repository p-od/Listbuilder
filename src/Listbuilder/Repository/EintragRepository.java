package Listbuilder.Repository;

import Listbuilder.Model.Eintrag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse verfügt über eine Liste mit allen erstellten Einträgen.
 * Zudem enthält sie Schnittstellen für create, read und delete.
 *
 * @version 1.0
 *
 * @author Gammenthaler Fabian
 * @author Kohler Kevin
 * @author Odermatt Pascal
 */
public class EintragRepository {

    /**
     * Liste mit allen Einträgen, welche zur Laufzeit der Applikation erstellt werden.
     */
    private List<Eintrag> eintraege;

    public EintragRepository() {
        this.eintraege = new ArrayList<>();
    }

    /**
     * Gibt den Eintrag zurück, welcher die angegebene Id besitzt.
     *
     * @param id Die Id des Eintrags, welcher zurückgegeben werden soll.
     * @return Den Eintrag, welcher die angegebene Id besitzt.
     */
    public Eintrag getEintrag(UUID id) {
        return findById(id);
    }

    /**
     * Gibt alle Einträge zurück.
     *
     * @return Liste mit allen Einträgen.
     */
    public List<Eintrag> getAllEintraege() {
        return this.eintraege;
    }

    /**
     * Erstellt einen Eintrag und fügt ihn der Liste mit allen Einträgen hinzu.
     *
     * @param name Der Name, welchen der Eintrag erhalten soll.
     * @return Den erstellten Eintrag.
     */
    public Eintrag createEintrag(String name) {
        Eintrag eintrag = new Eintrag(name);
        eintraege.add(eintrag);
        return eintrag;
    }

    /**
     * Löscht einen Eintrag und entfernt ihn aus der Liste mit allen Einträgen.
     *
     * @param id Die Id des Eintrags, der gelöscht werden soll.
     */
    public void deleteEintrag(UUID id) {
        Eintrag eintragToDelete = findById(id);
        eintraege.remove(eintragToDelete);
    }

    /**
     * Ändert den Wert der erledigt-Variable eines Eintrags auf 'true', wenn er vorher 'false' war, und auf 'false', wenn er vorher 'true' war.
     *
     * @param id Die Id des Eintrags, dessen Wert der erledigt-Variable angepasst werden soll.
     */
    public void toggleEintragErledigt(UUID id) {
        Eintrag eintragToBeMarked = findById(id);
        eintragToBeMarked.toggleErledigt();
    }

    /**
     * Sucht in der Liste mit allen Einträgen nach dem Eintrag, der die angegebene Id besitzt.
     *
     * @param id Die Id des Eintrags, der gesucht wird.
     * @return Der Eintrag mit der angegebenen Id, oder null, wenn kein passender Eintrag gefunden wurde.
     */
    public Eintrag findById(UUID id) {
        for (Eintrag eintrag : eintraege) {
            if (eintrag.getId().equals(id)) {
                return eintrag;
            }
        }
        return null;
    }
}

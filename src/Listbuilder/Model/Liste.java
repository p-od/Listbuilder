package Listbuilder.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse stellt die Listen dar, welcher der Anwender erstellen kann.
 * Für den Anwender wichtig ist die Angabe, ob die Liste erledigt wurde oder nicht,
 * sowie der Name der Liste.
 *
 * Der Liste können Einträge hinzugefügt werden, welche eine Aufgabe, eine Erinnerung o.Ä. darstellen sollen.
 *
 * @version 1.0
 *
 * @author Gammenthaler Fabian
 */
public class Liste {

    /**
     * Primärschlüssel, um die Liste eindeutig identifizieren zu können.
     */
    private UUID id;

    /**
     * Damit kann der Anwender angeben, ob er eine Liste resp. deren Inhalt/Aufgaben erledigt hat oder nicht.
     */
    private boolean erledigt;

    /**
     * Name der Liste. Dies kann ein Phantasiename oder beispielsweise eine konkrete Überschrift für eine Gruppe von Aufgaben/Erinnerungen sein.
     */
    private String name;

    /**
     * Liste mit Einträgen. Die Einträge sollen Aufgaben/Erinnerungen für den Anwender darstellen.
     */
    private List<Eintrag> eintraege;

    public Liste(String name) {
        this.id = UUID.randomUUID();
        this.erledigt = false;
        this.name = name;
        this.eintraege = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isErledigt() {
        return erledigt;
    }

    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Eintrag> getEintraege() {
        return eintraege;
    }

    public void setEintraege(List<Eintrag> eintraege) {
        this.eintraege = eintraege;
    }

    /**
     * Fügt der Liste einen Eintrag hinzu.
     *
     * @param eintrag Der Eintrag, welcher der Liste hinzugefügt werden soll.
     */
    public void addEintrag(Eintrag eintrag) {
        this.eintraege.add(eintrag);
    }

    /**
     * Entfernt einen bestimmten Eintrag aus der Liste.
     *
     * @param eintrag Der Eintrag, welcher aus der Liste entfernt werden soll.
     */
    public void removeEintrag(Eintrag eintrag) {
        eintraege.remove(eintrag);
    }

    /**
     * Ändert den Wert der erledigt-Variable auf 'true', wenn er vorher 'false' war, und auf 'false', wenn er vorher 'true' war.
     */
    public void toggleErledigt() {
        erledigt = !erledigt;
    }
}

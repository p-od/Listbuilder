package Listbuilder.Model;

import java.util.UUID;

/**
 * Diese Klasse stellt die Einträge dar, welcher der Anwender einer Liste hinzufügen kann.
 * Für den Anwender wichtig ist die Angabe, ob der Eintrag erledigt wurde oder nicht,
 * sowie der Name des Eintrags.
 *
 * @version 1.0
 *
 * @author Kohler Kevin
 */
public class Eintrag {

    /**
     * Primärschlüssel, um den Eintrag eindeutig identifizieren zu können.
     */
    private UUID id;

    /**
     * Damit kann der Anwender angeben, ob er einen Eintrag resp. dessen Inhalt/Aufgabe erledigt hat oder nicht.
     */
    private boolean erledigt;

    /**
     * Name des Eintrags. Dies kann ein Phantasiename oder eine konkrete Aufgabenstellung in Form einer Erinnerung sein.
     */
    private String name;

    public Eintrag(String name) {
        this.id = UUID.randomUUID();
        this.erledigt = false;
        this.name = name;
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

    /**
     * Ändert den Wert der erledigt-Variable auf 'true', wenn er vorher 'false' war, und auf 'false', wenn er vorher 'true' war.
     */
    public void toggleErledigt() {
        erledigt = !erledigt;
    }
}

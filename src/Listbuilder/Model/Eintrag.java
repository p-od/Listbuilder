package Listbuilder.Model;

import java.util.UUID;

/**
 * Diese Klasse stellt die Einträge dar, welcher der Anwender einer Liste hinzufügen kann.
 * Für den Anwender wichtig ist die Angabe, ob der Eintrag erledigt wurde oder nicht,
 * sowie der Name des Eintrags.
 *
 * @author Kohler Kevin
 */
public class Eintrag {

    private UUID id;

    private boolean erledigt;

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

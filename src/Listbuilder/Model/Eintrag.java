package Listbuilder.Model;

import java.util.UUID;

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
}

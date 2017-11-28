package Listbuilder.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Liste {

    private UUID id;

    private boolean erledigt;

    private String name;

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

    public void addEintrag(Eintrag eintrag) {
        this.eintraege.add(eintrag);
    }

    public void removeEintrag(Eintrag eintrag) {
        eintraege.remove(eintrag);
    }

    public void toggleErledigt() {
        erledigt = !erledigt;
    }
}

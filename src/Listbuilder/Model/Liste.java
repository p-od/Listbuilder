package Listbuilder.Model;

import java.util.List;

public class Liste {

    private int id;

    private boolean erledigt;

    private String name;

    private List<Eintrag> eintraege;

    public Liste() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

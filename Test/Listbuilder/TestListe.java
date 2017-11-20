package Listbuilder;

import Listbuilder.Model.Liste;

public class TestListe {

    @TEST
    public void createNewListeTest() {
        Liste liste = createNewListe("Test");
        assertTrue(liste.getListeName().equals("Test"));
    }


    @TEST
    public void deleteListeTest() {
        Liste liste = createNewListe("Test");
        liste.delete("Test");
        assertFalse(liste.hasElement("Test"));
    }
}

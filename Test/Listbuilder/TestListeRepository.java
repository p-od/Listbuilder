package Listbuilder;


import Listbuilder.Model.Eintrag;
import Listbuilder.Repository.EintragRepository;
import Listbuilder.Repository.ListeRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestListeRepository {

    private static UUID id;
    private static String name;

    @BeforeClass
    public static void init() {
        id = UUID.randomUUID();
        name = "Test";
    }

    @Test
    public void createNewListeTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);

        //invoke method under test
        listeRepo.createListe(name);

        //assert result
        assertEquals(name, listeRepo.getAllListen().get(0).getName());
    }

    @Test
    public void deleteListeTest() {
        // prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.deleteListe(id);

        //assert result
        assertEquals(0, listeRepo.getAllListen().size());
    }

    @Test
    public void toggleListeErledigtTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.toggleListeErledigt(id);

        //assert result
        assertTrue(listeRepo.getListe(id).isErledigt());
    }

    @Test
    public void getListeTest(){
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test & assert result
        assertEquals(name, listeRepo.getListe(id).getName());
    }

    @Test
    public void addEintragToListeTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.addEintragToListe(id, "testeintrag");

        //assert result
        assertEquals("testeintrag", listeRepo.getListe(id).getEintraege().get(0).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteEintragFromListeTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);
        listeRepo.addEintragToListe(id, "testeintrag");
        List<Eintrag> eintraege = listeRepo.getListe(id).getEintraege();
        UUID eintragToDeleteId = eintraege.get(0).getId();

        //invoke method under test
        listeRepo.deleteEintragFromListe(id, eintragToDeleteId);

        //provoke IndexOutOfBoundsException
        eintraege.get(0);
    }

    @Test
    public void getAllListenTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        ListeRepository listeRepo = new ListeRepository(eintragRepo);
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        int size = listeRepo.getAllListen().size();

        //assert result
        assertEquals(1, size);
    }
}

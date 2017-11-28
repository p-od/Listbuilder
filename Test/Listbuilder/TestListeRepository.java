package Listbuilder;


import Listbuilder.Repository.ListeRepository;
import org.junit.*;

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
        ListeRepository listeRepo = new ListeRepository();

        //invoke method under test
        listeRepo.createListe(name);

        //assert result
        assertEquals(name, listeRepo.getAllListen().get(0).getName());
    }

    @Test
    public void deleteListeTest() {
        // prepare
        ListeRepository listeRepo = new ListeRepository();
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.deleteListe(id);

        //assert result
        assertEquals(0, listeRepo.getAllListen().size());
    }

    @Test
    public void markAserledigtTest() {
        //prepare
        ListeRepository listeRepo = new ListeRepository();
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.markListeAsErledigt(id);

        //assert result
        assertTrue(listeRepo.getListe(id).isErledigt());
    }

    @Test
    public void getListeTest(){
        //prepare
        ListeRepository listeRepo = new ListeRepository();
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test & assert result
        assertEquals(name, listeRepo.getListe(id).getName());
    }

    @Test
    public void getAllListenTest() {
        //prepare
        ListeRepository listeRepo = new ListeRepository();
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        int size = listeRepo.getAllListen().size();

        //assert result
        assertEquals(1, size);
    }

    @Test
    public void addEintragToListeTest() {
        //prepare
        ListeRepository listeRepo = new ListeRepository();
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);

        //invoke method under test
        listeRepo.addEintragToListe(id, name);

        //assert result
        assertEquals(1, listeRepo.getListe(id).getEintraege().size());
    }
}

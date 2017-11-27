package Listbuilder;


import Listbuilder.Repository.ListeRepository;
import org.junit.*;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestListeRepository {

    private static ListeRepository listeRepo;
    private static UUID id;
    private static String name;

    @BeforeClass
    public static void init() {
        listeRepo = new ListeRepository();
        id = UUID.randomUUID();
        name = "Test";
    }

    @Test
    public void createNewListeTest() {
        listeRepo.createListe(name);
        assertEquals(name, listeRepo.getAllListen().get(0).getName());
    }

    @Test
    public void deleteListeTest() {
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);
        listeRepo.deleteListe(id);
        assertEquals(0, listeRepo.getAllListen().size());
    }

    @Test
    public void markAserledigtTest() {
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);
        listeRepo.markListeAsErledigt(id);
        assertTrue(listeRepo.getListe(id).isErledigt());
    }

    @Test
    public void getListeTest(){
        listeRepo.createListe(name);
        listeRepo.getAllListen().get(0).setId(id);
        listeRepo.markListeAsErledigt(id);
        assertTrue(listeRepo.getListe(id).isErledigt());
    }
}

package Listbuilder;

import Listbuilder.Repository.EintragRepository;
import org.junit.*;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEintragRepository {

    private static UUID id;
    private static String name;

    @BeforeClass
    public static void init() {
        id = UUID.randomUUID();
        name = "Test";
    }

    @Test
    public void createEintragTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();

        //invoke method under test
        eintragRepo.createEintrag(name);

        //assert result
        assertEquals(name, eintragRepo.getAllEintraege().get(0).getName());
    }

    @Test
    public void deleteEintragTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        eintragRepo.createEintrag(name);
        eintragRepo.getAllEintraege().get(0).setId(id);

        //invoke method under test
        eintragRepo.deleteEintrag(id);

        //assert result
        assertEquals(0, eintragRepo.getAllEintraege().size());
    }

    @Test
    public void markEintragAserledigtTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        eintragRepo.createEintrag(name);
        eintragRepo.getAllEintraege().get(0).setId(id);

        //invoke method under test
        eintragRepo.markEintragAsErledigt(id);

        //assert result
        assertTrue(eintragRepo.getEintrag(id).isErledigt());
    }

    @Test
    public void getEintragTest() {
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        eintragRepo.createEintrag(name);
        eintragRepo.getAllEintraege().get(0).setId(id);

        //invoke method under test & assert result
        assertEquals(name, eintragRepo.getEintrag(id).getName());
    }

    @Test
    public void getAllEintraegeTest(){
        //prepare
        EintragRepository eintragRepo = new EintragRepository();
        eintragRepo.createEintrag(name);

        //invoke method under test
        int size = eintragRepo.getAllEintraege().size();

        //assert result
        assertEquals(1, size);
    }
}

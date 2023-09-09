import Model.Usuario;
import dao.DAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class UsuarioTest {

    @Test

    public void testando(){

        DAO.getUsuarioDAO().create(new Usuario("JOAO"));
        DAO.getUsuarioDAO().create(new Usuario("Marcelino"));
        DAO.getUsuarioDAO().create(new Usuario("Junior bandidão"));
        DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino"));



        Assertions.assertEquals(DAO.getUsuarioDAO().findById(0).getNome(),"JOAO");
        Assertions.assertEquals(DAO.getUsuarioDAO().findById(1).getNome(),"Marcelino");
        Assertions.assertEquals(DAO.getUsuarioDAO().findById(2).getNome(),"Junior bandidão");
        Assertions.assertEquals(DAO.getUsuarioDAO().findById(3).getNome(),"Felipe souza marcelino");



    }

}
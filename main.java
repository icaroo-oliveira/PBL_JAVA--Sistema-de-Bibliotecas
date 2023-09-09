
import Model.*;
import dao.DAO;




public class main {
    public static void main(String[] args) {
        DAO.getUsuarioDAO().create(new Usuario("JOAO"));
        DAO.getUsuarioDAO().create(new Usuario("Marcelino"));
        DAO.getUsuarioDAO().create(new Usuario("Junior bandidão"));
        DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino"));


        System.out.println(DAO.getUsuarioDAO().findById(0).getNome());
        System.out.println(DAO.getUsuarioDAO().findById(1).getNome());
        System.out.println(DAO.getUsuarioDAO().findById(2).getNome());
        System.out.println(DAO.getUsuarioDAO().findById(3).getNome());

        System.out.println(DAO.getUsuarioDAO().findMany());


        Usuario usuario = DAO.getUsuarioDAO().findById(0);
        usuario.setNome("Felipe mascarenhas");

        DAO.getUsuarioDAO().update(DAO.getUsuarioDAO().findById(0),usuario);

        System.out.println(DAO.getUsuarioDAO().findById(0).getNome());

    }
}
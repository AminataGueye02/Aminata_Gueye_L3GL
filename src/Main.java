import dao.IUser;
import dao.UserImpl;
import entity.User;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[]  args){

        //Pour verifier la connexion avec la base (getConnection en public)
        //DB DB = new DB();
        //DB.getConnection();
        IUser iUser = new UserImpl();

        User user = new User();


        // Créer un scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);


        // Afficher le menu
        System.out.println("****************BIENVENUE*****************");
        System.out.println("1. Ajouter un utilisateur");
        System.out.println("2. Lister les utilisateurs");
        System.out.println("Entrez votre choix : ");

        // Lire le choix de l'utilisateur
        int choix = scanner.nextInt();

        // Traiter le choix de l'utilisateur
        switch (choix) {
            case 1:
                // Ajouter un utilisateur
                iUser.addUser();
                break;
            case 2:
                // Lister les utilisateurs
                List<User> users = iUser.listUsers();
                for (User u : users) {
                    System.out.println("Email : " +u.getEmail() + " Role : " + u.getRole_id().getName());
                }
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }
    }

}
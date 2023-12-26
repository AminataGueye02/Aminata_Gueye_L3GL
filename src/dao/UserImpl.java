package dao;

import entity.Role;
import entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser{

    DB db = new DB();
    int yes;
    @Override
    public int addUser() {
        // Insérer l'utilisateur dans la base de données

        User user = new User();
        PasswordHashing passwordHasheds = new PasswordHashing();
        Scanner scan = new Scanner(System.in);
        System.out.println(" Saisie des Infos Util");
        System.out.println("Email : ");
        String email = scan.nextLine();
        System.out.println("Mot de Passe : ");
        String password = scan.nextLine();
        user.setPasswordHashing(passwordHasheds.generatePasswordHash(password, "1234567890ABCDEF"));
        int IdOFRole = getTheIdOfRoleFromTableRole();
        if(IdOFRole != -1){
            Role rl = new Role();
            rl.setId(IdOFRole);
            user.setRole_id(rl);
        }
        //Recupération des valeurs saisies
        user.setEmail(email);
        user.setPassword(password);

        // Vérifier que l'adresse email est définie
        boolean isValide = true;
        if (email == null || email.isEmpty()) {
            isValide = false;
            System.out.println("L'adresse email est obligatoire.");
        } else {
            int index = email.indexOf("@");
            if (index == -1) {
                isValide = false;
                System.out.println("L'adresse email doit contenir un @.");
            } else {
                String domaine = email.substring(index + 1);
                if (!domaine.equals("gmail.com")) {
                    isValide = false;
                    System.out.println("L'adresse email doit être de type @gmail.com.");
                }
            }
        }

        if (!isValide) {
            return 0;
        }

        // Vérifier que le password est définie
        if (password == null || password.isEmpty()) {
            System.out.println("Le mot de passe est obligatoire.");
            return 0;
        }

        String sql = "INSERT INTO user(email, password,passwordHashing,role_id) VALUES(?,?,?,?)";

        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, user.getEmail());
            db.getPstm().setString(2, user.getPasswordHashing());
            db.getPstm().setString(3, user.getPasswordHashing());

            Role role = user.getRole_id();
            if (role != null){
                int RoleId = role.getId();
                db.getPstm().setInt(4, RoleId);
            }

            yes= db.executeMaj();
            db.clossConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return yes;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        // Récupérer tous les utilisateurs de la base de données
        String sql = "SELECT * FROM user u  JOIN role r ON u.role_id = r.idR";

        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setEmail(rs.getString("u.email"));
                Role role = new Role();
                role.setId(rs.getInt("r.idR"));
                role.setName(rs.getString("r.name"));
                user.setRole_id(role);
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // Retourner la liste d'utilisateurs
        return users;
    }

    public int getTheIdOfRoleFromTableRole(){
        int IdOFRole=-1;
        String sql = "SELECT idR FROM role WHERE idR=1";
        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();

            if (rs.next()) {
                IdOFRole = rs.getInt("idR");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return IdOFRole;
    }
}

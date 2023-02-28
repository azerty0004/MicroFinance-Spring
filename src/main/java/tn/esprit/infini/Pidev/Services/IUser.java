package tn.esprit.infini.Pidev.Services;



import tn.esprit.infini.Pidev.entities.User;

import java.util.List;

public interface IUser {
    //CRUD
    User addUser(User user);
    List<User> retrieveAllUsers();
    User updateUser (User user);
    void deleteUser(Integer idUser);
    //Advanced functions
    User retrieveUser (int idUser);
    User retrieveUserByLogin(String login);
    Boolean login(String login, String mdp);
    void banUser(User user);
}

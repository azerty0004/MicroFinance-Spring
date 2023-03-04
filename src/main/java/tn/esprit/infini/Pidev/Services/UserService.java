package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.TypeUser;
import tn.esprit.infini.Pidev.entities.User;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUser{
    UserRepository UR;
    //CRUD
    @Override
    public User addUser(User user) {
        UR.findById(user.getId()).get().setType(TypeUser.Potential_Client);
        return UR.save(user);}
    @Override
    public List<User> retrieveAllUsers() {return (List<User>) UR.findAll();}
    @Override
    public User updateUser(User user) {return UR.save(user);}
    @Override
    public void deleteUser(Integer idUser) {UR.deleteById(idUser);}
    //Advanced functions
    @Override
    public User retrieveUser(int idUser) {return UR.findById(idUser).get();}

    @Override
    public User retrieveUserByLogin(String login) {return UR.findByLogin(login);}

    @Override
    public Boolean login(String login, String mdp) {
        if (UR.findByLogin(login)!=null){if (UR.findByLogin(login).getPassword()==mdp) {
            UR.findByLogin(login).setNombreTentatives(0);
            return true;
        }
            else if (UR.findByLogin(login).getNombreTentatives()==5) return false;
            else {
            UR.findByLogin(login).setNombreTentatives(UR.findByLogin(login).getNombreTentatives()+1);
                return false;
        }}
        else return false;
    }
    @Override
    public void banUser(User user) {

    }
}

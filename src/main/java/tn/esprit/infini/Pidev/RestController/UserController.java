package tn.esprit.infini.Pidev.RestController;

import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IUser;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;

public class UserController {
    private IUser iUser;
    @GetMapping("/getUsers")
    List<User> afficher() {
        return iUser.retrieveAllUsers();
    }
    @PostMapping("/addUser")
    User ajouter(@RequestBody User user) {
        return iUser.addUser(user);
    }

    @GetMapping("/getUserById/{id}")
    User afficherAvecId(@PathVariable int idUser){
        return iUser.retrieveUser(idUser);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return iUser.updateUser(user);
    }

    //@DeleteMapping("/deleteUser/{idUser}")
    //void deletePack(@PathVariable ("idGuarantor") Integer idUser)
    //{
    //    iUser.deleteUser(idUser);
    //}
}

package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IUser;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;
@RestController
@AllArgsConstructor
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

    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable ("idUser") Integer idUser)
    {iUser.deleteUser(idUser);}
    @PutMapping("/changePassword/{idUser}/{newPassword}")
    public void changePassword(@PathVariable int idUser, @PathVariable String newPassword) {
        iUser.changePassword(iUser.retrieveUser(idUser),newPassword);
    }
    @GetMapping("/veriyUserPassword/{idUser}/{password}")
    public boolean veriyUserPassword(@PathVariable int idUser,@PathVariable String password){
        if(iUser.veriyUserPassword(iUser.retrieveUser(idUser),password)==false) {return false;}
        else return true;
    }
}

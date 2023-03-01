
package com.team1159ers.mock_csulb_db.controller.user;

import com.team1159ers.mock_csulb_db.model.user.User;
import com.team1159ers.mock_csulb_db.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.saveUser(user);
        return "New User has been added.";
    }

    @GetMapping("/getAll")
    public List<User> list(){
        return userService.getAllUsers();
    }

    @GetMapping("/getByCsulbId")
    public User getUserByCsulbId(@RequestParam("csulbId") String csulbId) {
        User user;
        try {
            System.out.println(csulbId);
            int csulbIdAsInt = Integer.parseInt(csulbId);
            System.out.println(csulbIdAsInt);
            user = userService.getUserByCsulbId(csulbIdAsInt);
        } catch (NumberFormatException e) {
            user = new User();
        }

        return Objects.requireNonNullElseGet(user, User::new);
    }

    @GetMapping("/getByEmail")
    public User getUserByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);

        return Objects.requireNonNullElseGet(user, User::new);
    }
}

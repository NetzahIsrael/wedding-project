package com.example.demo1.Controller;

import com.example.demo1.model.Users;
import com.example.demo1.services.UsersRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users")
@RestController
@CrossOrigin(origins = "http://localhost:5174", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsersController {

    private UsersRepository usersRepository;
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/signUp")
    public ResponseEntity<Users> signUp(@RequestBody Users user) {
        List<Users> users = usersRepository.findAll();
        for (Users u : users) {
            if (u.getUsername().equals(user.getUsername()))
                return new ResponseEntity<>(u, HttpStatus.CONFLICT);
        }
        Users newUser = usersRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "http://localhost:5173") // אפשר גישה מהלקוח
    @PostMapping("/Log_in")
    public ResponseEntity <Users> Log_in(@RequestBody Users  newuser){
        List<Users> useresList=usersRepository.findAll();
        for(Users u:useresList){
            if (u.getUsername() != null && u.getUsername().equals(newuser.getUsername())) {
                if (u.getPassword().equals(newuser.getPassword())){
                    return new ResponseEntity<>(u, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        System.out.println("gooodddddddddddd");
        return new ResponseEntity<>(new Users(), HttpStatus.NOT_FOUND);
    }
    //Get----מקבלת קוד מחזירה אובייקט
    @GetMapping( "/getUsers/{id}")
    public ResponseEntity<Users> getUsers(@PathVariable Long id) {
        Users s = usersRepository.getById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users s = usersRepository.findById(id).orElse(null);
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<Users> getUserByName(@PathVariable String name) {
        List<Users> list = usersRepository.findAll();
        for (Users u : list) {
            if (u.getUsername().equals(name))
                return new ResponseEntity<>(u,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //Get--מחזירה רשימה של אובייקטים
    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updateUsers/{userId}")
    public ResponseEntity updateUsers(@RequestBody Users users, @PathVariable Long id) {
        if (usersRepository.findById(id).isPresent()) {
            Users u = usersRepository.findById(id).get();
            u.setUsername(users.getUsername());
            u.setPassword(users.getPassword());
            u.setEmail(users.getEmail()); // Assuming 'mail' is a field in the Users entity

            usersRepository.save(u);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //delete--מחיקת אובייקט מהטבלה
    @DeleteMapping("/deletUser/{id}")
    public ResponseEntity<Void> deletUser(@PathVariable Long id){
        usersRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // מחזיר 204
    }

    //     Post--הוספה של אובייקט לטבלה
    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
        List<Users> useresList = usersRepository.findAll();
        for (Users u : useresList) {
            if (u.getUsername()!=null&& u.getPassword()!=null && u.getUsername() .equals(users.getUsername()) && u.getPassword() .equals(users.getPassword())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        Users newUsers = usersRepository.save(users);
        return new ResponseEntity<>(newUsers, HttpStatus.CREATED);

    }



}

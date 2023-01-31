package com.andy.medicab.contoller;

import com.andy.medicab.model.Account;
import com.andy.medicab.model.Driver;
import com.andy.medicab.model.Hopital;
import com.andy.medicab.model.User;
import com.andy.medicab.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.andy.medicab.contoller.Utils.*;

/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/account/")
public class AccountController {
    @Autowired
    private Accountservice accountservice;
    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private HopitalService hopitalService;
    @Autowired
    private EmailService emailService;
    @PostMapping(path = "newUser")
    public ResponseEntity<Long> addNewUser(@RequestBody User user){
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            Long id = userService.save(user);
            this.sendSuccessMessage(user);
            return  new ResponseEntity<Long>(id, HttpStatus.CREATED);
        }catch (Exception e){
            //e.printStackTrace();
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");
            return buildErrorMessage(e,"Echec d'enregistrement, \n Reessayez plus tard");
        }
    }
    void sendSuccessMessage(Account account){
        emailService.sendMail(account.getEmail(),"Confirmation d'inscription","Votre inscription a été effectuée avec success");
    }
    @PostMapping(path = "newDriver")
    public ResponseEntity<Long> addNewDriver(@RequestBody Driver user){
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            Long id = driverService.save(user);
            this.sendSuccessMessage(user);
            return  new ResponseEntity<Long>(id, HttpStatus.CREATED);
        }catch (Exception e){
            //e.printStackTrace();
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");
            return buildErrorMessage(e,"Echec d'enregistrement, \n Reessayez plus tard");
        }
    }
    @PostMapping(path = "newHopital")
    public ResponseEntity<Long> addNewHopital(@RequestBody Hopital user){
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            Long id = hopitalService.save(user);
            this.sendSuccessMessage(user);
            return  new ResponseEntity<Long>(id, HttpStatus.CREATED);
        }catch (Exception e){
            //e.printStackTrace();
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");
            return buildErrorMessage(e,"Echec d'enregistrement, \n Reessayez plus tard");
        }
    }
    @GetMapping(path = "findUser/{typeAccount}/{emailOrPhone}/{password}")
    public ResponseEntity<Account> findUser(@PathVariable String typeAccount,@PathVariable String emailOrPhone,@PathVariable String password){
        try {
            String password1 = encryptPassword(password);
            Account account = this.accountservice.findByNumberOrEmail(emailOrPhone,emailOrPhone,password1);
            if (account == null) {
                throw new Exception("Aucune compte trouvé, Veuillez verifiez vos identifiants");
            }
            return new ResponseEntity<Account>(account,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return buildErrorMessage(e.getMessage());
        }
    }
  @GetMapping(path = "findUserByCookie/{typeAccount}/{emailOrPhone}/{password}")
  public ResponseEntity<Account> findUserByCookie(@PathVariable String typeAccount,@PathVariable String emailOrPhone,@PathVariable String password){
    try {
      Account account = this.accountservice.findByNumberOrEmail(emailOrPhone,emailOrPhone,password);
      if (account == null) {
        throw new Exception("Aucune compte trouvé, Veuillez verifiez vos identifiants");
      }
      return new ResponseEntity<Account>(account,HttpStatus.OK);
    }catch (Exception e){
      e.printStackTrace();
      return buildErrorMessage(e.getMessage());
    }
  }
  @GetMapping(path="getAccountById/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable Long id ){
      try {
        Account account = this.accountservice.findById(id);
        if (account == null) {
          throw new Exception("Aucune compte trouvé");
        }
        return new ResponseEntity<Account>(account,HttpStatus.OK);
      }catch (Exception e){
        e.printStackTrace();
        return buildErrorMessage(e.getMessage());
      }
  }
}

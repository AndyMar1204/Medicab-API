package com.andy.medicab.controller;

import com.andy.medicab.model.Account;
import com.andy.medicab.model.Doctor;
import com.andy.medicab.model.Hopital;
import com.andy.medicab.model.User;
import com.andy.medicab.services.DoctorService;
import com.andy.medicab.services.EmailServiceImpl;
import com.andy.medicab.services.HopitalService;
import com.andy.medicab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.andy.medicab.controller.Utils.*;


/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/users/")
public class UserController implements CrudController<User,Long>{
    @Autowired
    private UserService service;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HopitalService hopitalService;
    @Autowired
    private EmailServiceImpl emailService;
    @RequestMapping("")
    public String home() {
        return "API pour les utilisateurs";
    }

    public void addMyAccount(){
        User user = new User();
        user.setPostnom("Andy");
        user.setNumber("+243970634739");
        user.setEmail("josueandy5@gmail.com");
        user.setPassword("andy1204");
        user.setSexe("M");
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            Long id = service.save(user);
            this.sendSuccessMessage(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param user
     * @return <Id>
     */
    @Override
    @PostMapping(path = SAVE_)
    public ResponseEntity<Long> save(@RequestBody User user) {
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            Long id = service.save(user);
            this.sendSuccessMessage(user);
            return  new ResponseEntity<Long>(id, HttpStatus.CREATED);
        }catch (Exception e){
            //e.printStackTrace();
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");

            return buildErrorMessage(e,"Echec d'enregistrement, \n Reessayez plus tard");
        }
    }
    void sendSuccessMessage(Account account) {
        try{
        emailService.sendMail(account.getEmail(),"Confirmation d'inscription","Votre inscription a été effectuée avec success");
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }
    /**
     * @param user
     * @param aLong
     * @return <T>
     */
    @Override
    public ResponseEntity<User> update(User user, Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @return <Void>
     */
    @Override
    public ResponseEntity<Void> delete(Long aLong) {
        return null;
    }

    /**
     * @param id
     * @return <T>
     */
    @GetMapping(FIND_BY_ID+"{id}")
    @Override
    public ResponseEntity<User> findById(@PathVariable Long id) {
        try {
            User user= service.findById(id);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Aucun utilisateur trouvé");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }

    }

    /**
     * @return ResponseEntity<List < T>>
     */
    @GetMapping(FIND_ALL)
    @Override
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<List<User>>(service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Aucun utilisateur trouvé");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }
    }

    /**
     * @param id
     * @return <Boolean>
     */

    @GetMapping(CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        try {
            return new ResponseEntity<Boolean>(service.existById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Une erreur est survenue lors du traitement");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }
    }
    @PostMapping(path="updateInfo/{id}")
    ResponseEntity<User> updateUserInfo(@PathVariable Long id,@RequestBody User user){
        System.out.println(user);
        if(service.existById(id)) {
            try {
                User us = service.findById(id);
                us.setUsername(user.getUsername());
                us.setPostnom(user.getPostnom());
                us.setDonneurOrgane(user.getDonneurOrgane());
                us.setAlergies(user.getAlergies());
                us.setTraitement(user.getTraitement());
                us.setGroupeSanguin(user.getGroupeSanguin());
                us.setSexe(user.getSexe());

                return new ResponseEntity<User>(service.update(us),HttpStatus.OK);
            }catch(Exception ex) {
                return buildErrorMessage(ex, "Echec");
            }


        }else
            return buildErrorMessage("Aucun utilisateur à modifier");
    }
    @PostMapping(path = "addUserDoctor/{id_user}")
    public ResponseEntity<Doctor> addUserDoctor(@PathVariable Long id_user,@RequestBody Doctor doctor){

        if(service.existById(id_user)){
            User user = service.findById((id_user));
            if ((doctor.getId()==null)){
                long id_doc = doctorService.save(doctor);
                doctor.setId(id_doc);
                user.setDoctor(doctorService.findById(id_doc));
                //user.setDoctor(doctor);
            }else {
                Doctor doc = doctorService.findById(doctor.getId());
                user.setDoctor(doc);
            }
            service.update(user);
            return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
        }

        return buildErrorMessage("Une erreur est survenue, Reessayez plus tard");
    }
    @GetMapping(path = "setUserHopital/{id_user}/{id_hopital}")
    ResponseEntity addHopitalReference(@PathVariable long id_user,@PathVariable long id_hopital){
        if((service.existById(id_user))&&(hopitalService.existById(id_hopital))){
            User user = service.findById(id_user);
            Hopital hopital = hopitalService.findById(id_hopital);
            user.setHopital(hopital);
            service.update(user);
            return buildSuccessMessage("Mise à jour reussie");
        }
        return  buildSuccessMessage("Echec, Aucune modification effectuée");
    }
}

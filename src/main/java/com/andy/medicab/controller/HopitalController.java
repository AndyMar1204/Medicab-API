package com.andy.medicab.controller;

import com.andy.medicab.model.Hopital;
import com.andy.medicab.services.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static com.andy.medicab.controller.Utils.*;

/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/hospitals/")
public class HopitalController  implements CrudController<Hopital,Long> {
    @Autowired
    private HopitalService service;
    @RequestMapping("")
    public String home() {
        return "API pour les hopitaux";
    }
    @PostMapping(path = SAVE_)
    @Override
    public ResponseEntity<Long> save(@RequestBody Hopital hopital) {
        try {
            hopital.setPassword((encryptPassword(hopital.getPassword())));
            return new ResponseEntity<Long>(service.save(hopital), HttpStatus.CREATED);
        }catch (Exception ex){
            if(ex instanceof DataIntegrityViolationException)
                return buildErrorMessage(ex, "email ou numero deja utilisé par un autre compte");
            return buildErrorMessage(ex,"L'enregistrement a echoué");
        }

    }
    @PutMapping(path=UPDATE_+"{id}")
    @Override
    public ResponseEntity<Hopital> update(@RequestBody Hopital hopital,@PathVariable Long id) {
        try {
            return new ResponseEntity<Hopital>(service.update(hopital),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex, "Echec lors de la mise à jour");
        }
    }
    @DeleteMapping(path = DELETE_BY_ID+"{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return buildSuccessMessage("Hopital Supprimé avec success");
        }catch (Exception ex){
            return  buildErrorMessage(ex,"Echec lors de la suppression");
        }
    }
    @GetMapping(FIND_BY_ID+"{id}")
    @Override
    public ResponseEntity<Hopital> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Hopital>(service.findById(id),HttpStatus.OK);
        }catch (Exception ex){
            return  buildErrorMessage(ex, "Hopital non trouvé");
        }
    }
    @GetMapping(FIND_ALL)
    @Override
    public ResponseEntity<List<Hopital>> getAll() {
        try {
            List<Hopital> list = service.findAll();
            return new ResponseEntity<List<Hopital>>(list,HttpStatus.OK);
        }catch (Exception ex){
            return  buildErrorMessage(ex, "Hopitaux non trouvés");
        }
    }
    @GetMapping(CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        try {
            return new ResponseEntity<Boolean>(service.existById(id), HttpStatus.OK);
        }catch (Exception e){
            return buildErrorMessage(e,"Une erreur est survenue lors du traitement");
        }
    }
    @PutMapping(path="updateInfo")
    ResponseEntity<Hopital> updateInfo(@RequestBody Hopital hopital){
        if(service.existById(hopital.getId())) {
            Hopital h = service.findById(hopital.getId());
            h.setNom(hopital.getNom());
            h.setHeureFermeture(hopital.getHeureFermeture());
            h.setHeureOuverture(hopital.getHeureOuverture());
            h.setInfos(hopital.getInfos());
            try {
                return new ResponseEntity<Hopital>(service.update(h),HttpStatus.OK);
            }catch(Exception ex) {
                return buildErrorMessage(ex,"Une erreur est survenue lors de la mise à jour");
            }
        }
        else
            return buildErrorMessage("Cet hopital n'existe pas");
    }
    @GetMapping(path = "isOpen/{id}")
    public ResponseEntity<Boolean> checkOpen(@PathVariable Long id){
        if (service.existById(id)){
            Hopital hopital = service.findById(id);
            return new ResponseEntity<>(this.isOpen(hopital),HttpStatus.OK);
        }else
            return buildErrorMessage("Hopital n'existe pas");
    }
    private boolean isOpen(Hopital hopital){
        LocalTime now = LocalTime.now();
        if (hopital.getHeureOuverture().isBefore(hopital.getHeureFermeture())){
            if (hopital.getHeureOuverture().isBefore(now) && (hopital.getHeureFermeture().isAfter(now)))
                return true;
            else return false;
        }else{
            if(now.isBefore(hopital.getHeureFermeture()))
                return true;
            if (now.isAfter(hopital.getHeureOuverture()))
                return true;
            return false;
        }
    }
}

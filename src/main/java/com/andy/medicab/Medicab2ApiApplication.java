package com.andy.medicab;

import com.andy.medicab.model.Urgences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.andy.medicab.controller.Utils.BASE_URL;

@CrossOrigin({BASE_URL})
@RestController
@SpringBootApplication
public class Medicab2ApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(Medicab2ApiApplication.class, args);
    }
    @RequestMapping("/")
    public String home() {

        return "API MEDICAB v2.0.0";
    }

    @GetMapping(path = "getAllUrgences")
    public ResponseEntity<List<Urgences>>  getAllUrgences(){
        List<Urgences> urgences= new ArrayList<>();
        for (Urgences urgence:
             Urgences.values()) {
            urgences.add(urgence);
        }
        return new ResponseEntity<>(urgences, HttpStatus.OK);
    }

}

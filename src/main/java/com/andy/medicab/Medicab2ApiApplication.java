package com.andy.medicab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.andy.medicab.contoller.Utils.BASE_URL;

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
    @Autowired
    public void check(){
        System.err.println("Chekin err");
        System.out.println("Check fonction");
    }

}

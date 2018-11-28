package com.m2i.flexiflex.controller;
import com.m2i.flexiflex.service.Cryptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class EmailValidationController {
    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(value="key") String key, @RequestParam(value="id") Integer id) {

        String[] PreInscriptionEmails={"flexiflex.emailvalidation@gmail.com","nimportequoi@nimporteou.com"};
        //  Valid URLs:
        //  http://localhost:8080/email_validation?key=7e62acd8a69b4b27bd9b7c6fe1af006eea890e151af4eb29b37802b6b0f07664&id=0
        //  http://localhost:8080/email_validation?key=54eaa93444577f299d5cbdda60d958c4d051541b1d0b9fbc90d5a23066a7e0ad&id=1

        boolean emailvalidation=Cryptor.verifySHA256(PreInscriptionEmails[id],key);
        return new ResponseEntity<>(emailvalidation? "Validation success: " + PreInscriptionEmails[id] : "Validation failure", HttpStatus.OK);
    }
}

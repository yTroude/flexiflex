package com.m2i.flexiflex.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class EmailValidationController {
    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(value="key1") String validationToken, @RequestParam(value="key2") String uuid) {

        //String[] PreInscriptionEmails={"flexiflex.emailvalidation@gmail.com","nimportequoi@nimporteou.com"};
        //  Valid URLs:
        //  http://localhost:8080/email_validation?key=7e62acd8a69b4b27bd9b7c6fe1af006eea890e151af4eb29b37802b6b0f07664&id=0
        //  http://localhost:8080/email_validation?key=54eaa93444577f299d5cbdda60d958c4d051541b1d0b9fbc90d5a23066a7e0ad&id=1

        boolean isEmailValidated=true;//EmailValiationVerify(validationToken,uuid);
        return new ResponseEntity<>(isEmailValidated? "Validation success: " : "Validation failure", HttpStatus.OK);
    }
}

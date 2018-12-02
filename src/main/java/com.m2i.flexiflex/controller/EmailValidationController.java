package com.m2i.flexiflex.controller;
import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.UserRepository;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
public class EmailValidationController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(value="key1") String uuid, @RequestParam(value="key2") String validationToken) {
        //Cherche l'utilisateur correspondant à l'UUID
        UserEntity user = userRepository.findByUuid(uuid);

        if (user !=null){
            //comparaison du token avec celui dans la base
            if((user.getValidationToken().equals(validationToken))){
                //On modifie l'user
                user.setEmailValidation(1);
                //On l'enregistre en base
                if(userRepository.save(user)!=null){
                    return new ResponseEntity<>("Inscription validée",HttpStatus.ACCEPTED);
                }
                else{
                    return new ResponseEntity<>("L'utilisateur n'a pas pu être mis à jour.",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            else {
                return new ResponseEntity<>("Token non valide",HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>("Impossible de trouver l'utilisateur associé.", HttpStatus.BAD_REQUEST);
        }

    }
}

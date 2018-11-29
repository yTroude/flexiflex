package com.m2i.flexiflex.controller;
import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
public class EmailValidationController {
    @RequestMapping(path="/email_validation", method = RequestMethod.GET)
    public ResponseEntity<String> url(@RequestParam(value="key1") String uuid, @RequestParam(value="key2") String validationToken) {
        try {
            Session session = HibernateSession.getSession();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class).add(Property.forName(UserProperties.UUID.get()).eq(uuid));
            List <UserEntity> userEntity= detachedCriteria.getExecutableCriteria(session).list();
            if (!userEntity.isEmpty()){
                UserEntity user=userEntity.get(0);
                if(validationToken.equals(user.getValidationToken())) {
                    try{
                        Transaction transaction = session.beginTransaction();
                        user.setEmailValidation(1);
                        session.save(user);
                        transaction.commit();
                        return new ResponseEntity<>(HttpStatus.ACCEPTED);
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
        catch (Exception e){
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

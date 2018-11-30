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
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class).add(Property.forName(UserProperties.UUID).eq(uuid));
            List <UserEntity> userEntities = detachedCriteria.getExecutableCriteria(session).list();
            System.out.println(">>>>>>>>>>>>>>>>>>> A/" + uuid +"/"+ validationToken + "/" + userEntities.size());
            if (!userEntities.isEmpty()){
                System.out.println(">>>>>>>>>>>>>>>>>>> B" + userEntities.get(0).getValidationToken() + "  " + userEntities.get(0).getUuid());
                UserEntity user = userEntities .get(0);
                System.out.println(">>>>>>>>>>>>>>>>>>> C:");
                if(validationToken.equals(user.getValidationToken())) {
                    try{
                        System.out.println(">>>>>>>>>>>>>>>>>>> D:");
                        Transaction transaction = session.beginTransaction();
                        System.out.println(">>>>>>>>>>>>>>>>>>> E:");
                        user.setEmailValidation(1);
                        System.out.println(">>>>>>>>>>>>>>>>>>> F:");
                        session.save(user);
                        System.out.println(">>>>>>>>>>>>>>>>>>> G:");
                        transaction.commit();
                        //return new ResponseEntity<>(HttpStatus.ACCEPTED);
                        return new ResponseEntity<>("YEAH", HttpStatus.OK);
                    }
                    catch (Exception e) {
                        System.out.println(">>>>>>>>>>>>>>>>>>> 1:"+ e);
                    }
                }
                else return new ResponseEntity<>("Le token n'est pas le bon", HttpStatus.OK);
            }
            else return new ResponseEntity<>("Le user n'existe pas", HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(">>>>>>>>>>>>>>>>>>> 2:"+ e);
        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Fait chier", HttpStatus.OK);
    }
}

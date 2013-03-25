package fr.pilato.demo.sql2nosql.webapp;

import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Person RESTFul API
 * <ul>
 * <li>PUT : create a new person
 * <li>DELETE : remove a person
 * </ul>
 * @author David Pilato
 *
 */
@Controller
@RequestMapping("/1/person")
public class PersonService {
    final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonDao personDao;

    public Person get(String id) {
        // TODO Implement here
        Person person = null;

        return person;
    }
}

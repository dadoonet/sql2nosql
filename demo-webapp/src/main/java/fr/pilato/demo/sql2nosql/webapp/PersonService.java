package fr.pilato.demo.sql2nosql.webapp;

import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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













/*
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody Person get(@PathVariable String id) {
		Person person = personDao.get(Integer.valueOf(id));
		if (logger.isDebugEnabled()) logger.debug("get({})={}", id, person);

		return person;
	}


	@RequestMapping(method = RequestMethod.PUT, value = "/")
    public @ResponseBody String create(@RequestBody String json) {
        if (logger.isDebugEnabled()) logger.debug("create({})", json);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(json, Person.class);
            if (logger.isDebugEnabled()) logger.debug("After Jackson parsing: {}", person);
            if (person != null) {
                person = personDao.save(person);
                if (logger.isDebugEnabled()) logger.debug("Person saved: {}", person);
                return String.valueOf(person.getId());
            }
        } catch (IOException e) {
            logger.error("Error while saving json", e);
        }

        return "";
    }

*/
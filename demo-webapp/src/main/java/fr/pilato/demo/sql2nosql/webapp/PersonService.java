package fr.pilato.demo.sql2nosql.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import fr.pilato.demo.sql2nosql.model.dao.SearchDao;
import fr.pilato.demo.sql2nosql.model.helper.PersonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;


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
    @Autowired
    SearchDao searchDao;

    private static final int numPersons = 1000;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public @ResponseBody Person[] getAll() {
        Collection persons = searchDao.findPersons();
        if (logger.isDebugEnabled()) logger.debug("getAll()={} persons", persons.size());

        return (Person[]) persons.toArray(new Person[]{});
    }

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


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody String update(@PathVariable String id,
                       @RequestBody String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Person person = mapper.readValue(json, Person.class);
            if (logger.isDebugEnabled()) logger.debug("Person: {}", person);
            if (person != null && id != null) {
                if (person.getId() == null) person.setId(Integer.valueOf(id));

                person = personDao.save(person);

                if (logger.isDebugEnabled()) logger.debug("Person updated: {}", person);
            }
        } catch (IOException e) {
            logger.error("Error while updating json", e);
        }

        return "";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody String delete(@PathVariable String id) {
        if (logger.isDebugEnabled()) logger.debug("Person: {}", id);
        if (id != null) {
            personDao.delete(personDao.get(Integer.valueOf(id)));

            if (logger.isDebugEnabled()) logger.debug("Person deleted: {}", id);
        }

        return "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/_search", params = "q")
    public @ResponseBody Person[] search(@RequestParam String q) {
        Collection persons = searchDao.findLikeGoogle(q);
        if (logger.isDebugEnabled()) logger.debug("search({})={} persons", q, persons.size());

        return (Person[]) persons.toArray(new Person[]{});
    }

    @RequestMapping(method = RequestMethod.POST, value = "/_init")
    public @ResponseBody String init() throws IOException {
        return init(numPersons-1);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/_init", params = "size")
    public @ResponseBody String init(@RequestParam(required = false, defaultValue = ""+(numPersons - 1)) Integer size) throws IOException {
        if (logger.isDebugEnabled()) logger.debug("Initializing database for {} persons", size);

        Person joe = PersonGenerator.personGenerator("Joe Smith");
        personDao.save(joe);

        Person john = PersonGenerator.personGenerator("John Wilson");
        personDao.save(john);

        // We generate numPersons persons
        for (int i = 0; i < size - 1; i++) {
            personDao.save(PersonGenerator.personGenerator());

        }
        return "";
    }


}

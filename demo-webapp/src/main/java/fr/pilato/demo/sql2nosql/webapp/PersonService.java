package fr.pilato.demo.sql2nosql.webapp;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.helper.PersonGenerator;
import fr.pilato.demo.sql2nosql.webapp.util.ConnectionManager;
import fr.pilato.demo.sql2nosql.webapp.util.KeyUtil;
import fr.pilato.demo.sql2nosql.webapp.util.ViewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Person RESTFul API
 * <ul>
 * <li>PUT : create a new person
 * <li>DELETE : remove a person
 * </ul>
 * @author David Pilato
 */
@Controller
@RequestMapping("/1/person")
public class PersonService {

    public final static String KEY_PERSON_PREFIX = "person";
    public final static String PERSON_DSGN_DOC = "person_view";
    public final static String PERSON_BY_NAME_VIEW = "by_name";
    public final static String LAST_CHAR = "\\uefff";

    final Logger logger = LoggerFactory.getLogger(PersonService.class);

    CouchbaseClient client = ConnectionManager.getInstance();
    ObjectMapper mapper = new ObjectMapper();


    private static final int numPersons = 1000;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public @ResponseBody  ArrayList getAll() {
        return search(null);
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody Person get(@PathVariable String id) {
        Person person = personDao.get(Integer.valueOf(id));
        if (logger.isDebugEnabled()) logger.debug("get({})={}", id, person);

        return person;
    }
     */

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public
    @ResponseBody
    String get(@PathVariable String id) {
        String person = (String)client.get( KeyUtil.getKey(KEY_PERSON_PREFIX, id) );
        if (logger.isDebugEnabled()) logger.debug("get({})={}", id, person);
        return person;
    }

    /*
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

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public
    @ResponseBody
    String create(@RequestBody String json) {
        if (logger.isDebugEnabled()) logger.debug("create({})", json);
        String key = KeyUtil.nextValue(KEY_PERSON_PREFIX);
        client.set(key, 0, json);
        return key;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public
    @ResponseBody
    String update(@PathVariable String id,
                  @RequestBody String json) {
        if (logger.isDebugEnabled()) logger.debug("update({})", json);
        client.replace(KeyUtil.getKey(KEY_PERSON_PREFIX, id), 0, json);
        return "";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public
    @ResponseBody
    String delete(@PathVariable String id) {
        if (logger.isDebugEnabled()) logger.debug("Person: {}", id);
        if (id != null) {
            client.delete(KeyUtil.getKey(KEY_PERSON_PREFIX, id));
            if (logger.isDebugEnabled()) logger.debug("Person deleted: {}", id);
        }
        return "";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/_search")
    public @ResponseBody  ArrayList<HashMap<String, String>>  search() {
        return search(null);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/_search", params = "q")
    public @ResponseBody  ArrayList<HashMap<String, String>>  search(@RequestParam String q) {
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        try {
            View view = client.getView(PERSON_DSGN_DOC, PERSON_BY_NAME_VIEW);
            Query query = new Query();
            query.setIncludeDocs(true);
            if (q != null) {
              query.setRange(q, q + LAST_CHAR);
            }

            query.setLimit(10);

            ViewResponse viewResponse = client.query(view, query);
            for (ViewRow row : viewResponse) {

                HashMap<String, String> parsedDoc = mapper.readValue( (String)row.getDocument(), HashMap.class )  ;

                //HashMap<String, String> parsedDoc = gson.fromJson( (String)row.getDocument(), HashMap.class);
                result.add( parsedDoc  );
            }

        } catch (InvalidViewException e) {
            logger.info("View does now exist... creating it");
            ViewUtil.createViews();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;    
    }

    @RequestMapping(method = RequestMethod.POST, value = "/_init")
    public @ResponseBody String init() throws IOException {
        return init(numPersons-1);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/_init", params = "size")
    public @ResponseBody String init(@RequestParam(required = false, defaultValue = ""+(numPersons - 1)) Integer size) throws IOException {
        if (logger.isDebugEnabled()) logger.debug("Initializing database for {} persons", size);

        Person joe = PersonGenerator.personGenerator("Joe Smith");
        sendToCouchbase(joe);

        Person john = PersonGenerator.personGenerator("John Wilson");
        sendToCouchbase(john);

        // We generate numPersons persons
        for (int i = 0; i < size - 1; i++) {
            sendToCouchbase(PersonGenerator.personGenerator());
        }

        // create the view
        try {
            client.getView(PERSON_DSGN_DOC, PERSON_BY_NAME_VIEW);
        }  catch (InvalidViewException e) {
            ViewUtil.createViews();
        }
        return "";
    }

    private void sendToCouchbase(Person person) throws JsonProcessingException {
        String key = KeyUtil.nextValue(KEY_PERSON_PREFIX);
        int internalId = Integer.parseInt(key.substring(key.indexOf(KeyUtil.KEY_SEPARATOR) + 1));
        person.setId(internalId);
        String json = mapper.writeValueAsString(person);
        client.set(key, 0, json);
    }

}

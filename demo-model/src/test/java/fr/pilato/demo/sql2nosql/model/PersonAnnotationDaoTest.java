/*
 * Licensed to Tugdual Grall and David Pilato (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.pilato.demo.sql2nosql.model;

import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import fr.pilato.demo.sql2nosql.model.dao.SearchDao;
import fr.pilato.demo.sql2nosql.model.helper.PersonGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests Person DAO.
 * 
 * @author David Winterfeldt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PersonAnnotationDaoTest {

    final Logger logger = LoggerFactory.getLogger(PersonAnnotationDaoTest.class);
    final int numPersons = 100;

    @Autowired
    protected PersonDao personDao = null;

    @Autowired
    protected SearchDao searchDao = null;

    /**
     * We want to inject some beans before starting tests
     */
    @Before
    public void setup() throws IOException {
        // We have to delete everything before each test
        Collection<Person> lPersons = searchDao.findPersons();
        if (lPersons != null && !lPersons.isEmpty()) {
            personDao.deleteAll(lPersons);
        }

        Person joe = PersonGenerator.personGenerator("Joe Smith");
        personDao.save(joe);

        Person john = PersonGenerator.personGenerator("John Wilson");
        personDao.save(john);

        // We generate numPersons persons
        for (int i = 0; i < numPersons; i++) {
            personDao.save(PersonGenerator.personGenerator());

        }
    }

    @Test
    public void testHibernateTemplate() throws SQLException {
        assertNotNull("Search DAO is null.", searchDao);
        
        Collection<Person> lPersons = searchDao.findPersons();

        int expected = numPersons + 2;
        
        assertNotNull("Person list is null.", lPersons);
        assertEquals("Number of persons should be " + expected + ".", expected, lPersons.size());
        
        Integer firstId = new Integer(1);
        Integer secondId = new Integer(2);
        
        for (Person person : lPersons) {
            assertNotNull("Person is null.", person);
            
            if (firstId.equals(person.getId())) {                
                String name = "Joe Smith";
                
                int expectedAddresses = 1;

            	assertEquals("Person name should be " + name + ".", name, person.getName());
            }

            if (secondId.equals(person.getId())) {                
                String name = "John Wilson";
                
                int expectedAddresses = 2;

            	assertEquals("Person name should be " + name + ".", name, person.getName());
            }

            logger.debug(person.toString());
        }
    }

    @Test
    public void testFindLikeGoogle_CaseSensitiveName() throws SQLException {
        findLikeGoogleTestHelper("Smith", 1);
    }

    @Test
    public void testFindLikeGoogle_CaseSensitiveFullName() throws SQLException {
        findLikeGoogleTestHelper("Joe Smith", 1);
    }

    @Test
    public void testFindLikeGoogle_FullName() throws SQLException {
        findLikeGoogleTestHelper("JOE smith", 1);
    }

    @Test
    public void testFindLikeGoogle_Name() throws SQLException {
        findLikeGoogleTestHelper("smith", 1);
    }

    @Test
    public void testFindLikeGoogle_LastAndFirstDoesNotMatch() throws SQLException {
        findLikeGoogleTestHelper("smith joe", 0);
    }

    private void findLikeGoogleTestHelper(String query, int expected) throws SQLException {
        assertNotNull("Search DAO is null.", searchDao);

        Collection<Person> lPersons = searchDao.findLikeGoogle(query);

        assertNotNull("Person list is null.", lPersons);
        assertEquals("Number of persons should be " + expected + ".", expected, lPersons.size());
    }
}

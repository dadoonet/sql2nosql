/*
 * Licensed to Tugdual Grall and David Pilato (the "Author") under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package fr.pilato.demo.sql2nosql.webapp.util;


import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import fr.pilato.demo.sql2nosql.model.dao.SearchDao;
import fr.pilato.demo.sql2nosql.model.helper.PersonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContext;


//@Component
public class PersonService {
    final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Inject
    PersonDao personDao;
    @Inject
    SearchDao searchDao;

    int numPersons = 200;

    public void init() throws Exception {
        if (logger.isDebugEnabled()) logger.debug("Initializing database");

        Person joe = PersonGenerator.personGenerator("Joe Smith");
        personDao.save(joe);

        Person john = PersonGenerator.personGenerator("John Wilson");
        personDao.save(john);

        // We generate numPersons persons
        for (int i = 0; i < numPersons; i++) {
            personDao.save(PersonGenerator.personGenerator());

        }
    }


    public void configure(ServletContext context) {
        // TODO : fix this to use Spring properly and @Inject everywhere
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        try {
            personDao =   (PersonDao)wac.getBean("personDao") ;
            searchDao =   (SearchDao)wac.getBean("searchDao") ;
            this.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

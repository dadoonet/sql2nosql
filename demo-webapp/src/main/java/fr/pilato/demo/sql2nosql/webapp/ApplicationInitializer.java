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

package fr.pilato.demo.sql2nosql.webapp;

import fr.pilato.demo.sql2nosql.webapp.util.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializer implements ServletContextListener {
    final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info(" Application Initialization : Initialization");
        PersonService personService = new PersonService();
        personService.configure( servletContextEvent.getServletContext() );
        try {
            personService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info(" Application Initialization : Destroy");
    }
}



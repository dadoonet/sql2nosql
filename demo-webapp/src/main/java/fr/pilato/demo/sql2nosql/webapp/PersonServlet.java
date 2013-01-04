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

import fr.pilato.demo.sql2nosql.model.bean.Person;
import fr.pilato.demo.sql2nosql.model.dao.PersonDao;
import fr.pilato.demo.sql2nosql.model.dao.SearchDao;
import fr.pilato.demo.sql2nosql.webapp.util.UserMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PersonServlet extends HttpServlet {

    final Logger logger = LoggerFactory.getLogger(PersonServlet.class);


    //TODO : INject
    PersonDao personDao;
    SearchDao searchDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);


        // TODO : fix this to use Spring properly and @Inject everywhere
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        try {
            personDao =   (PersonDao)wac.getBean("personDao") ;
            searchDao =   (SearchDao)wac.getBean("searchDao") ;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        String msg = request.getParameter("msg");
        // check if we need to show a message to the user
        if ( msg != null) {
            String userMessage = UserMessages.getMessage(Integer.parseInt(msg));
            request.setAttribute( "msg", userMessage );
        }

        if (id != null) {
            // TODO : Check error
            Integer idInt =  Integer.parseInt(id);
            Person person = personDao.get(idInt);
            request.setAttribute( "person", person );
        }
        request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // if the an ID is present it is an update it not it's a insert
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String action = request.getParameter("action");

        Person person = null;

        if (id != null) {
            Integer idInt =  Integer.parseInt(id);
            person = personDao.get(idInt);

            if (action != null && action.equalsIgnoreCase("delete")) {

                personDao.delete( person );
                response.sendRedirect( request.getContextPath() +"/home?msg=" + UserMessages.DATA_DELETED.getCode()  );
                return;
            }

        } else {
            person = new Person();
        }

        person.setName( name );

        person = personDao.save( person );
        response.sendRedirect( request.getContextPath() + request.getServletPath() +"?msg=" + UserMessages.DATA_SAVED.getCode() + "&id="+ person.getId()  );

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}

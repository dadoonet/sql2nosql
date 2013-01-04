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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Configurable(dependencyCheck=true)
public class HomeServlet extends HttpServlet {

    final Logger logger = LoggerFactory.getLogger(HomeServlet.class);

    //TODO : Inject
    PersonDao personDao;
    SearchDao searchDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        logger.info("Initialize context for servlet");


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

        String msg = request.getParameter("msg");
        // check if we need to show a message to the user
        if ( msg != null) {
            String userMessage = UserMessages.getMessage(Integer.parseInt(msg));
            request.setAttribute( "msg", userMessage );
        }

        String query = request.getParameter("query");
        if ( query == null) query = "";

        Collection<Person> persons = searchDao.findLikeGoogle(query);
        request.setAttribute("query", query);
        request.setAttribute("persons", persons);
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}

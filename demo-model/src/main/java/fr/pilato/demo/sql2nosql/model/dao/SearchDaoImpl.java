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

package fr.pilato.demo.sql2nosql.model.dao;


import fr.pilato.demo.sql2nosql.model.bean.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


/**
 * Person DAO implementation.
 * 
 * @author David Winterfeldt
 */
@Repository
@Transactional(readOnly = true)
public class SearchDaoImpl implements SearchDao {

    protected HibernateTemplate template = null;

    /**
     * Sets Hibernate session factory.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }
    
    /**
     * Find persons.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons() throws DataAccessException {
        return template.find("from Person");
    }
    
    /**
     * Find persons by name.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersonsByName(String name) throws DataAccessException {
        return template.find("from Person p where p.name = ?", name);
    }

    /**
     * Find persons by any column (like full text).
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findLikeGoogle(String query) throws DataAccessException {
        String toLikeQuery = "%" + query + "%";
        Session session = SessionFactoryUtils.getNewSession(template.getSessionFactory());
        Criteria crit = session.createCriteria(Person.class);
        crit.add(Restrictions.ilike("name", toLikeQuery));
        List results = crit.list();

        return results;
    }
}

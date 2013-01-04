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
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Search for Person DAO interface.
 */
public interface SearchDao {

    /**
     * Find persons.
     */
    public Collection<Person> findPersons() throws DataAccessException;
    
    /**
     * Find persons by name.
     */
    public Collection<Person> findPersonsByName(String name) throws DataAccessException;

    /**
     * Find persons with a google like search.
     */
    public Collection<Person> findLikeGoogle(String query) throws DataAccessException;
}

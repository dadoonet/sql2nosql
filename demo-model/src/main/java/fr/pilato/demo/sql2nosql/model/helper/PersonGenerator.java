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

package fr.pilato.demo.sql2nosql.model.helper;


import fr.pilato.demo.sql2nosql.model.bean.Address;
import fr.pilato.demo.sql2nosql.model.bean.Meta;
import fr.pilato.demo.sql2nosql.model.bean.Person;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class PersonGenerator {
    final static Logger logger = LoggerFactory.getLogger(PersonGenerator.class);

    public static ArrayList<String> names;

    static {
        try {
            if (logger.isDebugEnabled()) logger.debug("Generating names from CSV file...");
            PersonGenerator.names = CsvReader.readAsStrings("/prenoms.csv");
        } catch (IOException e) {
            logger.error("Can not generate names from CSV", e);
        }
    }

    public static Person personGenerator(String name) throws IOException {
        Person person = new Person();
        person.setName(name);
        person.setDateOfBirth(buildBirthDate());
        person.setMeta(buildMeta());
        person.setAddress(buildAddress());

        return person;
    }

    public static Person personGenerator() throws IOException {
        Person person = new Person();
        buildGender(person);
        person.setDateOfBirth(buildBirthDate());
        person.setMeta(buildMeta());
        person.setAddress(buildAddress());

        return person;
    }

    private static Meta buildMeta() {
        Meta meta = new Meta();
        int nbMeta = numberGenerator(0, 6);

        for (int i = 0; i < nbMeta; i++) {
            int nbConsult = numberGenerator(30, 2000);
            int typeMeta = numberGenerator(i, 11);
            switch (typeMeta) {
                case 0:
                    meta.setShoes(nbConsult);
                    break;
                case 1:
                    meta.setToys(nbConsult);
                    break;
                case 2:
                    meta.setFashion(nbConsult);
                    break;
                case 3:
                    meta.setMusic(nbConsult);
                    break;
                case 4:
                    meta.setGarden(nbConsult);
                    break;
                case 5:
                    meta.setElectronic(nbConsult);
                    break;
                case 6:
                    meta.setHifi(nbConsult);
                    break;
                case 7:
                    // meta.setMaison(nbConsult);
                    break;
                case 8:
                    meta.setCars(nbConsult);
                    break;
                case 9:
                    meta.setFood(nbConsult);
                    break;
                default:
                    break;
            }


        }

        return meta;

    }

    private static Date buildBirthDate() {
        String birthDate = "" + numberGenerator(1940, 70) + "-" + numberGenerator(1, 12) + "-" + numberGenerator(1, 28);
        Date date = null;
        try {
            date = DateUtils.parseDate(birthDate, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
        }
        return date;
    }

    private static void buildGender(Person person) throws IOException {
        int pos = numberGenerator(0, names.size());

        String line = names.get(pos);
        ArrayList<String> temp =  CsvReader.extractFromCommas(line);
        person.setName(temp.get(0) + " " + CsvReader.extractFromCommas(
                names.get(numberGenerator(0, names.size()))).get(0));
        person.setGender(temp.get(1));
    }

    private static Address buildAddress() throws IOException {
        Address address = new Address();
        generateCountry(address);
        Long result = Math.round(Math.random() * 2);

        if ("FR".equals(address.getCountrycode())) {
            switch (result.intValue()) {
                case 0:
                    address.setCity("Paris");
                    address.setZipcode("75000");
                    break;
                case 1:
                    address.setCity("Nantes");
                    address.setZipcode("44000");
                    break;
                case 2:
                    address.setCity("Cergy");
                    address.setZipcode("95000");
                    break;
                default:
                    break;
            }
        }

        if ("GB".equals(address.getCountrycode())) {
            switch (result.intValue()) {
                case 0:
                    address.setCity("London");
                    address.setZipcode("98888");
                    break;
                case 1:
                    address.setCity("Plymouth");
                    address.setZipcode("5226");
                    break;
                case 2:
                    address.setCity("Liverpool");
                    address.setZipcode("86767");
                    break;
                default:
                    break;
            }
        }

        if ("DE".equals(address.getCountrycode())) {
            switch (result.intValue()) {
                case 0:
                    address.setCity("Berlin");
                    address.setZipcode("9998");
                    break;
                case 1:
                    address.setCity("Bonn");
                    address.setZipcode("0099");
                    break;
                case 2:
                    address.setCity("Munich");
                    address.setZipcode("45445");
                    break;
                default:
                    break;
            }
        }

        if ("ES".equals(address.getCountrycode())) {
            switch (result.intValue()) {
                case 0:
                    address.setCity("Madrid");
                    address.setZipcode("52525");
                    break;
                case 1:
                    address.setCity("Barcelona");
                    address.setZipcode("9101");
                    break;
                case 2:
                    address.setCity("Pampelune");
                    address.setZipcode("7811");
                    break;
                default:
                    break;
            }
        }

        return address;
    }

    private static String generateCountry(Address address) {

        Long result = Math.round(Math.random() * 3);

        switch (result.intValue()) {
            case 0:
                address.setCountry("France");
                address.setCountrycode("FR");
                break;
            case 1:
                address.setCountry("Germany");
                address.setCountrycode("DE");
                break;
            case 2:
                address.setCountry("England");
                address.setCountrycode("GB");
                break;
            case 3:
                address.setCountry("Spain");
                address.setCountrycode("ES");
                break;
            default:
                break;
        }

        return null;
    }

    private static int numberGenerator(int min, int range) {
        return (int) Math.floor(Math.random()*range+min);
    }
}

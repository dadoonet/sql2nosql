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

package fr.pilato.demo.sql2nosql.model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * We define here marketing meta data:
 * Number of clicks on each segment
 */
@Entity
public class Meta {
    private Integer id = null;

    private Integer cars;
    private Integer shoes;
    private Integer toys;
    private Integer fashion;
    private Integer music;
    private Integer garden;
    private Integer electronic;
    private Integer hifi;
    private Integer food;
//    private Integer home;

    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

    public Integer getShoes() {
        return shoes;
    }

    public void setShoes(Integer shoes) {
        this.shoes = shoes;
    }

    public Integer getToys() {
        return toys;
    }

    public void setToys(Integer toys) {
        this.toys = toys;
    }

    public Integer getFashion() {
        return fashion;
    }

    public void setFashion(Integer fashion) {
        this.fashion = fashion;
    }

    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        this.music = music;
    }

    public Integer getGarden() {
        return garden;
    }

    public void setGarden(Integer garden) {
        this.garden = garden;
    }

    public Integer getElectronic() {
        return electronic;
    }

    public void setElectronic(Integer electronic) {
        this.electronic = electronic;
    }

    public Integer getHifi() {
        return hifi;
    }

    public void setHifi(Integer hifi) {
        this.hifi = hifi;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

//    public Integer getHome() {
//        return home;
//    }
//
//    public void setHome(Integer home) {
//        this.home = home;
//    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  cars=" + cars);
        sb.append("  shoes=" + shoes);
        sb.append("  toys=" + toys);
        sb.append("  fashion=" + fashion);
        sb.append("  music=" + music);
        sb.append("  garden=" + garden);
        sb.append("  electronic=" + electronic);
        sb.append("  hifi=" + hifi);
        sb.append("  food=" + food);
//    sb.append("  home=" + home);

        return sb.toString();
    }

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Meta other = (Meta) obj;

        if (cars != other.cars) return false;
        if (shoes != other.shoes) return false;
        if (toys != other.toys) return false;
        if (fashion != other.fashion) return false;
        if (music != other.music) return false;
        if (garden != other.garden) return false;
        if (electronic != other.electronic) return false;
        if (hifi != other.hifi) return false;
        if (food != other.food) return false;
//        if (home != other.home) return false;
        return true;
    }



}

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

    private Integer voiture;
    private Integer chaussures;
    private Integer jouets;
    private Integer mode;
    private Integer musique;
    private Integer jardin;
    private Integer electronique;
    private Integer hifi;
    private Integer food;
//    private Integer maison;

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

    public Integer getVoiture() {
        return voiture;
    }

    public void setVoiture(Integer voiture) {
        this.voiture = voiture;
    }

    public Integer getChaussures() {
        return chaussures;
    }

    public void setChaussures(Integer chaussures) {
        this.chaussures = chaussures;
    }

    public Integer getJouets() {
        return jouets;
    }

    public void setJouets(Integer jouets) {
        this.jouets = jouets;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getMusique() {
        return musique;
    }

    public void setMusique(Integer musique) {
        this.musique = musique;
    }

    public Integer getJardin() {
        return jardin;
    }

    public void setJardin(Integer jardin) {
        this.jardin = jardin;
    }

    public Integer getElectronique() {
        return electronique;
    }

    public void setElectronique(Integer electronique) {
        this.electronique = electronique;
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

//    public Integer getMaison() {
//        return maison;
//    }
//
//    public void setMaison(Integer maison) {
//        this.maison = maison;
//    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  voiture=" + voiture);
        sb.append("  chaussures=" + chaussures);
        sb.append("  jouets=" + jouets);
        sb.append("  mode=" + mode);
        sb.append("  musique=" + musique);
        sb.append("  jardin=" + jardin);
        sb.append("  electronique=" + electronique);
        sb.append("  hifi=" + hifi);
        sb.append("  food=" + food);
//    sb.append("  maison=" + maison);

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

        if (voiture != other.voiture) return false;
        if (chaussures != other.chaussures) return false;
        if (jouets != other.jouets) return false;
        if (mode != other.mode) return false;
        if (musique != other.musique) return false;
        if (jardin != other.jardin) return false;
        if (electronique != other.electronique) return false;
        if (hifi != other.hifi) return false;
        if (food != other.food) return false;
//        if (maison != other.maison) return false;
        return true;
    }



}

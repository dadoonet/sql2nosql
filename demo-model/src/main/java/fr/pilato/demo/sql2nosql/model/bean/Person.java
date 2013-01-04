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

import javax.persistence.*;
import java.util.Date;


/**
 * We want a bean to simulate :
 * {
 "nom" : "Pilato David",
 "dateOfBirth" : "1971-12-26",
 "sexe" : "homme",
 "mobile" : {
 "os" : "IOS",
 "version" : "5.0.1"
 },
 "meta" : {
 "chaussures" : 10,
 "hifi" : 2700,
 "bricolage" : 1400
 }
 }
 */
@Entity
public class Person {

    private Integer id = null;
    private String name = null;
    private Date dateOfBirth = null;
    private String sexe = null;
    private Date created = null;

    private Meta meta;
    private Address address;


    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Gets name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date created.
     */
	public Date getCreated() {
		return created;
	}

    /**
     * Sets date created.
     */
	public void setCreated(Date created) {
		this.created = created;
	}

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the object. 
     */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  name=" + name);
        sb.append("  dateOfBirth=" + dateOfBirth);

        sb.append("  meta=[");
        
        if (meta != null) {
            sb.append(meta.toString());
        }
        
        sb.append("]");

        sb.append("  address=[");

        if (address != null) {
            sb.append(address.toString());
        }

        sb.append("]");

        sb.append("  created=" + created);
        
        return sb.toString();
    }

    /**
     * Returns a hash code value for the object.
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result
                + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		final Person other = (Person) obj;
        if (dateOfBirth == null) {
            if (other.dateOfBirth != null)
                return false;
        } else if (!dateOfBirth.equals(other.dateOfBirth))
            return false;
        if (meta == null) {
			if (other.meta != null)
				return false;
		} else if (!meta.equals(other.meta))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

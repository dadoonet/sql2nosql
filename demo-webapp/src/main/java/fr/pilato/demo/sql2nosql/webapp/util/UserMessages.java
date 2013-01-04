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

import java.util.HashMap;

public enum UserMessages {
    DATA_SAVED(0, "Data Saved Successfuly."),
    DATA_DELETED(1, "Data Deleted Successfuly.");

    private final int code;
    private final String description;


    private static HashMap<Integer, UserMessages> codeMap = new HashMap<Integer, UserMessages>();
    static
    {
        for (UserMessages  type : UserMessages.values())
        {
            codeMap.put(type.code, type);
        }
    }


    private UserMessages(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }


    public static String getMessage(int codeValue)
    {
        if ( codeMap.get(codeValue) != null ) {
            return codeMap.get(codeValue).getDescription();
        }
        return null;
    }

}

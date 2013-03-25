package fr.pilato.demo.sql2nosql.webapp.util;


import com.couchbase.client.CouchbaseClient;

public class KeyUtil {

    public final static String KEY_SEPARATOR = ":";

    public static String nextValue(String prefix) {
        CouchbaseClient client = ConnectionManager.getInstance();
        return prefix + KEY_SEPARATOR + client.incr(prefix +"_counter",1,0) ;
    }


    public static String getKey(String prefix, String value) {
        return prefix + KEY_SEPARATOR + value ;
    }

}

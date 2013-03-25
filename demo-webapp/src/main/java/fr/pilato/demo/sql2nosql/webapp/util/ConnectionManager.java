package fr.pilato.demo.sql2nosql.webapp.util;



import com.couchbase.client.CouchbaseClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ConnectionManager handles connecting, disconnecting and managing
 * of the Couchbase connection.
 *
 * To get the connection from a Servlet context, use the getInstance()
 * method.
 */
public class ConnectionManager implements ServletContextListener {

    /**
     * Holds the connected Couchbase instance.
     */
    private static CouchbaseClient client;

    /**
     * The Logger to use.
     */
    private static final Logger logger = Logger.getLogger(
            ConnectionManager.class.getName());

    /**
     * Connect to Couchbase when the Server starts.
     *
     * @param sce the ServletContextEvent (not used here).
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (client == null) {
            initializeConnection();



        }
    }

    /**
     * Disconnect from Couchbase when the Server shuts down.
     *
     * @param sce the ServletContextEvent (not used here).
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO, "Disconnecting from Couchbase Cluster");
        client.shutdown();
    }

    /**
     * Returns the current CouchbaseClient object.
     *
     * @return the current Couchbase connection.
     */
    public static CouchbaseClient getInstance() {
        if (client != null) {
            return client;
        } else {
            initializeConnection();
            return client;
        }


    }


    private static void initializeConnection() {
        logger.log(Level.INFO, "Connecting to Couchbase Cluster");
        ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://127.0.0.1:8091/pools"));
        try {
            client = new CouchbaseClient(nodes, "default", "");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

}


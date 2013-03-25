package fr.pilato.demo.sql2nosql.webapp.util;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.ViewDesign;

public class ViewUtil {

    /**
     * Create the views for the application
     */
    public static void createViews() {
        String mapFunction =
                "function (doc, meta) {\n" +
                "  if (doc.name) {\n" +
                "  \temit(doc.name);\n" +
                "  }\n" +
                "}";
        ViewDesign viewDesign = new ViewDesign("by_name", mapFunction);
        DesignDocument designDocument = new DesignDocument("person_view");
        designDocument.setView(viewDesign);
        CouchbaseClient client = ConnectionManager.getInstance();
        client.createDesignDoc( designDocument );
    }

}

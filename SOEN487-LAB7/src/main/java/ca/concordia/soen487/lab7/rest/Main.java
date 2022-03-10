package ca.concordia.soen487.lab7.rest;

import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.grizzly.ssl.SSLContextConfigurator;
//import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
//    private static final String KEYSTORE_LOC = "SOEN487-LAB7/localhost.jks";
//    private static final String KEYSTORE_PASS = "changeit";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in ca.concordia package
        final ResourceConfig rc = new ResourceConfig().packages("ca.concordia");
        rc.register(MultiPartFeature.class);
//
//        SSLContextConfigurator sslCon = new SSLContextConfigurator();
//
//        sslCon.setKeyStoreFile(KEYSTORE_LOC);
//        sslCon.setKeyPass(KEYSTORE_PASS);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

//        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc, true, new SSLEngineConfigurator(sslCon).setClientMode(false).setNeedClientAuth(false));
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.printf("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...%n", BASE_URI);
        System.in.read();
        server.shutdown();
    }
}


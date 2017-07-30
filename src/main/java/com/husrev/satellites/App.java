package com.husrev.satellites;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {
    public static void main(String[] args) throws Exception {
       
    	//BasicConfigurator.configure();
    	
    	
   	 	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
         context.setContextPath("/");

         Server jettyServer = new Server(2532);
         jettyServer.setHandler(context);

         ServletHolder jerseyServlet = context.addServlet(
              org.glassfish.jersey.servlet.ServletContainer.class, "/*");
         jerseyServlet.setInitOrder(0);

         // Tells the Jersey Servlet which REST service/class to load.
         jerseyServlet.setInitParameter(
            "jersey.config.server.provider.classnames",
            Resources.class.getCanonicalName());

         try {
             jettyServer.start();
             jettyServer.join();
         } catch (Exception e){
             System.out.println("er : " + e.toString());
             jettyServer.stop();
             jettyServer.destroy();
         }
	
    }
}

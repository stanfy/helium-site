package com.stanfy.helium.server;


import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/** DataRetrieveServletListener, a ServletContextListener, is setup in web.xml. **/
public class DataRetrieveServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
      // This will be invoked as part of a warmup request,
      // or the first user request if no warmup request.
      ObjectifyService.register(Data.class);
    }

    // Never invokes by App Engine.
    public void contextDestroyed(ServletContextEvent event) { }

}

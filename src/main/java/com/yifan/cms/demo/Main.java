package com.yifan.cms.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Date 2016/5/17 12:29
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(8090);
        WebAppContext context = new WebAppContext();
        context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("./src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.viki;

import java.util.concurrent.locks.LockSupport;

public class UserInterface {


    public void start() throws InterruptedException {
        System.out.println("Server starting...");
        Thread.sleep(5000);
        new Server().handleRequest();

    }


}

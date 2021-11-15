package org.viki;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 5000;
   private ServerSocket server;

    public void handleRequest() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started. " + "\n" + "Listening for requests...");
            System.out.println();

            while (true) {

                Socket clientServerConnection = server.accept();
                System.out.println("New client connected"+ " "
                        + clientServerConnection.getInetAddress()
                        .getHostAddress());

                new Thread(new ClientRequestHandler(clientServerConnection)).start();

            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(server !=null){
                    server.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}

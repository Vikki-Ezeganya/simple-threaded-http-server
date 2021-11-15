package org.viki;

import java.io.*;
import java.net.Socket;

public class ClientRequestHandler implements Runnable {

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {



            processClientRequest();

        }

        catch (IOException exception) {
            exception.printStackTrace();
        }

        finally {
            try {
                clientSocket.close();
            }

            catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }

    private void processClientRequest() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

         readFromFile(br.readLine().split(" ")[1]);

    }


    private void readFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        OutputStream clientOutputStream = null;


        try {


            switch (filePath) {

                case "/json": {
                    // get me a json file from the file system
                    fileInputStream = new FileInputStream("podE.json");
                    clientOutputStream = clientSocket.getOutputStream();
                    clientOutputStream.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    clientOutputStream.write(("\r\n").getBytes());
                    clientOutputStream.write(fileInputStream.readAllBytes());
                    clientOutputStream .flush();
                    break;
                }
                case "/vikki": {
                    // get me a picture of me
                    fileInputStream = new FileInputStream("myself.png");
                    clientOutputStream = clientSocket.getOutputStream();
                    clientOutputStream.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    clientOutputStream.write(("\r\n").getBytes());
                    clientOutputStream.write(fileInputStream.readAllBytes());
                    break;
                }
                default: {
                    // display an html
                    fileInputStream = new FileInputStream("portfolio.html");
                    clientOutputStream = clientSocket.getOutputStream();
                    clientOutputStream .write(("HTTP/1.1 200 OK\r\n").getBytes());
                    clientOutputStream .write(("\r\n").getBytes());
                    clientOutputStream .write(fileInputStream.readAllBytes());
                    clientOutputStream .flush();
                }
            }
        }

    catch(IOException e){
            e.printStackTrace();
        }

        finally {


            try{

            if(fileInputStream!=null){
                fileInputStream.close();
            }
            if (clientOutputStream!=null){
                clientOutputStream.close();
            }

                clientSocket.close();

            }
            catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }


}

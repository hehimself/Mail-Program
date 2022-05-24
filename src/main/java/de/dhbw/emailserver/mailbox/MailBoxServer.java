package de.dhbw.emailserver.mailbox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MailBoxServer implements Runnable {
    private final int port;

    public MailBoxServer(int p_port){
        port = p_port;
    }

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(port);

            while (!Thread.currentThread().isInterrupted()){
                Socket l_client = socket.accept();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}

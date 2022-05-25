package de.dhbw.emailserver.mailbox;

import de.dhbw.emailserver.util.ProtocolHelper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MailBoxServer implements Runnable {
    private final int port;
    private MailBoxManager mailBoxManager;


    public MailBoxServer(int p_port, MailBoxManager p_manager){
        port = p_port;
        mailBoxManager = p_manager;
    }

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(port);

            while (!Thread.currentThread().isInterrupted()){
                Socket l_client = socket.accept();

                Reader l_in = new InputStreamReader(l_client.getInputStream());
                Writer l_out = new OutputStreamWriter(l_client.getOutputStream());

                String l_line = ProtocolHelper.readProtocolLine(l_in);
                String[] l_cmd = ProtocolHelper.parseProtocolLine(l_line);

                if ("LIST".equals(l_cmd[0])){
                    String l_response = ProtocolHelper.createLineResponse("200", "ok");
                    l_out.write(l_response);
                    l_out.flush();
                }
                else if ("QUIT".equals(l_cmd[0])) {
                    String l_response = ProtocolHelper.createLineResponse("200", "bye");
                    l_out.write(l_response);
                    l_out.flush();
                    l_client.close();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        File l_msgboxesRoot = new File ("./mailboxes");

        MailBoxManager l_manager = new MailBoxManager(l_msgboxesRoot);
        l_manager.loadMailboxes();

        MailBoxServer l_server = new MailBoxServer(1234, l_manager);
        new Thread(l_server).start();

    }
}

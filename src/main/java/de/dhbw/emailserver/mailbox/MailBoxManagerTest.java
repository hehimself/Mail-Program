package de.dhbw.emailserver.mailbox;

import java.io.File;
import java.io.IOException;

public class MailBoxManagerTest {
    public static void main(String[] args) throws MailboxCreationException, MailBoxDeletionException, MailboxNotfoundException {
        File root = new File("./mailboxes");
        MailBoxManager m1 = new MailBoxManager(root);
        m1.loadMailboxes();
        m1.createMailbox("test@example.de", "1245");
        m1.deleteMailbox("test@example.de");
        System.out.println("existiert test@example.de ?: " + m1.hasMailBox("test@example.de"));
    }
}

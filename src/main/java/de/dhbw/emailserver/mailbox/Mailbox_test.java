package de.dhbw.emailserver.mailbox;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Mailbox_test {
    public static void main(String[] args) throws Exception {
        File  l_msgboxRoot = new File("./mailboxes/m1");
        File l_metadataFile = new File(l_msgboxRoot, "metadata.properties");
        Properties l_props = new Properties();
        l_props.load(new FileReader(l_metadataFile));
        //l_props.setProperty("owner","michael.schlegel@usu.com");

        Mailbox l_mailbox = new Mailbox(l_msgboxRoot, l_props);
        System.out.println("Owner: " + l_mailbox.getOwner());

        l_mailbox.storeMessage("Meine erste Mail");
        System.out.println("Anzahl: " + l_mailbox.getEmailCount());
        System.out.println("Vorhanden: " + l_mailbox.hasEmail(1));
        System.out.println("Inhalt 1: " + l_mailbox.getEmail(1));
        //Löschen funktioniert irgendwie nicht.
        System.out.println("gelöscht: " + l_mailbox.deleteEmail(1));
    }
}

package de.dhbw.emailserver.mailbox;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MailBoxManager {
    public static final String MAILBOX_INF = "metadata.properties";
    private final File root;
    private final Map<String, Mailbox> mailboxes;
    public MailBoxManager(File p_root){
        root = p_root;
        mailboxes = new HashMap<>();
    }

    public void loadMailboxes() {
        File[] l_folders =  root.listFiles(File::isDirectory);
        for (File folder : Objects.requireNonNull(l_folders)){
            File l_infFile = new File(folder,MAILBOX_INF);
            if (l_infFile.exists()){
                try {
                    Properties l_metadata = new Properties();
                    l_metadata.load(new FileReader(l_infFile));
                    Mailbox l_mailbox = new Mailbox(folder, l_metadata);
                    mailboxes.put(l_mailbox.getOwner(), l_mailbox);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public void deleteMailbox(String p_ownerId) throws MailBoxDeletionException, MailboxNotfoundException{
        Mailbox l_mailbox = mailboxes.get(p_ownerId);
        if (l_mailbox == null){
            throw new MailboxNotfoundException(p_ownerId + "konnte nicht gefunden werdn");
        }
        File l_folder = l_mailbox.getRootFolder();
        File[] l_contentFiles = l_folder.listFiles();
        if (l_contentFiles != null){
            for (File l_file : l_contentFiles){
                if (!l_file.delete()){
                    throw new MailBoxDeletionException("konnte nicht gelöscht werden");
                }

            }
        }
        if (!l_folder.delete()){
            throw new MailBoxDeletionException("konnte nicht gelöscht werden");
        }
        mailboxes.remove(p_ownerId);
    }



    public void createMailbox(String p_email, String p_passwort) throws MailboxCreationException {
        if(mailboxes.containsKey(p_email)){
            throw new MailboxCreationException("Owner existiert bereits");
        }
        File l_boxFolder;
        try {
            l_boxFolder = Files.createTempDirectory(root.toPath(), "box_").toFile();
        } catch (IOException e) {
            throw new MailboxCreationException("kene Berechtigung", e);
        }
        Properties l_metadata = new Properties();
        l_metadata.setProperty("owner", p_email);
        l_metadata.setProperty("password", p_passwort);
        try(Writer l_writer = new FileWriter(new File(l_boxFolder, MAILBOX_INF))){
            l_metadata.store(l_writer, "Mailbox for " + p_email);
        } catch (Exception e){
            throw new MailboxCreationException("Filewriter",e);
        }
        mailboxes.put(p_email, new Mailbox(l_boxFolder,l_metadata));

    }
    public Boolean hasMailBox(String p_ownerId){
        return mailboxes.containsKey(p_ownerId);
    }

    public Mailbox findMailBox(String p_ownerId) throws MailboxNotfoundException {
        if (!hasMailBox(p_ownerId)){
            throw new MailboxNotfoundException("keine Mailbox vorhanden");
        }
        return mailboxes.get(p_ownerId);

    }
}

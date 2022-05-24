package de.dhbw.emailserver.mailbox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class Mailbox {
    private final File root;
    private final Properties metadata;

    public Mailbox(File p_root, Properties p_metadata){
        root = p_root;
        metadata = p_metadata;
    }

    public String getOwner(){
        return metadata.getProperty("owner");
    }

    public void storeMessage(String p_message) throws EMailNotSavedExeption {
        try {
            File l_msgFile = File.createTempFile("msg_",".eml",root);
            try (Writer l_writer = new FileWriter(l_msgFile)){
                l_writer.write(p_message);
            }
        } catch (Exception e) {
            throw new EMailNotSavedExeption("Mail not saved", e);
        }


    }

    public File[] getAllEmails(){
        return Objects.requireNonNull(root.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".eml")));
    }

    public int getEmailCount() {
        return Objects.requireNonNull(root.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".eml"))).length;
    }

    public boolean hasEmail(int p_number) {
        if (p_number < 1){
            return false;
        }
        try {
            getEmail(p_number);
            return true;
        } catch (EMailNotFoundExeption eMailNotFoundExeption) {
            return false;
        }
    }

    public String getEmail(int p_number) throws EMailNotFoundExeption {
        File[] l_emls = root.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".eml"));
        assert l_emls != null;
        sort_mail(l_emls);
        if (p_number > l_emls.length){
            throw new EMailNotFoundExeption("E-Mail kann nicht gefunden werden");
        }
        File l_email = l_emls[p_number-1];
        String content;
        try {
            content = Files.readString(l_email.toPath());
        } catch (IOException e){
            throw new EMailNotFoundExeption("Konnte E-Mail nicht lesen", e);
        }
        return content;
    }

    public boolean deleteEmail(int p_EmailNumber) throws EMailNotFoundExeption {
        File[] l_emls = getAllEmails();
        sort_mail(l_emls);
        if (p_EmailNumber > l_emls.length || p_EmailNumber < 1){
            throw new EMailNotFoundExeption("E-Mail kann nicht gefunden werden");
        }
        File l_email = l_emls[p_EmailNumber - 1];
        if(l_email.delete()){
            return true;
        }
        return false;
    }

    private void sort_mail(File[] l_emls) {
        Arrays.sort(l_emls, (o1, o2) -> {
            long c1 = getFileCreationEpoch(o1);
            long c2 = getFileCreationEpoch(o2);
            return (int) (c1 - c2);
        });
    }

    private long getFileCreationEpoch(File p_file) {
        try {
            BasicFileAttributes l_fileAttributes = Files.readAttributes(p_file.toPath(), BasicFileAttributes.class);
            return l_fileAttributes.creationTime().toInstant().toEpochMilli();
        } catch (IOException ex) {
            throw new RuntimeException(p_file.getAbsolutePath(), ex);
        }
    }

    File getRootFolder() {
        return root;
    }
}



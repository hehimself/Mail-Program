package de.dhbw.emailserver.mailbox;

public class MailBoxExeption extends Exception{
    public MailBoxExeption() {
    }

    public MailBoxExeption(String message) {
        super(message);
    }

    public MailBoxExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public MailBoxExeption(Throwable cause) {
        super(cause);
    }
}

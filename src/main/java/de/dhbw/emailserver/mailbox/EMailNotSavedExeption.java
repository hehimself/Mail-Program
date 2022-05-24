package de.dhbw.emailserver.mailbox;

public class EMailNotSavedExeption extends MailBoxExeption {
    public EMailNotSavedExeption() {
    }

    public EMailNotSavedExeption(String message) {
        super(message);
    }

    public EMailNotSavedExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public EMailNotSavedExeption(Throwable cause) {
        super(cause);
    }

}

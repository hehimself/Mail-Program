package de.dhbw.emailserver.mailbox;

public class MailBoxDeletionException extends Exception {
    public MailBoxDeletionException() {
    }

    public MailBoxDeletionException(String message) {
        super(message);
    }

    public MailBoxDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailBoxDeletionException(Throwable cause) {
        super(cause);
    }
}

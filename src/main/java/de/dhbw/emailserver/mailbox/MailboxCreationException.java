package de.dhbw.emailserver.mailbox;

public class MailboxCreationException extends Exception {
    public MailboxCreationException() {
    }

    public MailboxCreationException(String message) {
        super(message);
    }

    public MailboxCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailboxCreationException(Throwable cause) {
        super(cause);
    }
}

package de.dhbw.emailserver.mailbox;

public class MailboxNotfoundException extends Exception {
    public MailboxNotfoundException() {
    }

    public MailboxNotfoundException(String message) {
        super(message);
    }

    public MailboxNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailboxNotfoundException(Throwable cause) {
        super(cause);
    }
}

package de.dhbw.emailserver.mailbox;

public class EMailNotFoundExeption extends Exception {
    public EMailNotFoundExeption() {
    }

    public EMailNotFoundExeption(String message) {
        super(message);
    }

    public EMailNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public EMailNotFoundExeption(Throwable cause) {
        super(cause);
    }
}

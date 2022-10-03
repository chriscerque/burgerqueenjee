package net.ent.etrs.burgerqueenjee.model.exceptions;

public class ProduitException extends Exception {
    public ProduitException() {
    }

    public ProduitException(String message) {
        super(message);
    }

    public ProduitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProduitException(Throwable cause) {
        super(cause);
    }

    public ProduitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

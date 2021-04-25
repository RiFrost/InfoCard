package databaseConnection;

public class DataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataException() {
    }

    public DataException(Throwable t) {
        super(t);
    }

    public DataException(String msg) {
        super(msg);
    }

}

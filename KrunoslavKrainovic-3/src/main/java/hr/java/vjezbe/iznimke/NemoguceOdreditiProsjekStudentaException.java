package hr.java.vjezbe.iznimke;

public class NemoguceOdreditiProsjekStudentaException extends Exception {

    private static final long serialVersionUID = 503215131547379395L;

    public NemoguceOdreditiProsjekStudentaException() {
    }

    public NemoguceOdreditiProsjekStudentaException(String message) {
        super(message);
    }

    public NemoguceOdreditiProsjekStudentaException(String ime,String prezime,String ispit) {
        super("Student " +  ime + " "+  prezime +" ima negativnu ocjenu na " + ispit + " ispitu!");
    }

    public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
        super(cause);
    }


}

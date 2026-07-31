package br.study.SpringStudy_3.Exception;

public class AutorNotFoundException extends RuntimeException {
    public AutorNotFoundException(Long id) {
        super("Autor with id " + id + " not found");
    }
    public AutorNotFoundException(String message) {
        super(message);
    }
}

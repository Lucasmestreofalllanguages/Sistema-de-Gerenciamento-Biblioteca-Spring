package br.study.SpringStudy_3.Exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("Member with id " + id + " not found");
    }
    public MemberNotFoundException(String message) {
        super(message);
    }
}

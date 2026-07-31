package br.study.SpringStudy_3.Exception;

public class BookAlreadyBorrowed extends RuntimeException {
    public BookAlreadyBorrowed(Long idBook, Long idMember) {
        super("Member with id: " + idMember + " already have a loan with de Book id: " + idBook);
    }
    public BookAlreadyBorrowed(String message) {
        super(message);
    }
}

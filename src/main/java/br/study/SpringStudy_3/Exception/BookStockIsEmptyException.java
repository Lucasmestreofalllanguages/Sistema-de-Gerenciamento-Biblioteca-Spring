package br.study.SpringStudy_3.Exception;

public class BookStockIsEmptyException extends RuntimeException {
    public BookStockIsEmptyException(Long id) {
        super("Book with id: " + id + " stock is empty");
    }

    public BookStockIsEmptyException(String message) {
        super(message);
    }
}

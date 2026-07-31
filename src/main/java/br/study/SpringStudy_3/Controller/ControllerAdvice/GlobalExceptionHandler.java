package br.study.SpringStudy_3.Controller.ControllerAdvice;


import br.study.SpringStudy_3.Exception.*;
import br.study.SpringStudy_3.Exception.ErrorMessages.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<BookErrorMessage> handleBookNotFoundException(BookNotFoundException e){
        BookErrorMessage bookErrorMessage = new BookErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookErrorMessage);
    }

    @ExceptionHandler(AutorNotFoundException.class)
    public ResponseEntity<AutorErrorMessage> handleAutorNotFoundException(AutorNotFoundException e){
        AutorErrorMessage autorErrorMessage = new AutorErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(autorErrorMessage);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<MemberErrorMessage>  handleMemberNotFoundException(MemberNotFoundException e) {
        MemberErrorMessage memberErrorMessage = new MemberErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(memberErrorMessage);
    }

    @ExceptionHandler(BookAlreadyBorrowed.class)
    public ResponseEntity<BookErrorMessage> handleBookAlreadyBorrowed(BookAlreadyBorrowed e){
        BookErrorMessage bookErrorMessage = new BookErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bookErrorMessage);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<LoanNotFoundErrorMessage> handleLoanNotFoundException(LoanNotFoundException e) {
        LoanNotFoundErrorMessage errorMessage = new LoanNotFoundErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }



    @ExceptionHandler(BookStockIsEmptyException.class)
    public ResponseEntity<BookStockEmptyErrorMessage> hanldeBookStockIsEmptyException(BookStockIsEmptyException e) {
        BookStockEmptyErrorMessage errorMessage = new BookStockEmptyErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);

    }

    @ExceptionHandler(MaxLoanLimitReachedException.class)
    public ResponseEntity<MaxLoanLimitErrorMessage> hanldeMaxLoanLimitReachedException(MaxLoanLimitReachedException e) {
        MaxLoanLimitErrorMessage errorMessage = new MaxLoanLimitErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

}

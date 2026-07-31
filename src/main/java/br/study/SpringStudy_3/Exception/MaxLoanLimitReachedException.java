package br.study.SpringStudy_3.Exception;

public class MaxLoanLimitReachedException extends RuntimeException {
    public MaxLoanLimitReachedException(Long id) {
      super("Member with id: " + id + " reached the limit of Loans");
    }
    public MaxLoanLimitReachedException(String message) {
        super(message);
    }
}

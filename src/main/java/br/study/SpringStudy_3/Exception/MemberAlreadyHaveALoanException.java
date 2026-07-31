package br.study.SpringStudy_3.Exception;

public class MemberAlreadyHaveALoanException extends RuntimeException {
    public MemberAlreadyHaveALoanException(Long id) {
        super("Member with id: " + id + " already have a loan");
    }

    public MemberAlreadyHaveALoanException(String message) {
        super(message);
    }
}

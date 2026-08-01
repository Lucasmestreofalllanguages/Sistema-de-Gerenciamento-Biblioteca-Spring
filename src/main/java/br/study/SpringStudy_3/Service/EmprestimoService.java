package br.study.SpringStudy_3.Service;


import br.study.SpringStudy_3.Entity.Emprestimo;
import br.study.SpringStudy_3.Entity.Livro;
import br.study.SpringStudy_3.Entity.Membro;
import br.study.SpringStudy_3.Exception.*;
import br.study.SpringStudy_3.Repository.EmpRepository;
import br.study.SpringStudy_3.Repository.LivroRepository;
import br.study.SpringStudy_3.Repository.MembroRepository;
import br.study.SpringStudy_3.Service.Enum.EmpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmpRepository emprestimoRepository;

    @Transactional
    public Emprestimo toLoan(Long idMember, Long idBook) {
        Livro livro = livroRepository.findById(idBook).orElseThrow(
                () -> new BookNotFoundException(idBook)
        );
        Membro membro = membroRepository.findById(idMember).orElseThrow(
                () -> new MemberNotFoundException(idMember)
        );

        if(emprestimoRepository.existsByLivroAndMembroAndStatus(
                livro,
                membro,
                EmpStatus.EMPRESTADO,
                EmpStatus.ATRASADO
        )) {
            throw new BookAlreadyBorrowed(idBook, idMember);
        }
        if(maxLoans(idMember)) {
            throw new MaxLoanLimitReachedException(idMember);
        }
        if(livro.getEstoque() < 1) {
            throw new BookStockIsEmptyException(idBook);
        }
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setMembro(membro);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setStatus(EmpStatus.EMPRESTADO);
        emprestimoRepository.save(emprestimo);
        livro.setEstoque(livro.getEstoque() - 1);
        livroRepository.save(livro);
        return emprestimo;
    }

    public boolean maxLoans(Long idMember) {
        if(!membroRepository.existsById(idMember)) {
            throw new MemberNotFoundException(idMember);
        }
        return emprestimoRepository.countEmprestimoByMembro_IdAndStatus(idMember, EmpStatus.EMPRESTADO, EmpStatus.ATRASADO) >= 5;
    }

    @Transactional
    public void returnLoan(Long idBook, Long idMember) {
        Livro livro = livroRepository.findById(idBook).orElseThrow(
                () -> new BookNotFoundException(idBook)
        );

        Membro membro = membroRepository.findById(idMember).orElseThrow(
                () -> new MemberNotFoundException(idMember)
        );

        Emprestimo emprestimo = emprestimoRepository.findByMembroAndLivroAndStatus(
                membro,
                livro,
                EmpStatus.EMPRESTADO
        ).orElseThrow(
                () -> new LoanNotFoundException("Member with id: " + idMember +
                        " don't have an active Loan with Book id: " + idBook)
        );

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus(EmpStatus.DEVOLVIDO);
        emprestimoRepository.save(emprestimo);

        livro.setEstoque(livro.getEstoque() + 1);
        livroRepository.save(livro);
    }

    public int checkLateLoans() {
        int modified = 0;

        List<Emprestimo> emprestimosAtrasados = emprestimoRepository.findByStatus(EmpStatus.EMPRESTADO);

        for(Emprestimo emprestimo : emprestimosAtrasados) {
            if(LocalDate.now().isAfter(emprestimo.getDataEmprestimo().plusDays(7))) {
                emprestimo.setStatus(EmpStatus.ATRASADO);
                emprestimoRepository.save(emprestimo);
                modified++;
            }
        }

        return modified;
    }



    public boolean bookHaveLoan(Long idBook) {
        Livro livro = livroRepository.findById(idBook).orElseThrow(
                () -> new BookNotFoundException(idBook)
        );

        Optional<Emprestimo> empOptional = emprestimoRepository.findByLivroAndStatus(
                livro,
                EmpStatus.EMPRESTADO
        );
        return empOptional.isPresent();
    }

    // Completar Metodo
    public boolean memberHaveLoan(Long idMember) {
        Membro membro = membroRepository.findById(idMember).orElseThrow(
                () -> new MemberNotFoundException(idMember)
        );
        return emprestimoRepository.findByMembroAndStatus(membro, EmpStatus.EMPRESTADO).isPresent();
    }
}

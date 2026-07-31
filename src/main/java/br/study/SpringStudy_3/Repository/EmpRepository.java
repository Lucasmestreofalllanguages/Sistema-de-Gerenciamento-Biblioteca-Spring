package br.study.SpringStudy_3.Repository;

import br.study.SpringStudy_3.Entity.Emprestimo;
import br.study.SpringStudy_3.Entity.Livro;
import br.study.SpringStudy_3.Entity.Membro;
import br.study.SpringStudy_3.Service.Enum.EmpStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpRepository extends JpaRepository<Emprestimo, Long> {
    Optional<Emprestimo> findByLivroAndStatus(Livro livro, EmpStatus status);
    Optional<Emprestimo> findByMembroAndStatus(Membro membro, EmpStatus status);
    Long countEmprestimoByMembro_IdAndStatus(Long idMember, EmpStatus status, EmpStatus Status2);
    boolean existsByLivroAndMembroAndStatus(Livro livro, Membro membro, EmpStatus status, EmpStatus status2);
    List<Emprestimo> findByStatus(EmpStatus status);
    Optional<Emprestimo> findByMembroAndLivroAndStatus(Membro membro, Livro livro, EmpStatus status);
}

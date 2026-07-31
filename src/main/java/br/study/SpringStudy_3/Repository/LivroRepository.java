package br.study.SpringStudy_3.Repository;

import br.study.SpringStudy_3.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}

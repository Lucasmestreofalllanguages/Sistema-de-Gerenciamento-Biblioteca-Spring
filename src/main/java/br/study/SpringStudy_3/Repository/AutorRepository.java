package br.study.SpringStudy_3.Repository;

import br.study.SpringStudy_3.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}

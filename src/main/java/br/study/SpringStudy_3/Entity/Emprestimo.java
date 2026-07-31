package br.study.SpringStudy_3.Entity;

import br.study.SpringStudy_3.Service.Enum.EmpStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "membro_id")
    private Membro membro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    private EmpStatus status;

}

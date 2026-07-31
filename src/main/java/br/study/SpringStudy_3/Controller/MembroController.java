package br.study.SpringStudy_3.Controller;

import br.study.SpringStudy_3.Entity.Membro;
import br.study.SpringStudy_3.Exception.MemberAlreadyHaveALoanException;
import br.study.SpringStudy_3.Exception.MemberNotFoundException;
import br.study.SpringStudy_3.Repository.MembroRepository;
import br.study.SpringStudy_3.Service.DTO.MembroCreateDTO;
import br.study.SpringStudy_3.Service.DTO.MembroRequestDTO;
import br.study.SpringStudy_3.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membro")
public class MembroController {
    @Autowired
    MembroRepository membroRepository;

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping
    public List<Membro> listAll(){
        return membroRepository.findAll();
    }

    @PostMapping("/cadastro")
    public MembroRequestDTO registerMember(@RequestBody MembroCreateDTO membroCreateDTO) {
        Membro membro = new Membro();

        membro.setNome(membroCreateDTO.getNome());
        membro.setCpf(membroCreateDTO.getCpf());
        membroRepository.save(membro);

        return new MembroRequestDTO(membro.getNome(), membro.getId());
    }

    @GetMapping("/{id}")
    public MembroRequestDTO showMember(@PathVariable Long id){
        Membro membro = membroRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException(id)
        );

        return new MembroRequestDTO(membro.getNome(), membro.getId());
    }

    @PutMapping("/atualizar/{id}")
    public MembroRequestDTO updateMember(@RequestBody MembroCreateDTO membroCreateDTO, @PathVariable Long id){
        Membro membro = membroRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException(id)
        );
        if(membroCreateDTO.getNome() != null) {
            membro.setNome(membroCreateDTO.getNome());
        }
        if(membroCreateDTO.getCpf() != null) {
            membro.setCpf(membroCreateDTO.getCpf());
        }
        membroRepository.save(membro);

        return new MembroRequestDTO(membro.getNome(), membro.getId());
    }

    @DeleteMapping("/excluir/{id}")
    public void delete(@PathVariable Long id) {
        membroRepository.findById(id).orElseThrow(
                () -> new  MemberNotFoundException(id)
        );

        if(emprestimoService.memberHaveLoan(id)) {
            throw new MemberAlreadyHaveALoanException(id);
        } else {
            membroRepository.deleteById(id);
        }
    }
}

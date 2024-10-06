package Atividade_Fujioka.service;

import Atividade_Fujioka.model.DTO.ProdutorDTO;
import Atividade_Fujioka.model.Produtor;
import Atividade_Fujioka.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProdutorService {

    private final ProdutorRepository produtorRepository;

    public List<Produtor> listarTodos() {
        return produtorRepository.findAll();
    }

    public Optional<Produtor> buscarPorId(Long id) {
        return produtorRepository.findById(id);
    }

    public Produtor criarProdutor(ProdutorDTO produtor) {
        return produtorRepository.save(produtor);
    }

    public Optional<Produtor> atualizarProdutor(Long id, ProdutorDTO produtor) {
        return produtorRepository.findById(id).map(p -> {
            p.setNome(produtor.getNome());
            p.setIdade(produtor.getIdade());
            p.setNacionalidade(produtor.getNacionalidade());
            return produtorRepository.save(p);
        });
    }

    public boolean deletarProdutor(Long id) {
        if (produtorRepository.existsById(id)) {
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

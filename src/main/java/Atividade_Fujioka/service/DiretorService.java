package Atividade_Fujioka.service;

import Atividade_Fujioka.model.Ator;
import Atividade_Fujioka.model.DTO.AtorDTO;
import Atividade_Fujioka.model.DTO.DiretorDTO;
import Atividade_Fujioka.model.DTO.FilmeDTO;
import Atividade_Fujioka.model.DTO.GeneroDTO;
import Atividade_Fujioka.model.Diretor;
import Atividade_Fujioka.model.Filme;
import Atividade_Fujioka.model.Genero;
import Atividade_Fujioka.repository.DiretorRepository;
import Atividade_Fujioka.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiretorService {

        private final DiretorRepository diretorRepository;

        public List<DiretorDTO> listarTodos() {
            return diretorRepository.findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        public Optional<DiretorDTO> buscarPorId(Long id) {
            return diretorRepository.findById(id)
                    .map(this::convertToDTO);
        }

        public FilmeDTO criarFilme(FilmeDTO filmeDTO) {
            Filme filme = convertToEntity(filmeDTO);
            return convertToDTO(DiretorRepository.save(filme));
        }

        public Optional<FilmeDTO> atualizarFilme(Long id, FilmeDTO filmeDTO) {
            return diretorRepository.findById(id).map(filme -> {
                filme.setTitulo(diretorDTO.getTitulo());
                filme.setAnoLancamento(filmeDTO.getAnoLancamento());
                filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
                filme.setAtores(filmeDTO.getAtores().stream().map(this::convertToEntity).collect(Collectors.toList()));
                filme.setGeneros(filmeDTO.getGeneros().stream().map(this::convertToEntity).collect(Collectors.toList()));
                return convertToDTO(filmeRepository.save(filme));
            });
        }

        public boolean deletarFilme(Long id) {
            if (filmeRepository.existsById(id)) {
                filmeRepository.deleteById(id);
                return true;
            }
            return false;
        }

        private FilmeDTO convertToDTO(Filme filme) {
            return new FilmeDTO(
                    filme.getId(),
                    filme.getTitulo(),
                    filme.getAnoLancamento(),
                    filme.getDiretor() != null ? new DiretorDTO(filme.getDiretor().getId(), filme.getDiretor().getNome()) : null,
                    filme.getAtores()
                            .stream()
                            .map(ator -> new AtorDTO(ator.getId(), ator.getNome()))
                            .collect(Collectors.toList()),
                    filme.getGeneros()
                            .stream()
                            .map(genero -> new GeneroDTO(genero.getId(), genero.getNome()))
                            .collect(Collectors.toList())
            );
        }


        private Filme convertToEntity(FilmeDTO filmeDTO) {
            Filme filme = new Filme();
            filme.setTitulo(filmeDTO.getTitulo());
            filme.setAnoLancamento(filmeDTO.getAnoLancamento());
            filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
            filme.setAtores(filmeDTO.getAtores()
                    .stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList()));
            filme.setGeneros(filmeDTO.getGeneros()
                    .stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList()));
            return filme;
        }

        private Diretor convertToEntity(DiretorDTO diretorDTO) {
            if (diretorDTO == null) {
                return null; // Retorne null caso não haja Diretor
            }
            Diretor diretor = new Diretor();
            diretor.setId(diretorDTO.getId());
            diretor.setNome(diretorDTO.getNome());
            return diretor;
        }

        private Ator convertToEntity(AtorDTO atorDTO) {
            Ator ator = new Ator();
            ator.setId(atorDTO.getId());
            ator.setNome(atorDTO.getNome());
            return ator;
        }

        private Genero convertToEntity(GeneroDTO generoDTO) {
            Genero genero = new Genero();
            genero.setId(generoDTO.getId());
            genero.setNome(generoDTO.getNome());
            return genero;
        }
    }


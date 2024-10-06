package Atividade_Fujioka.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutorDTO {

    private Long id;
    private String nome;
    private int idade;
    private String nacionalidade;
    private List<FilmeDTO> filmes;
}

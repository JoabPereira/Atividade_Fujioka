package Atividade_Fujioka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Diretor {
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "diretor")
    private List<Filme> filmeDirigidos;
}

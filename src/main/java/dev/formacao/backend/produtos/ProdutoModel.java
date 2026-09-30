package dev.formacao.backend.produtos;

import dev.formacao.backend.usuarios.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "descricao", nullable = false)
    private String descricao;

    @Column (name = "preco", nullable = false)
    private Double preco;

    @Column (name = "inserido_em", nullable = false)
    private LocalDateTime inserido_em;

    @Enumerated(EnumType.STRING)
    @Column (name = "categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuarioModel;
}

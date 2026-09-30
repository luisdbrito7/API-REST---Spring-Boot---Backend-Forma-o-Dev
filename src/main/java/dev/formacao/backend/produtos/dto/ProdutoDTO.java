package dev.formacao.backend.produtos.dto;

import dev.formacao.backend.produtos.Categoria;
import dev.formacao.backend.produtos.ProdutoModel;

import dev.formacao.backend.usuarios.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Optional<Integer> id = Optional.empty();

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição do produto é obrigatória")
    @Size(min = 3, max = 300, message = "A descrição do produto deve ter entre 3 e 300 caracteres.")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    @Positive(message = "O preço deve ser positivo.")
    private Double preco;

    @NotNull(message = "A categoria do produto é obrigatória")
    private Categoria categoria;

    private Optional<LocalDateTime> inseridoEm = Optional.empty();

    private Optional<Integer> idUsuario = Optional.empty();

    public ProdutoModel toModel() {
        ProdutoModel p = new ProdutoModel();

        if (this.id != null && this.id.isPresent()) {
            p.setId(this.id.get());
        }

        p.setNome(this.getNome());
        p.setDescricao(this.getDescricao());
        p.setPreco(this.getPreco());
        p.setCategoria(this.getCategoria());

        if (this.inseridoEm != null && this.inseridoEm.isPresent()) {
            p.setInserido_em(this.inseridoEm.get());
        } else {
            p.setInserido_em(LocalDateTime.now());
        }

        if (this.idUsuario != null && this.idUsuario.isPresent()) {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(this.idUsuario.get());
            p.setUsuarioModel(usuarioModel);
        } else {
            p.setUsuarioModel(null);
        }

        return p;
    }

    public static ProdutoDTO fromModel(ProdutoModel produtoModel) {
        return new ProdutoDTO(
                Optional.ofNullable(produtoModel.getId()),
                produtoModel.getNome(),
                produtoModel.getDescricao(),
                produtoModel.getPreco(),
                produtoModel.getCategoria(),
                Optional.ofNullable(produtoModel.getInserido_em()),
                Optional.ofNullable(produtoModel.getUsuarioModel().getId())
        );
    }
}
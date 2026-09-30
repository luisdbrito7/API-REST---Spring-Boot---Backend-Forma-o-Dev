package dev.formacao.backend.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    List<ProdutoModel> findByUsuarioModelId(Integer usuarioId);
    Optional<ProdutoModel> findByIdAndUsuarioModelId(Integer produtoId, Integer usuarioId);
}

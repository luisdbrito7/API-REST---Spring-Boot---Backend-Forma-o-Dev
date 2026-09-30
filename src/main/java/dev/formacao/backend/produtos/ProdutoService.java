package dev.formacao.backend.produtos;

import dev.formacao.backend.exceptions.ProdutoNaoEncontrado;
import dev.formacao.backend.produtos.dto.ProdutoDTO;
import dev.formacao.backend.usuarios.UsuarioModel;
import dev.formacao.backend.usuarios.UsuarioRepository;
import dev.formacao.backend.usuarios.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

        public final ProdutoRepository produtoRepository;
        private final UsuarioRepository usuarioRepository;

        public ProdutoService(ProdutoRepository r, UsuarioRepository ur){
        this.produtoRepository = r;
        this.usuarioRepository = ur;
}

    private Integer pegarIdUsuario(String email){
            UsuarioModel usuario = usuarioRepository.findByEmail(email);
            Integer idUsuario = usuario.getId();
            return idUsuario;
    }

    public List<ProdutoDTO> ListarTodos(String email){
        Integer idUsuario = pegarIdUsuario(email);
        return produtoRepository.findByUsuarioModelId(idUsuario).stream()
        .map(ProdutoDTO::fromModel).toList();
    }

        public ProdutoDTO listarUm(Integer id, String email){
            Integer idUsuario = pegarIdUsuario(email);
            ProdutoModel produto = produtoRepository.findByIdAndUsuarioModelId(id, idUsuario).orElseThrow(ProdutoNaoEncontrado::new);
            return ProdutoDTO.fromModel(produto);
        }

    public ProdutoDTO criar(ProdutoDTO produtoDTO, String email){
            Integer idUsuario = pegarIdUsuario(email);
            produtoDTO.setIdUsuario(Optional.of(idUsuario));
        ProdutoModel produto = produtoRepository.saveAndFlush(produtoDTO.toModel());
        return ProdutoDTO.fromModel(produto);
    }
    public ProdutoDTO atualizar(Integer id,ProdutoDTO produtoDTO, String email){
          ProdutoDTO produtoOriginal = listarUm(id, email);
          produtoOriginal.setNome(produtoDTO.getNome());
          produtoOriginal.setDescricao(produtoDTO.getDescricao());
          produtoOriginal.setPreco(produtoDTO.getPreco());
          produtoOriginal.setCategoria(produtoDTO.getCategoria());
          ProdutoModel produtoSalvo = produtoRepository.saveAndFlush(produtoOriginal.toModel());
          return ProdutoDTO.fromModel(produtoSalvo);
    }
    public ProdutoDTO deletar (Integer id, String email){
            ProdutoDTO produto = this.listarUm(id, email);
            produtoRepository.deleteById(id);
            return produto;
    }
}
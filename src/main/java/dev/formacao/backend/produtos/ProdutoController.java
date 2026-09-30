package dev.formacao.backend.produtos;

import dev.formacao.backend.produtos.dto.ProdutoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService ProdutoService;

    public ProdutoController(ProdutoService s){
        this.ProdutoService = s;
    }
    @GetMapping
    public List<ProdutoDTO> listarTodos(@AuthenticationPrincipal UserDetails userDetails){
    return ProdutoService.ListarTodos(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public ProdutoDTO listarUm(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails){
        return ProdutoService.listarUm(id, userDetails.getUsername());}

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO produtoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        ProdutoDTO novoProduto = ProdutoService.criar(produtoDTO, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Integer id,
                                @Valid @RequestBody ProdutoDTO produtoDTO,
                                @AuthenticationPrincipal UserDetails userDetails
    ){
        return ProdutoService.atualizar(id, produtoDTO, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    public ProdutoDTO deletar (@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails){
        return ProdutoService.deletar(id, userDetails.getUsername());
    }
}

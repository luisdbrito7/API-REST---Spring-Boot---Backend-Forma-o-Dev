package dev.formacao.backend.usuarios;

import dev.formacao.backend.usuarios.dto.CadastrarUsuarioDTO;
import dev.formacao.backend.usuarios.dto.LogarUsuarioDTO;
import dev.formacao.backend.usuarios.dto.UsuarioCadastradoDTO;
import dev.formacao.backend.usuarios.dto.UsuarioLogadoDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/cadastrar")
    public UsuarioCadastradoDTO cadastrar(@Valid @RequestBody CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        return usuarioService.cadastrar(cadastrarUsuarioDTO);
    }
    @PostMapping("/login")
    public UsuarioLogadoDTO logar(@Valid @RequestBody LogarUsuarioDTO logarUsuarioDTO){
    return usuarioService.logar(logarUsuarioDTO);
    }
}
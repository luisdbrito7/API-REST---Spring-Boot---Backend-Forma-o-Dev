package dev.formacao.backend.usuarios;

import dev.formacao.backend.JWT.JwtService;
import dev.formacao.backend.exceptions.UsuarioJaExiste;
import dev.formacao.backend.exceptions.UsuarioNaoExiste;
import dev.formacao.backend.usuarios.dto.CadastrarUsuarioDTO;
import dev.formacao.backend.usuarios.dto.LogarUsuarioDTO;
import dev.formacao.backend.usuarios.dto.UsuarioCadastradoDTO;
import dev.formacao.backend.usuarios.dto.UsuarioLogadoDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                          @Lazy AuthenticationManager authenticationManager,
                          JwtService jwtService
    ){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public UsuarioCadastradoDTO cadastrar(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        UsuarioModel usuarioExistente = usuarioRepository.findByEmail(cadastrarUsuarioDTO.getEmail());

        if (usuarioExistente != null) {
            throw new UsuarioJaExiste();
        }

        UsuarioModel usuarioParaSalvar = new UsuarioModel();

        usuarioParaSalvar.setNome(cadastrarUsuarioDTO.getNome());
        usuarioParaSalvar.setEmail(cadastrarUsuarioDTO.getEmail());
        usuarioParaSalvar.setSenha(passwordEncoder.encode(cadastrarUsuarioDTO.getSenha()));

        usuarioRepository.saveAndFlush(usuarioParaSalvar);

        return new UsuarioCadastradoDTO(usuarioParaSalvar.getNome(), usuarioParaSalvar.getEmail());
    }

    public UsuarioLogadoDTO logar(LogarUsuarioDTO logarUsuarioDTO) {
        UsuarioModel usuarioExiste = usuarioRepository.findByEmail(logarUsuarioDTO.getEmail());
        if (usuarioExiste == null) {
            throw new UsuarioNaoExiste();
        }

        UsernamePasswordAuthenticationToken emailSenha = new UsernamePasswordAuthenticationToken(logarUsuarioDTO.getEmail(), logarUsuarioDTO.getSenha());
        authenticationManager.authenticate(emailSenha);
        String token = jwtService.gerarToken(usuarioExiste.getEmail());
        return new UsuarioLogadoDTO(usuarioExiste.getNome(), usuarioExiste.getEmail(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
        return usuario;
    }
}
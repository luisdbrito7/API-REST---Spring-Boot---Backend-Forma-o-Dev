package dev.formacao.backend.exceptions;

public class UsuarioNaoExiste extends RuntimeException{
    public UsuarioNaoExiste(){
        super("Usuário não existe");
    }
}

package dev.formacao.backend.exceptions;

public class UsuarioJaExiste extends RuntimeException{
    public UsuarioJaExiste(){
        super("Usuário já existe");
    }
}

package dev.formacao.backend.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogarUsuarioDTO {

    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "E-mail inválido")
    String email;

    @NotBlank(message = "A senha é obrigatorio")
    @Size(min = 3, max = 50, message = "A senha deve ter entre 3 e 50 caracteres")
    String senha;
}

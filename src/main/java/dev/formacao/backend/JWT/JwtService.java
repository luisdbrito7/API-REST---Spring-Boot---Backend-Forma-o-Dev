package dev.formacao.backend.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String segredo;

    @Value("${jwt.expiration}")
    private Integer expiracao;

    public String gerarToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.create()
                    .withIssuer("Backend")
                    .withSubject(email)
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Houve um problema", exception);
        }
    }
    public String decodificarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.require(algorithm)
                    .withIssuer("Backend")
                    .build().verify(token)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Houve um problema", exception);
        }
    }
}
// UserService.java
package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.infraestructure.persisstence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User salvar( User request) {
        String plano = null;
        if(request.getPlano() == 1 ){
            plano = "BASIC";
        }
        if(request.getPlano() == 2 ){
            plano = "MEDIUMN";
        }
        if(request.getPlano() == 3 ){
            plano = "PREMIUMN";
        }

        return userRepository.save(User.builder()
                .username(request.getUsername())
                .nome(request.getNome())
                .password(passwordEncoder.encode(request.getPassword()))
                .ativo(true)
                .criacao(LocalDateTime.now())
                .nascimento(request.getNascimento())
                .email(request.getEmail())
                .role(plano)
                .status("ATIVO")
                .build());
    }
    public  User getUserIdByUsername(String username) {
        Optional<User> usuario = userRepository.findByUsername(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new IllegalArgumentException("Usuário não encontrado");
    }

}

// UserService.java
package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.infraestructure.persisstence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String username, String rawPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }
    public  User getUserIdByUsername(String username) {
        Optional<User> usuario = userRepository.findByUsername(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new IllegalArgumentException("Usuário não encontrado");
    }
}

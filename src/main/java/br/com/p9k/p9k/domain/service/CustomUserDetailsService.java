// CustomUserDetailsService.java
package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.infraestructure.persisstence.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Lista de permissões diretamente (você pode definir isso como preferir)
        List<String> roles = List.of("ROLE_ADM", "BASIC", "MEDIUMN", "PREMIUMN");

        // Converter as roles diretamente em authorities
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new) // Converter cada role para SimpleGrantedAuthority
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // Hash BCrypt do BD
                authorities // Passar as authorities geradas
        );
    }

}

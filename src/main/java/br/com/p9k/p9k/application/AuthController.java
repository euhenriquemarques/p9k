// AuthController.java
package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> creds) {
        String username = creds.get("username");
        String password = creds.get("password");

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = usuarioService.getUserIdByUsername(username);

        String token = jwtUtil.generateToken(username,user.getId(), user.getRole());
        return Map.of(
                "token", token,
                "role", user.getRole()
        );
    }
}

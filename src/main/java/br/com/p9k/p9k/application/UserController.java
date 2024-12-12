// UserController.java
package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String,String> creds) {
        String username = creds.get("username");
        String password = creds.get("password");
        return userService.createUser(username, password);
    }
}

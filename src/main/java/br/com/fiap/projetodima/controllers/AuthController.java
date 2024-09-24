package br.com.fiap.projetodima.controllers;
import br.com.fiap.projetodima.dto.auth.SignInCredentialsDTO;
import br.com.fiap.projetodima.model.User;
import br.com.fiap.projetodima.services.TokenServices;
import jakarta.validation.Valid;
import br.com.fiap.projetodima.dto.auth.ResponseTokenDTO;
import br.com.fiap.projetodima.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenServices tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenServices tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDTO> loginUser(@RequestBody @Valid SignInCredentialsDTO credentialsDTO){
        try {
            var token = new UsernamePasswordAuthenticationToken(credentialsDTO.email(), credentialsDTO.password());
            var authentication = manager.authenticate(token);

            var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new ResponseTokenDTO(tokenJwt));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

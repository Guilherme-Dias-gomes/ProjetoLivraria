package br.com.fiap.projetodima.config;
import br.com.fiap.projetodima.repositories.UserRepository;
import br.com.fiap.projetodima.services.TokenServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;


@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenServices tokenServices;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJwt = request.getHeader("Authorization");

        if (tokenJwt != null) {
            if (!tokenJwt.startsWith("Bearer ")) {
                response.setStatus(401);
                response.addHeader("Content-Type", "application/json");
                response.getWriter().write("""
                    {
                        "message": "Token must start with Bearer"
                    }
                """);
                return;
            } else {
                try {
                    // Extrair o subject (nome de usuário) do token
                    var subject = tokenServices.getSubject(tokenJwt.replace("Bearer ", ""));

                    // Buscar o usuário pelo nome de usuário
                    var userOptional = userRepository.findByName(subject);

                    // Verificar se o usuário foi encontrado
                    if (userOptional.isPresent()) {
                        var user = userOptional.get();

                        // Criar a autenticação com as autoridades do usuário
                        var auth = new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    } else {
                        // Caso o usuário não seja encontrado
                        response.setStatus(404);
                        response.addHeader("Content-Type", "application/json");
                        response.getWriter().write("""
                            {
                                "message": "User not found"
                            }
                        """);
                        return;
                    }

                } catch (Exception e) {
                    response.setStatus(403);
                    response.addHeader("Content-Type", "application/json");
                    response.getWriter().write("""
                    {
                        "message": "%s"
                    }
                """.formatted(e.getMessage()));
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}

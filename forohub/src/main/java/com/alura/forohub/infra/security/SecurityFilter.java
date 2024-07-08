package com.alura.forohub.infra.security;

import com.alura.forohub.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("Validación de token no null");
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            System.out.println("Token Subject: " + subject);
            if (subject != null) {
                var usuario = usuarioRepository.findByLogin(subject);
                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("Usuario no encontrado");
                }
            } else {
                System.out.println("Subject del token es null");
            }
        } else {
            System.out.println("Encabezado Authorization no presente o no tiene el formato correcto");
        }
        filterChain.doFilter(request, response);
    }
}


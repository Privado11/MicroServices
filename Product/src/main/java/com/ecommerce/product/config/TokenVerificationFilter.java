package com.ecommerce.product.config;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenVerificationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        // Obtener el token del header Authorization
        String authHeader = request.getHeader("Authorization");
        
        log.info("=== Inicio de verificación de token ===");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            // Loguear información sobre el token
            log.info("Token recibido: {}", token.substring(0, Math.min(token.length(), 20)) + "...");
            
            try {
                // Decodificar el token (sin verificar la firma) para ver su contenido
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                
                if (chunks.length >= 2) {
                    String payload = new String(decoder.decode(chunks[1]));
                    log.info("Contenido del token: {}", payload);
                }
                
                log.info("Token presente y con formato correcto");
            } catch (Exception e) {
                log.error("Error al decodificar el token: {}", e.getMessage());
            }
        } else {
            log.warn("No se encontró token Bearer en la petición");
        }
        
        log.info("=== Fin de verificación de token ===");
        
        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
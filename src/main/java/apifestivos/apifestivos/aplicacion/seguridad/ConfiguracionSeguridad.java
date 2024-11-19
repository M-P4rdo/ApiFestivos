package apifestivos.apifestivos.aplicacion.seguridad;
//Especificar qué rutas protegidas

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private FiltroSeguridad filtro;

    @Bean
    public UserDetailsService servicioUsuario() {
        return new UsuarioDetallesServicio();
    }

    // Configurando Seguridad Http
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        return https.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilita CORS
                .csrf(csrf -> csrf.disable()) // Deshabilita la protección CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/usuarios/login/**").permitAll()
                        .anyRequest().authenticated()) // endpoints sin tokens
                .addFilterAfter(filtro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // URL de frontend
        config.addAllowedHeader("Authorization"); // Permite encabezados personalizados como Authorization
        config.addAllowedHeader("Content-Type"); // Permite encabezados estándar
        config.addAllowedMethod("GET"); // Métodos HTTP permitidos
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.setAllowCredentials(true); // Permite cookies/sesiones si es necesario

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica a todas las rutas
        return source;
    }

}

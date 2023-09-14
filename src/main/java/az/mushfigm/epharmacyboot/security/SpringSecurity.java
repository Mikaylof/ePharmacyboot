package az.mushfigm.epharmacyboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
      /*  auth.inMemoryAuthentication().withUser("mushu").password("1234").roles("ADMIN")
                .and().withUser("elish").password("123").roles("USER");*/
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       // http.csrf().disable();
        http.authorizeHttpRequests().antMatchers("/user/**","/purchaser/**").permitAll()
                .antMatchers("/customer/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/order/**", "/medication/**").hasRole("ADMIN").anyRequest().authenticated()
                .and().httpBasic().and().csrf().disable();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

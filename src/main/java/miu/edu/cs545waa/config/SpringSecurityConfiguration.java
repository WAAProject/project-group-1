package miu.edu.cs545waa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // JDBC Authentication
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                // Finding user
                .usersByUsernameQuery("SELECT email, password, enabled FROM user WHERE email = ?")
                // Finding roles / authorities of user
                .authoritiesByUsernameQuery("SELECT email, UPPER(user_type) FROM user WHERE email = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/buyer/**").hasAuthority("BUYER")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/", "/static/**").permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .rememberMeCookieName("remember-me-cookie")
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(24 * 60 * 60) // expired time = 1 day
                .and()
                .logout()
                    .logoutUrl("/logout") //change default /logout url to /perform_logout
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied");
        // enabling /logout path for GET request for now
        http.csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

        // enable H2 console? Got better solution. Use WebSecurity object's ignoring method.
//        http.csrf().disable();
//        http.headers().frameOptions().disable();

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}

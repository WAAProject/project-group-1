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
//                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                // Finding roles / authorities of user
//                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");

                // This populates dummy users, use after .dataSource(dataSource) line
                .withDefaultSchema()
                .withUser(
                        User.withUsername("buyer")
                                .password("buyer")
                                .roles("BUYER")
                )
                .withUser(
                        User.withUsername("seller")
                                .password("seller")
                                .roles("SELLER")
                )
                .withUser(
                        User.withUsername("admin")
                                .password("admin")
                                .roles("ADMIN")
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/buyer/**").hasRole("BUYER")
                .antMatchers("/seller/**").hasRole("SELLER")
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "SELLER", "BUYER")
                .antMatchers("/", "/static/**").permitAll()
                .and().formLogin();
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

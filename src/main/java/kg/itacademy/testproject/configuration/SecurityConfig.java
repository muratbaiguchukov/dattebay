
package kg.itacademy.testproject.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.jdbcAuthentication ()
                .dataSource ( dataSource )
                .usersByUsernameQuery ( "SELECT u.user_login, u.user_password, u.user_email u.user_activity FROM users u WHERE u.user_login = ?" )
                .authoritiesByUsernameQuery (
                        "SELECT u.login, r.name_role " +
                        "FROM users_roles ur " +
                        "INNER JOIN users u " +
                        "   on ur.user_id = u.id " +
                        "INNER JOIN roles r " +
                        "   on ur.role_id = r.id " +
                        "WHERE u.login = ? AND u.user_activity = 1"
                );
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception
    {
        http
                .sessionManagement ()
                .sessionCreationPolicy ( SessionCreationPolicy.STATELESS )
                .and ()
                .csrf ().disable ()
                .authorizeRequests ()

                //USER
                .antMatchers ( HttpMethod.GET, "/api/users/create" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/users/get/{userId}" ).permitAll ()
                .antMatchers ( HttpMethod.DELETE, "api/users/delete{userId}" ).permitAll ()
                .antMatchers ( HttpMethod.PUT, "/api/users/update" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/users/get/all-users" ).permitAll ()
                .antMatchers ( HttpMethod.POST, "/api/users/sign-up" ).permitAll ()

                //LESSONS
                .antMatchers ( HttpMethod.POST, "/api/lessons/create" ).hasRole ( "Teacher" )
                .antMatchers ( HttpMethod.DELETE, "/api/lessons/delete/{lessonId}" ).hasRole ( "Teacher" )

                //COURSE
                .antMatchers ( HttpMethod.PUT, "/api/courses/create" ).hasRole ( "Teacher" )
                .antMatchers ( HttpMethod.DELETE, "/api/courses/delete/{courseId}" ).hasRole ( "Teacher" )

                .and ()
                .httpBasic ();
    }

    @Bean
    public PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }
}


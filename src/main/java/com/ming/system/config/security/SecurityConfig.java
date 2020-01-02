package com.ming.system.config.security;


import com.ming.system.filter.JWTAccessDeniedHandler;
import com.ming.system.filter.JWTAuthenticationEntryPoint;
import com.ming.system.filter.JwtTokenFilter;
import com.ming.system.service.impl.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * 简书：https://www.jianshu.com/p/e715cc993bf0
 * github:https://github.com/gf-huanchupk/SpringBootLearning
 *  Springboot+Spring-Security+JWT 实现用户登录和权限认证
 * https://blog.csdn.net/zhangcongyi420/article/details/91348402
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailsService userDetailsService;

    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean res = s.equals( encode );
                return res;
            }
        } );

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //由于使用的是JWT，我们这里不需要csrf
        http.csrf().disable()
                //因为使用JWT，所以不需要HttpSession
                //.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //所有用户可以访问
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //登录接口放行
                .antMatchers("/login","/auth/login","/auth/register","/static/**").permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated().and()
                //统一结果处理
                .exceptionHandling()
                .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler())
                //指定登录界面，并且设置为所有人都能访问
                //.and().formLogin().loginPage("/login").permitAll()
                //.defaultSuccessUrl("/index")
                //登录失败
                //.failureUrl("/logout")
                //.and()
                //.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                //.invalidateHttpSession(true).deleteCookies("usernameCookie","urlCookie")
        ;

        //添加JWT filter,使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        //禁用缓存
        http.headers().cacheControl();
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
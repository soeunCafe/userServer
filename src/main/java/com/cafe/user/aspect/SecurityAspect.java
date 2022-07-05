package com.cafe.user.aspect;

import com.cafe.user.config.SecurityService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    SecurityService securityService;

    @Before("@annotation(tokenRequired)")
    public void authenticationWithToken(TokenRequired tokenRequired) throws IllegalAccessError, IllegalAccessException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");
        if(tokenBearer.isEmpty()) throw new IllegalAccessException("token isNull");
        if(securityService.getSubject(tokenBearer)==null) throw new IllegalAccessException("bad token");
    }
}

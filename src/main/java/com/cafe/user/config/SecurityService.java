package com.cafe.user.config;

import com.cafe.user.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SecurityService {
    @Value("${token.secret}")
    String SECRET_KEY="";
    @Value("${token.expiration_time}")
    long expTime;
    public String createToken(UserDto userDto){
        log.info(SECRET_KEY);
        log.info(""+expTime);
        if(expTime<=0){
            throw new RuntimeException();
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signatureKey =
                new SecretKeySpec(
                        secretKeyBytes,
                        signatureAlgorithm.getJcaName()
                );
        Map<String,Object> map = new HashMap<>();
        map.put("name",userDto.getName());
        map.put("img",userDto.getImg());
        return Jwts.builder()
                .setSubject(userDto.getId().toString())
                .addClaims(map)
                .signWith(signatureKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

    }
    public String getSubject(String tokenBearer){
        String token = tokenBearer.substring("Bearer ".length());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public Integer getIdAtToken(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenBearer = request.getHeader("Authorization");
        log.info(tokenBearer);

        String id = getSubject(tokenBearer);
        return Integer.parseInt(id);
    }

}
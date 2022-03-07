package team.arton.coreserver.service;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@Service
public class JwtService {

    @Value("${JWT.ISSUER}")
    private String ISSUER;

    @Value("${JWT.SECRET}")
    private String SECRET;

    public String createToken(Long userIdx) {
        Date nowDate = new Date();

        Map<String, Object> headers = new HashMap<>();
        headers.put(Header.TYPE, Header.JWT_TYPE);
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", userIdx);

        Long expiredTime = 1000 * 3600L * 24L * 7;
        Date expiredDate = new Date();
        expiredDate.setTime(expiredDate.getTime() + expiredTime);

        String jwtToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(ISSUER)
                .setIssuedAt(nowDate)
                .setExpiration(expiredDate)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        log.info(jwtToken);
        return jwtToken;
    }

    public Map<String, Object> verifyToken (String jwtToken) throws JwtException {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return claims;
    }

}

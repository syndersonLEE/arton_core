package utils;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Log4j2
public class JwtParser {

    public static String createToken(Long userIdx) {
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
                .setIssuer("sangyun")
                .setIssuedAt(nowDate)
                .setExpiration(expiredDate)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, "arton")
                .compact();

        log.info(jwtToken);
        return jwtToken;
    }

    public static Map<String, Object> verifyToken (String jwtToken) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("arton")
                    .parseClaimsJws(jwtToken)
                    .getBody();
            claimMap = claims;

        } catch (ExpiredJwtException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return claimMap;
    }

}

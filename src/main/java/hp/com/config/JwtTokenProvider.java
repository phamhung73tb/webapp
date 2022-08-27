package hp.com.config;

import hp.com.dto.ErrorCode;
import hp.com.exception.TokenInvalidException;
import hp.com.log.LOG;
import hp.com.utility.DateTimeUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author Pham Van Hung
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${token.key}")
    private String tokenKey;

    @Value("${token.expiration}")
    private Integer tokenExpiration;

    /**
     * Generate token from user login
     * @param username username
     * @return token of user
     */
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(DateTimeUtils.addDayToDate(new Date(), tokenExpiration ))
                .signWith(SignatureAlgorithm.HS512, tokenKey)
                .compact();
    }

    /**
     * Get ID from token
     * @param token token
     * @return id from token
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Check valid token
     * @param authToken token
     * @return validate
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOG.errorFile("token " + authToken + " is invalid");
           throw new TokenInvalidException(ErrorCode.E11.toString(), "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOG.errorFile("token " + authToken + " is expired");
            throw new TokenInvalidException(ErrorCode.E12.toString(), "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOG.errorFile("token " + authToken + " is unsupported");
            throw new TokenInvalidException(ErrorCode.E13.toString(), "Unsupported JWT token");
        } catch (Exception ex) {
            LOG.errorFile("token " + authToken + " is not found");
            throw new TokenInvalidException(ErrorCode.E14.toString(), "JWT not found");
        }
    }
}

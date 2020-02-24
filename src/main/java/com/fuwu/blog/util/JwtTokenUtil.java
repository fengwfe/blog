package com.fuwu.blog.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.fuwu.blog.dto.response.TokenInvalidDTO;
import com.fuwu.blog.exception.InvalidJwtTokenException;
import com.fuwu.blog.security.AppUserDetails;
import com.fuwu.blog.service.TokenInvalidService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	@Autowired
	TokenInvalidService logoutRecordService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	TokenInvalidService tokenInvalidService;
	
	
	@Value("${jwt.secret}")
    private String SECRET_KEY = "secret";
	private long  EXPIREATION_TIME=600*1000*60;
	private long TOKEN_REFRESH_BEFORE_EXPIRATION_TIME=5*1000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public Date extractIssueAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) throws InvalidJwtTokenException{
    	Claims claims=null;
		claims=Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIREATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    // token 合法返回用户信息，不合法返回NUll
    public AppUserDetails validateToken(String token) {
        try {
        	if(null==token||token.length()==0) {
        		return null;
        	}
        	boolean isTokenExpired=false;
        	boolean isTokenInvalidByLogoutOrRefresh=false;
        	boolean isTokenValid=false;
			Claims claims=extractAllClaims(token);
			String userName =claims.getSubject();
			Date tokenExpireDate=claims.getExpiration();
			Date issueAtDate=claims.getIssuedAt();
			isTokenExpired=tokenExpireDate.before(new Date());
			if(null==userName) {
				return null;
			}
			AppUserDetails userDetails=(AppUserDetails)userDetailsService.loadUserByUsername(userName);
			if(null==userDetails) {
				return null;
			}
			TokenInvalidDTO tokenInvalid =tokenInvalidService.fetchByUserId(userDetails.getId());
			if(null!=tokenInvalid) {
				 Date date=tokenInvalid.getCreated();
				 isTokenInvalidByLogoutOrRefresh=issueAtDate.before(date);
			}
			isTokenValid=!isTokenExpired && !isTokenInvalidByLogoutOrRefresh;
			if(!isTokenValid) {
				return null;
			}
			return userDetails;
			
		} catch (Exception e) {
//			logger.info(e.getMessage());
			return null;
		}
    }
    public String checkAndRefresh(String oldToken) {
    	String newToken=oldToken;
    	Date expireDate=extractExpiration(oldToken);
    	Date now=new Date();
    	if(expireDate.getTime()-now.getTime()<=TOKEN_REFRESH_BEFORE_EXPIRATION_TIME) {
    		String loginName=extractUsername(oldToken);
    		UserDetails userDetails=userDetailsService.loadUserByUsername(loginName);
    		tokenInvalidService.invalidTokenByUserId(((AppUserDetails) userDetails).getId());
    		newToken=generateToken(userDetails);
    		
    	}
    	return newToken;
    }

}

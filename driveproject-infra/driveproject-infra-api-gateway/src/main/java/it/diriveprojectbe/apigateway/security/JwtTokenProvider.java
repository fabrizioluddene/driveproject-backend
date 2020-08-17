package it.diriveprojectbe.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.diriveprojectbe.apigateway.client.UserClient;
import it.diriveprojectbe.apigateway.dto.JWTTokenResponse;
import it.diriveprojectbe.userservice.api.dto.UserDto;
import it.diriveprojectbe.userservice.api.dto.UsernameDto;
import it.diriveprojectbe.userservice.api.excpetion.NoUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String AUTHORIZATION = "Authorization";
    private String secretKey = "SECURITYTOKEN234678@";
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    UserClient userClient;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public JWTTokenResponse createToken(UserDto userDto) {
        JWTTokenResponse jwtTokenResponse = new JWTTokenResponse();
        Claims claims = Jwts.claims().setSubject(userDto.getUsername());
        claims.put(FIRSTNAME, userDto.getFirstName());
        claims.put(LASTNAME, userDto.getLastName());
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        jwtTokenResponse.setExpirationDate(validity);
        jwtTokenResponse.setToken(token);
        jwtTokenResponse.setTokenType("Bearer");
        return jwtTokenResponse;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);

        if (bearerToken != null) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void validateToken(String token) throws JwtException, IllegalArgumentException, NoUserFoundException {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        UsernameDto usernameDto = new UserDto();
        usernameDto.setUsername(getUsername(token));
        UserDto userDto = userClient.getUserByUsername(usernameDto).getBody();
        if (userDto.getError() != null){
            throw  new JWTAuthenticationException(userDto.getError().getDescription());
        }

    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token, HttpServletRequest httpServletRequest) {
        User userDetails = new User(getUsername(token), "dummyPassword", new ArrayList<>());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        return usernamePasswordAuthenticationToken;
    }
}

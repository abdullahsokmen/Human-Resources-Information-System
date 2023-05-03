package com.group.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {

    @Value("${security.secret-key}")
    String secretKey;
    @Value("${security.issuer}")
    String issuer;
        public Optional<String> createToken(Long id){
            String token = null;
            Long exDate = 1000L*60;
            try{
                /**
                 * DIKKAT!!!
                 * Claim objelerinin icine onemli ve herkes ile paylasilmayacak bilgileri koymazsiniz.
                 * email, username, passward vs gibi onemli bilgiler payload icinde olamaz.
                 */
                token = JWT.create().withAudience()
                        .withClaim("id",id)
                        .withClaim("howtopage","AuthService")
                        .withClaim("lastjoin",System.currentTimeMillis())
                        .withClaim("ders","Java JWT")
                        .withClaim("Student","Dino")
                        .withIssuer(issuer)//jwt sahibi
                        .withIssuedAt(new Date())// token olusma tarihi
                        .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                        .sign(Algorithm.HMAC512(secretKey));
                return Optional.of(token);
            }catch (Exception exception){
                return Optional.empty();
            }
        }

        public Boolean verifyToken(String token){
            try{
                Algorithm algorithm = Algorithm.HMAC512(secretKey);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(issuer).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                if(decodedJWT == null)
                    return false;
            }catch (Exception exception){
                return false;
            }
            return  true;
        }

        public Optional<Long> getByIdFromToken(String token){
            try{
                Algorithm algorithm = Algorithm.HMAC512(secretKey);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(issuer).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                if(decodedJWT == null)
                    return Optional.empty();
                Long id = decodedJWT.getClaim("id").asLong();
//                String howToPage = decodedJWT.getClaim("howtopage").asString();
//                System.out.println("howtopage.....: "+ howToPage);
                return Optional.of(id);
            }catch (Exception exception){
                return Optional.empty();
            }

        }
}

package fct.unl.pt.csdw1.Services;

import fct.unl.pt.csdw1.Entities.OurEntity;
import fct.unl.pt.csdw1.Repositories.OurRepo;
import fct.unl.pt.csdw1.Security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Optional;

@Service
public class OurService {
    private final OurRepo ourRepo;

    @Autowired
    public OurService(final OurRepo ourRepo) {
        this.ourRepo = ourRepo;
    }

    public OurEntity registerUser(String userName, String password){
        if(!ourRepo.findByUserName(userName).isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return ourRepo.save(new OurEntity(userName, passwordEncoder.encode(password)));
        }
        else
            return null;
    }

    public JSONObject changePassword(String who,String password){
        Optional<OurEntity> be = ourRepo.findByUserName(who);
        if(be.isPresent()){
            OurEntity b = be.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            b.updatePassword(passwordEncoder.encode(password));
            ourRepo.save(b);
            return new JSONObject().put("Success","True");
        }else
            return new JSONObject().put("error","User not found "+who);
    }

    public String getSubject(String token){
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
        Jws parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace("Bearer ", ""));
        Claims claims = (Claims) parsedToken.getBody();
        System.out.println(claims.getSubject());
        return claims
                .getSubject();
    }
}

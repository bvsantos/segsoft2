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

    public OurEntity registerUser(String userName, String password,String password2, String[] roles){
        if(!ourRepo.findByUserName(userName).isPresent() && password.equals(password2)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return ourRepo.save(new OurEntity(userName, passwordEncoder.encode(password),passwordEncoder.encode(password2),roles));
        }
        else
            return null;
    }

    public JSONObject changePassword(String who,String password,String password2){
        Optional<OurEntity> be = ourRepo.findByUserName(who);
        if(be.isPresent() && password.equals(password2)){
            OurEntity b = be.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            b.updatePassword(passwordEncoder.encode(password));
            ourRepo.save(b);
            return new JSONObject().put("Success","True");
        }else
            return new JSONObject().put("error","User not found "+who);
    }

    public JSONObject logout(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            OurEntity b = be.get();
            b.logout();
            ourRepo.save(b);
            return new JSONObject().put("Logout","Successful");
        }else{
            return new JSONObject().put("error","User not found "+username);
        }
    }

    public JSONObject delete(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            OurEntity b = be.get();
            if(b.getLocked() && !b.getLoggedIn()) {
                ourRepo.delete(b);
                return new JSONObject().put("Delete", "Successful");
            }else
                return new JSONObject().put("error", "User must not be logged and his account must be locked before its deleted");
        }else{
            return new JSONObject().put("error","User not found "+username);
        }
    }

    public JSONObject getInfo(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            return be.get().toJSON();
        }else{
            return new JSONObject().put("error","User not found "+username);
        }
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

    public boolean checkIfAdmin(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            return be.get().getRolesAsString().split("ROLE_ADMIN").length > 0;
        }else{
            return false;
        }
    }

    public JSONObject lock(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            OurEntity b = be.get();
            b.lock();
            ourRepo.save(b);
            return new JSONObject().put("Success",b.getOwnerName()+" locked");
        }else{
            return new JSONObject().put("error","User not found "+username);
        }
    }

    public JSONObject unlock(String username){
        Optional<OurEntity> be = ourRepo.findByUserName(username);
        if(be.isPresent()){
            OurEntity b = be.get();
            b.unlock();
            ourRepo.save(b);
            return new JSONObject().put("Success",b.getOwnerName()+" unlocked");
        }else{
            return new JSONObject().put("error","User not found "+username);
        }
    }
}

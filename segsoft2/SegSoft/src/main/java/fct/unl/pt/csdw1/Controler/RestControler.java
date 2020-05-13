package fct.unl.pt.csdw1.Controler;

import fct.unl.pt.csdw1.Daos.ChangePasswordDAO;
import fct.unl.pt.csdw1.Daos.RegisterDao;
import fct.unl.pt.csdw1.Services.OurService;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/")
public class RestControler {
        private final OurService bS;
        @Autowired
        public RestControler(final OurService bS ) {
                this.bS = bS;
        }

        @GetMapping(path="/test")
        public ResponseEntity<String> test() {
                return new ResponseEntity<>(new JSONObject().put("Application Status", "Running").toString(), HttpStatus.OK);
        }

        @RequestMapping(method=PUT,value="/register",consumes = "application/json")
        public ResponseEntity<String> createNew(@RequestBody RegisterDao dao){
                if(bS.registerUser(dao.userName,dao.password,dao.password2,dao.roles) == null)
                        return new ResponseEntity<>("A client already exists with the name "+ dao.userName , HttpStatus.CONFLICT);
                return new ResponseEntity<>("Created a new account for "+ dao.userName , HttpStatus.OK);
        }

        @RequestMapping(method=POST,value="/password", consumes = "application/json",produces = "application/json")
        public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDAO dao, @RequestHeader("Authorization") String beamer){
                if(bS.getSubject(beamer).equalsIgnoreCase(dao.username)){
                        JSONObject j = bS.changePassword(dao.username,dao.password,dao.password2);
                        if(j.has("error"))
                                return new ResponseEntity<>(j.toString(), HttpStatus.NOT_FOUND);
                        return new ResponseEntity<>(j.toString() , HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(new JSONObject().put("Error", "Token username and json username dont match").toString(),HttpStatus.BAD_REQUEST);
                }
        }

        @RequestMapping(method=DELETE,value="/delete",produces="application/json")
        public ResponseEntity<String> deleteAccount(@RequestHeader("Authorization") String beamer,@RequestParam("user") String username){
                String u = bS.getSubject(beamer);
                if(username.equalsIgnoreCase(u) || bS.checkIfAdmin(u)){
                        JSONObject j = bS.lock(username);
                        if(j.has("error"))
                                return new ResponseEntity<>(j.toString(),HttpStatus.BAD_REQUEST);
                        else {
                                j = bS.delete(username);
                                return new ResponseEntity<>(j.toString(), j.has("error") ? (HttpStatus.BAD_REQUEST) : (HttpStatus.OK));
                        }
                }else{
                        return new ResponseEntity<>(new JSONObject().put("error","You got no permission to delete this account").toString(),HttpStatus.FORBIDDEN);
                }
        }

        @RequestMapping(method=DELETE,value="/logout",produces = "application/json")
        public ResponseEntity<String> logout(@RequestHeader("Authorization") String beamer){
                JSONObject j = bS.logout(bS.getSubject(beamer));
                return new ResponseEntity<>(j.toString(),j.has("error")?(HttpStatus.BAD_REQUEST):(HttpStatus.OK));
        }

        @RequestMapping(method=GET,value="/account",produces = "application/json")
        public ResponseEntity<String> getAccount(@RequestHeader("Authorization") String beamer,@RequestParam("user") String username){
                String u = bS.getSubject(beamer);
                if(username.equalsIgnoreCase(u) || bS.checkIfAdmin(u)){
                        JSONObject j = bS.getInfo(username);
                        return new ResponseEntity<>(j.toString(),j.has("error")?(HttpStatus.BAD_REQUEST):(HttpStatus.OK));
                }else{
                        return new ResponseEntity<>(new JSONObject().put("error","You got no permission to get information of this account").toString(),HttpStatus.FORBIDDEN);
                }
        }

        @RequestMapping(method=PUT,value="/lock",produces = "application/json")
        public ResponseEntity<String> lockAccount(@RequestHeader("Authorization") String beamer,@RequestParam("user") String username){
                String u = bS.getSubject(beamer);
                if(username.equalsIgnoreCase(u) || bS.checkIfAdmin(u)){
                        JSONObject j = bS.lock(username);
                        return new ResponseEntity<>(j.toString(),j.has("error")?(HttpStatus.BAD_REQUEST):(HttpStatus.OK));
                }else{
                        return new ResponseEntity<>(new JSONObject().put("error","You got no permission to lock of this account").toString(),HttpStatus.FORBIDDEN);
                }
        }

        @RequestMapping(method=PUT,value="/unlock",produces = "application/json")
        public ResponseEntity<String> unlockAccount(@RequestHeader("Authorization") String beamer,@RequestParam("user") String username){
                String u = bS.getSubject(beamer);
                if(bS.checkIfAdmin(u)){
                        JSONObject j = bS.unlock(username);
                        return new ResponseEntity<>(j.toString(),j.has("error")?(HttpStatus.BAD_REQUEST):(HttpStatus.OK));
                }else{
                        return new ResponseEntity<>(new JSONObject().put("error","You got no permission to unlock this account").toString(),HttpStatus.FORBIDDEN);
                }
        }

}

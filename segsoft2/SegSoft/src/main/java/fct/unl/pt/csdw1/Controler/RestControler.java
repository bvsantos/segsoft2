package fct.unl.pt.csdw1.Controler;

import fct.unl.pt.csdw1.Daos.ChangePasswordDAO;
import fct.unl.pt.csdw1.Daos.RegisterDao;
import fct.unl.pt.csdw1.Services.OurService;
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

        @RequestMapping(method=PUT,value="/register",consumes = "application/json")
        public ResponseEntity<String> createNew(@RequestBody RegisterDao dao){
                if(bS.registerUser(dao.userName,dao.password) == null)
                        return new ResponseEntity<>("A client already exists with the name "+ dao.userName , HttpStatus.CONFLICT);
                return new ResponseEntity<>("Created a new account for "+ dao.userName , HttpStatus.OK);
        }

        @RequestMapping(method=POST,value="/password", consumes = "application/json",produces = "application/json")
        public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDAO dao, @RequestHeader("Authorization") String beamer){
                if(bS.getSubject(beamer).equalsIgnoreCase(dao.username)){
                        JSONObject j = bS.changePassword(dao.username,dao.password);
                        if(j.has("error"))
                                return new ResponseEntity<>(j.toString(), HttpStatus.NOT_FOUND);
                        return new ResponseEntity<>(j.toString() , HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(new JSONObject().put("Error", "Token username and json username dont match").toString(),HttpStatus.BAD_REQUEST);
                }
        }

        @GetMapping(path="/test")
        public ResponseEntity<String> test() {
                return new ResponseEntity<>(new JSONObject().put("Application Status", "Running").toString(), HttpStatus.OK);
        }
}

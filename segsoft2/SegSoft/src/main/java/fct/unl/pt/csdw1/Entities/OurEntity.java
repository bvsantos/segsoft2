package fct.unl.pt.csdw1.Entities;


import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@javax.persistence.Entity
public class OurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName, password,password2;
    private boolean logged_in, locked;
    private Long timeoutAuth, creationTime;
    private static final int TOKEN_LONGEVITY = 10 * 1000 * 60;
    /*
     * Example: ROLE_USER,ROLE_ADMIN => A simple roles.split(",") will get us a String [] of the roles,
     * with that array we will create a Collection of SimpleGrantedAuthority to be used by the spring security
     */
    private String roles;

    protected OurEntity() {
    }

    public OurEntity(String userName, String password,String password2, String[] roles) {
        this.userName = userName;
        this.password = password;
        this.password2 = password2;
        this.logged_in = false;
        this.locked = false;
        this.timeoutAuth = System.currentTimeMillis();
        this.creationTime = System.currentTimeMillis();
        // roles should be in this format ROLE_*,ROLE_*,...
        for(String l : roles){
            addRole(l);
        }
    }

    public boolean addRole(String l){
        Roles r;
        try{
            r = Roles.valueOf(l);
            this.roles += (this.roles.isEmpty()?(l):(","+l));
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }

    public Long getID() {
        return id;
    }

    public String getRolesAsString(){
        return this.roles;
    }

    public Collection<? extends GrantedAuthority> getRoles(){
        String[] r = roles.split(",");
        SimpleGrantedAuthority[] l= new SimpleGrantedAuthority[r.length];
        int i = 0;
        for(String o : r){
            l[i++] = new SimpleGrantedAuthority(o);
        }
        return Arrays.asList(l);
    }

    public String getOwnerName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void login() {
        logged_in = true;
        locked = false;
        timeoutAuth = System.currentTimeMillis() + TOKEN_LONGEVITY;
    }

    public void logout() {
        this.logged_in = false;
        this.locked = false;
        this.timeoutAuth = this.creationTime;
    }

    public void lock() {
        this.logged_in=false;
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean renewToken() {
        if (this.timeoutAuth > System.currentTimeMillis()) {
            this.timeoutAuth = System.currentTimeMillis() + TOKEN_LONGEVITY;
            return true;
        } else {
            logout();
            return false;
        }
    }

    public boolean getLoggedIn() {
        return this.logged_in;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public Long getTimeoutAuth() {
        return this.timeoutAuth;
    }

    public Long getCreationTime() {
        return this.creationTime;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public boolean checkIfTokenExpired(){
        if(this.timeoutAuth < System.currentTimeMillis()) {
            this.logged_in = false;
            return true;
        }else
            return false;
    }

    public JSONObject toJSON(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(this.creationTime);
        String creation = sdf.format(resultdate);
        return new JSONObject().put("username",this.userName).put("logInfo",new JSONObject().put("loggedIn",this.logged_in).put("locked",this.locked).put("creationDate",creation).toString());
    }
}

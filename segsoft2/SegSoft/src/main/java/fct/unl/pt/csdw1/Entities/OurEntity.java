package fct.unl.pt.csdw1.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
public class OurEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String userName, password;

    protected OurEntity(){};

    public OurEntity(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public Long getID(){
        return id;
    }

    public String getOwnerName(){
        return userName;
    }

    public String getPassword(){return password;}

    public void updatePassword(String password){
        this.password = password;
    }
}

package fct.unl.pt.csdw1.Daos;

public class ChangePasswordDAO {
    public String password,username,password2;
    public ChangePasswordDAO(String username,String password,String password2){
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }
}

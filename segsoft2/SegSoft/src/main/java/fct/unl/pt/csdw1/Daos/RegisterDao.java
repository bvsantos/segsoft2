package fct.unl.pt.csdw1.Daos;

public class RegisterDao {
    public String userName, password,password2;
    public String[] roles;
    public RegisterDao(String userName, String password,String password2,String[] roles){
        this.userName = userName;
        this.password = password;
        this.password2 = password2;
        this.roles = roles;
    }
}

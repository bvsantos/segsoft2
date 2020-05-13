package fct.unl.pt.csdw1.Security;

import fct.unl.pt.csdw1.Entities.OurEntity;
import fct.unl.pt.csdw1.Repositories.OurRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class MyUserDetails implements UserDetails {

    private OurEntity e1;


    public MyUserDetails(String userName,final OurRepo ourRepo){
        Optional<OurEntity> e = ourRepo.findByUserName(userName);
        this.e1 = e.isPresent()?e.get():null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.e1!=null?this.e1.getPassword():null;
    }

    @Override
    public String getUsername() {
        return this.e1!=null?this.e1.getOwnerName():null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.e1!=null?this.e1.getLocked():false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.e1!=null?this.e1.checkIfTokenExpired():false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package fct.unl.pt.csdw1.Security;

import fct.unl.pt.csdw1.Repositories.OurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final OurRepo ourRepo;

    @Autowired
    public MyUserDetailService(final OurRepo ourRepo) {
        this.ourRepo = ourRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new MyUserDetails(s, ourRepo);
    }
}

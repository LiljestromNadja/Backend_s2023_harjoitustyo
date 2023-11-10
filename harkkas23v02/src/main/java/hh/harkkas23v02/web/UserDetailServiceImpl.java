 package hh.harkkas23v02.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.harkkas23v02.domain.ApplicationUser;
import hh.harkkas23v02.domain.ApplicationUserRepository;

/**
 * This class is used by spring security to authenticate and authorize user
 **/

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final ApplicationUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(ApplicationUserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	ApplicationUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        System.out.println("LOG UserDetailService, KIRJAUTUNUT KÄYTTÄJÄROOLI: " + curruser.getRole());
        System.out.println("user id: " + curruser.getId());
        return user;
    }   
} 
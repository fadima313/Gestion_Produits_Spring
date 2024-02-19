package sn.esmt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.esmt.entites.*;
import sn.esmt.repository.*;

@Service
public class UserDetailsServiceImp implements UserDetailsService
{
	@Autowired
    private UserRepository urepo;
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
	   org.springframework.security.core.userdetails.User springUser= null;
	
	   User user = urepo.findByEmail(email);
	   if (user==null)
         throw new UsernameNotFoundException("email inexistant !!!!");
	   else
	   { 
		   List<Role> roles = user.getRoles();
		   List<String> noms_roles = new ArrayList<String>();
		   for ( Role r : roles)
			   noms_roles.add(r.getRoleName());
		   
		   Set<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
		   for (String nr : noms_roles)
			   ga.add(new SimpleGrantedAuthority(nr));
            
	  springUser = new org.springframework.security.core.userdetails.User(user.getEmail(),
			  user.getPassword(),ga);
				   
				  return springUser;
		   
	   }
    }

}

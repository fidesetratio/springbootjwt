package com.app.conf;

import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DefaultCustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		 if (authentication.getName() == null || authentication.getCredentials() == null) {
	            return null;
	        }

		    final String userEmail = authentication.getName();
	        final Object userPassword = authentication.getCredentials();

		 String validUserEmail = "patartimotius";
	        String validUserPassword = "evievi123";
	        if (userEmail.equalsIgnoreCase(validUserEmail)
	                && userPassword.equals(validUserPassword)) {
	            return new UsernamePasswordAuthenticationToken(
	                    userEmail, userPassword, getAuthority());
	        }
	        throw new UsernameNotFoundException("Invalid username or password");
	    }

	
	@Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<SimpleGrantedAuthority> getAuthority() {
    	  return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    	  
    }
    
}

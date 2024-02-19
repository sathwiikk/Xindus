package ecommerce.solutions.XindusTrade.EcommerceService;


import ecommerce.solutions.XindusTrade.EcommerceEntity.Users;
import ecommerce.solutions.XindusTrade.EcommerceRepository.UserRepository;
import ecommerce.solutions.XindusTrade.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Retrieve the user from the UserRepository based on the provided username
        Users user = userRepository.findByUsername(username).orElse(null);

        // If the user is not found, throw a UserNotFoundException
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        // Create a SimpleGrantedAuthority representing the "user" role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");

        // Create and return a UserDetails object representing the authenticated user
        // UserDetails is an interface provided by Spring Security for representing user details
        // Here, we use org.springframework.security.core.userdetails.User implementation
        // This constructor takes the username, password, and a collection of granted authorities
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Set.of(authority));
    }
}

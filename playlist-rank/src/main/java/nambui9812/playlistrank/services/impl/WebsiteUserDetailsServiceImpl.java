package nambui9812.playlistrank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;

@Service
public class WebsiteUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private WebsiteUserRepository websiteUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebsiteUser user = websiteUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}

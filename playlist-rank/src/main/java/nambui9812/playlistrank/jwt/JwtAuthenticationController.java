package nambui9812.playlistrank.jwt;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nambui9812.playlistrank.services.impl.WebsiteUserDetailsServiceImpl;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private WebsiteUserDetailsServiceImpl websiteUserDetailsServiceImpl;

  // Sign in action
  @PostMapping("/users/sign-in")
  public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    // Check username and password from user request
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    HashMap<String, Object> res = new HashMap<>();

    // Get user detail object from username
    final UserDetails userDetails = websiteUserDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
    
    // Create token
    final String token = jwtTokenUtil.generateToken(userDetails);

    res.put("message", "Sign in successfully.");
    res.put("username", userDetails.getUsername());
    res.put("token", token);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      // Account is disable
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      // Invalid credential
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}

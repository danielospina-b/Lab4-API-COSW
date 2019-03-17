package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.servlet.ServletException;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@CrossOrigin(origins = {"http://localhost:3000", "https://task-planner-cosw.firebaseapp.com/"})
@RestController
@RequestMapping("user")
public class UserController
{

    @Autowired
    private UserService userService;

    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public Token login(@RequestBody User login) throws ServletException {

        String jwtToken = "";

        if ( login.getEmail() == null || login.getPassword() == null )
        {
            throw new ServletException( "Please fill in username and password" );
        }

        String usermail = login.getEmail();
        String password = login.getPassword();

        User user = userService.findUserByEmail(usermail);

        if ( user == null )
        {
            throw new ServletException( "User username not found." );
        }

        if ( !password.equals(user.getPassword()) && !usermail.equals(user.getEmail())) {
            throw new ServletException( "Invalid login. Please check your email and password." );
        }

        jwtToken = Jwts.builder().setSubject( usermail ).claim( "roles", "user" ).setIssuedAt( new Date() ).signWith(
            SignatureAlgorithm.HS256, "secretkey" ).compact();

        return new Token( jwtToken );
    }

    public class Token
    {

        String access_token;


        public Token( String access_token )
        {
            this.access_token = access_token;
        }


        public String getAccessToken()
        {
            return access_token;
        }

        public void setAccessToken( String access_token )
        {
            this.access_token = access_token;
        }
    }

    @RequestMapping( value = "/info", method = RequestMethod.GET )
    public User getUserInfo(@RequestParam String email) throws ServletException {
        return userService.findUserByEmail(email);
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public Token registerUser(@RequestBody User user) throws ServletException {
        userService.createUser(user);
        return login(user);
    } 
}

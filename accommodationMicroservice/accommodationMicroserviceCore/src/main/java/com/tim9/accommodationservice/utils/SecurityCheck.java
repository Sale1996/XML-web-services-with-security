package com.tim9.accommodationservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityCheck {


public boolean check(String user, Authentication authentication) {
    if(user == null)
        return false;
   if(user.equals(authentication.getName()))    
       return true;
   // more logic ... 
   return false;
 }
}

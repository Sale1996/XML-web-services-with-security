package com.tim9.zuulrouter.utils;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint {//implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
//        
//        // How to customize the retured message?
//        // Link: https://stackoverflow.com/a/40791087
//        String json = String.format("{\"message\": \"%s\"}", e.getMessage());
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);       
//    }
}

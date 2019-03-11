package org.rcisoft.business.test.auth.controller;

import org.rcisoft.base.result.Result;
import org.rcisoft.entity.SysUser;
import org.rcisoft.business.test.auth.service.AuthServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthControllerTest {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthServiceTest authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createAuthenticationToken(String username, String password) throws AuthenticationException{
        //  @RequestBody JwtAuthenticationRequest authenticationRequest
        final String token = authService.login(username, password);
        // Return the token
        return token;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return refreshedToken;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(SysUser addedUser) throws AuthenticationException{
        Integer result = authService.register(addedUser);
        return Result.result(result, result);
    }



    @RequestMapping("/loginPage")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

}

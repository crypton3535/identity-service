package org.csspec.IdentityService.config;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.db.Account;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.csspec.IdentityService.Exceptions.InvalidHeaderException;

import javax.servlet.http.Cookie;
import java.util.*;

public class RequestValidator {
    static public final String uri = "http://139.59.36.12:8090/oauth/check_token";
    private static final String COOKIE_NAME = "FEEDBACK_CSSPEC";
    private static final String SESSION_COOKIE_NAME = "CSS_FEEDBACK_SESSION_USER_ID";
    private static final String SESSION_TOKEN = "SESSION_ACCESS_TOKEN";
    // store the secret as an environment variable
    private static final String SECRET = "secret";
    private static final String CLIENT = "feedback";

    public static Set<String> makeSet(String ...args) {
        Set<String> temp = new HashSet<>();
        for(int i = 0;i<args.length;i++) {
            temp.add(args[i]);
        }
        return temp;
    }

    public static Account validateRequest(String hash) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer " + hash));
        interceptors.add(new HeaderRequestInterceptor("x-auth-token", "client_id=" + CLIENT + "&client_secret=" + SECRET));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        RestTemplate template = new RestTemplate();
        template.setInterceptors(interceptors);
        return template.postForObject(RequestValidator.uri, null, Account.class);
    }

    public static Account checkHeader(HttpServletRequest request,String... args) {
      /*  Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        String cookiePayload = "";
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                cookiePayload = cookie.getValue();
                break;
            }
        }
        if (cookiePayload.equals("")) {
            System.out.println(COOKIE_NAME + " was null or \"\"");
            return null;
        }

        String hash = "";
        try {
            hash = Jwt.verifyJwt(cookiePayload);
        } catch (Exception e) {
            System.out.println("Unable to verify token: " + e.toString());
            return null;
      ///  }*/
    Set<String> role = makeSet(args);
        String header = request.getHeader("Authorization");
        if(header.startsWith("Bearer")) {
            System.out.println(header.substring(7, header.length()));
            Account account =  validateRequest(header.substring(7,header.length()));
            if(role.contains(account.getRole())) {
                return account;
            } else {
                return new Account();
            }
        }
        return new Account();
    }

}
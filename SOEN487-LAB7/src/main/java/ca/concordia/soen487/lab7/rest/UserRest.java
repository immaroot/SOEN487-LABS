package ca.concordia.soen487.lab7.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Path("/user")
public class UserRest {

    private static final List<User> users = new ArrayList<>();
    private static final Map<String, String> tokenUsername = new HashMap<>();
    private static final Map<String, Date> tokenExpiration = new HashMap<>();

    @POST
    @Path("/register")
    public String createUser(@FormParam("username") String username, @FormParam("password") String password) {
        User user = new User(username, password);
        users.add(user);
        return "Created user: " + user.getUsername();
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        MyResponse authResponse;
        Response.Status status;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.generateToken();
                tokenUsername.put(user.getToken(), username);
                tokenExpiration.put(user.getToken(), new Date());
                authResponse = new MyResponse(true, user.getToken());
                status = Response.Status.OK;
            } else {
                authResponse = new MyResponse(false, "");
                status = Response.Status.UNAUTHORIZED;
            }
        } else {
            authResponse = new MyResponse(false, "");
            status = Response.Status.FORBIDDEN;
        }
        return Response.status(status).entity(authResponse).build();
    }

    @POST
    @Path("/logout")
    public String logout(@FormParam("username") String username) {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user != null) {
            if (user.getToken().equals("")) {
                return "User is not logged in.";
            } else {
                tokenUsername.remove(user.getToken());
                tokenExpiration.remove(user.getToken());
                user.destroyToken();
                return "User has been logged out and token has been destroyed";
            }
        } else {
            return "User does not exists.";
        }
    }

    @POST
    @Path("/auth")
    public Response validateToken(@HeaderParam("x-api-key") String token) {
        if (tokenUsername.containsKey(token)) {
            Date now = new Date();
            long diff = now.getTime() - tokenExpiration.get(token).getTime();
            long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(diff);
            if (tokenDuration > 30) {
                tokenUsername.remove(token);
                tokenExpiration.remove(token);
            } else {
                return Response.status(Response.Status.OK).entity("true").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("false").build();
    }
}

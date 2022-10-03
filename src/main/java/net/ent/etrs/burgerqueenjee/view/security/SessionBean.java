package net.ent.etrs.burgerqueenjee.view.security;

import lombok.Getter;
import net.ent.etrs.boutik.model.entities.Role;
import net.ent.etrs.boutik.model.entities.User;
import net.ent.etrs.boutik.model.facades.FacadeUser;
import net.ent.etrs.boutik.utils.Hash;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    
    @Inject
    private FacadeUser facadeUser;
    
    @Getter
    private User user;
    
    private JSONObject securityJsonObject;
    
    @PostConstruct
    public void init() {
        try {
            JSONParser jsonParser = new JSONParser();
            InputStream inputStream = this.getClass().getResourceAsStream("/config/security.json");
            this.securityJsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean login(String login, String password) {
        Optional<User> u = this.facadeUser.findByLogin(login);
        if (u.isEmpty()) {
            return false;
        }
        User userCandidate = u.get();
        if (Hash.checkPassword(password, userCandidate.getPassword())) {
            this.user = userCandidate;
            return true;
        }
        return false;
    }
    
    public void disconnect() throws IOException {
        this.user = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/security/login.xhtml");
    }
    
    public boolean isAuthorized(String uri) {
        List<String> uris = new ArrayList<>();
        if (user != null) {
            for (Role r : this.user.getRole().getAllRoles()) {
                uris.addAll(this.getListUriFromRole(r.name()));
            }
        }
        uris.addAll(this.getListUriFromRole("ALWAYS"));
        return uris.contains(uri);
    }
    public boolean canAction(String action) {
        List<String> actions = new ArrayList<>();
        if (user != null) {
            for (Role r : this.user.getRole().getAllRoles()) {
                actions.addAll(this.getListActionsFromRole(r.name()));
            }
        }
        actions.addAll(this.getListActionsFromRole("ALWAYS"));
        return actions.contains(action);
    }
    
    public List<String> getAlwaysAuthorized() {
        return getListUriFromRole("ALWAYS");
    }
    
    private List<String> getListUriFromRole(String role) {
        List<String> uris = new ArrayList<>();
        JSONObject jsonRole = (JSONObject) this.securityJsonObject.get(role);
        JSONArray roleUris = (JSONArray) jsonRole.get("uris");
        roleUris.stream().forEach((l) -> uris.add(l.toString()));
        return uris;
    }
    
    private List<String> getListActionsFromRole(String role) {
        List<String> actions = new ArrayList<>();
        JSONObject jsonRole = (JSONObject) this.securityJsonObject.get(role);
        JSONArray jsonactions = (JSONArray) jsonRole.get("actions");
        jsonactions.stream().forEach((l) -> actions.add(l.toString()));
        return actions;
    }
    
}

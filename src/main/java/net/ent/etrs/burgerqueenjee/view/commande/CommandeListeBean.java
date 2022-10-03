package net.ent.etrs.burgerqueenjee.view.commande;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.boutik.model.entities.*;
import net.ent.etrs.boutik.model.facades.FacadeUser;
import net.ent.etrs.boutik.utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class CommandeListeBean implements Serializable {
    
    public final static String FLASH_COMMANDE = "FLASH_COMMANDE";

    @Getter
    @Setter
    @Inject
    private LazyDataModelCommande commandeList;
    
    @Inject
    private FacadeUser facadeUser;
    
    @Getter
    @Setter
    private List<User> userList;
    
    @Getter
    private List<EtatCommande> etatCommandeList = List.of(EtatCommande.values());

    @PostConstruct
    public void init() {
        try {
            this.userList = this.facadeUser.findAllClient().stream().collect(Collectors.toList());
        } catch (Exception e) {
            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors du chargement de la page.");
        }
    }
    
    public void modifier(Commande commande) {
        JsfUtils.putInFlashScope(CommandeListeBean.FLASH_COMMANDE, commande);
    }
    
    public String getBadge(EtatCommande etatCommande) {
        String r = null;
        switch (etatCommande) {
            case VALIDEE:
                r = "warning";
                break;
            case CONFIRMEE:
                r = "info";
                break;
            case EXPEDIEE:
            case RECUE:
                r = "success";
                break;
            case ANNULEE:
                r = "danger";
                break;
        }
        return r;
    }
}

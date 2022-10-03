package net.ent.etrs.burgerqueenjee.view.commande;

import lombok.Getter;
import net.ent.etrs.boutik.model.entities.*;
import net.ent.etrs.boutik.model.facades.FacadeCommande;
import net.ent.etrs.boutik.utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class CommandeFicheBean implements Serializable {
    
    @Inject
    private FacadeCommande facadeCommande;
    
//    @Inject
    @Getter
    private Commande commande;
    
    @Getter
    private List<EtatCommande> prochainsEtats;
    
    @PostConstruct
    public void init() {
        try {
            Commande c = (Commande) JsfUtils.getFromFlashScope(CommandeListeBean.FLASH_COMMANDE);
            Optional<Commande> optCommande = this.facadeCommande.findByIdWithProduits(c.getId());
            if (optCommande.isPresent()) {
                this.commande = optCommande.get();
                this.prochainsEtats = this.commande.getEtat().getProchains();
            }
        } catch (Exception e) {
            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors du chargement de la page.");
        }
    }
    
//    public void valider() {
//        try {
//            this.facadeCommande.save(this.commande);
//            JsfUtils.sendMessage(FacesMessage.SEVERITY_INFO,"Commande enregistré avec succès.");
//        } catch (Exception e) {
//            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors de l'enregistrement du commande.");
//        }
//    }
    
    public void changeEtat(EtatCommande etatCommande) {
        try {
            this.commande.setEtat(etatCommande);
            this.facadeCommande.save(commande);
            this.prochainsEtats = this.commande.getEtat().getProchains();
        } catch (Exception e) {
            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors de l'enregistrement du commande.");
        }
    }
}

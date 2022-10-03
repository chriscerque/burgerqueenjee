package net.ent.etrs.burgerqueenjee.view.produit;


import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.burgerqueenjee.model.entities.Produit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TailleProduit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TypeProduit;
import net.ent.etrs.burgerqueenjee.model.facades.FacadeProduit;
import net.ent.etrs.burgerqueenjee.utils.JsfUtils;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class ProduitFicheBean implements Serializable {

    @Inject
    private FacadeProduit facadeProduit;


    @Inject
    @Getter
    private Produit produit;

    @Getter
    private List<TypeProduit> typeProduits = new ArrayList<>();

    @Getter
    private List<TailleProduit> tailleProduits = new ArrayList<>();

    @Getter
    @Setter
    private UploadedFile file;

    @PostConstruct
    public void init() {
        try {
            this.typeProduits.addAll(this.facadeCategorie.findAll());
            this.tailleProduits.addAll(this.facadeMarque.findAll());
            Produit p = (Produit) JsfUtils.getFromFlashScope(ProduitListeBean.FLASH_PRODUIT);
            if (Objects.nonNull(p)) {
                this.produit = p;
            }
        } catch (Exception e) {
            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors du chargement de la page.");
        }
    }

    public void valider() {
        try {
            if (this.file != null && this.file.getFileName() != null) {
                this.produit.setImage(this.file.getContent());
            }
            this.facadeProduit.save(this.produit);
            JsfUtils.sendMessage(FacesMessage.SEVERITY_INFO, "Produit enregistré avec succès.");
        } catch (Exception e) {
            JsfUtils.sendMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'enregistrement du produit.");
        }
    }
}

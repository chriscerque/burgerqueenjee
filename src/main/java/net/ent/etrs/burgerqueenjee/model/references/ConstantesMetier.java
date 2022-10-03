package net.ent.etrs.burgerqueenjee.model.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 * @author christophe.cerqueira
 *
 */
public class ConstantesMetier {


    public static final String MSG_NOM_NON_VIDE = "Le nom doit être renseigné.";
    public static final String MSG_NOM_TAILLE_MIN = "Le nom doit contenir au minimum 3 caractères.";
    public static final String MSG_PRENOM_NON_VIDE = "Le nom doit être renseigné.";
    public static final String MSG_PRENOM_TAILLE_MIN = "Le nom doit contenir au minimum 3 caractères.";
    public static final String MSG_MAIL_NON_VIDE = "L'adresse mail doit être renseignée.";
    public static final String MSG_TAILLE_PRODUIT_NULL = "La taille du produit ne peut être null";
    public static final String MSG_TYPE_FRITE_NULL = "Le type de frite ne peut être null";
    public static final String MSG_CLIENT_NON_NULL = "Le client ne peut être null.";
    public static final String MSG_AJOUTER_PRODUIT_EXCEPTION = "Le produit a ajouter est null.";
    public static final String MSG_RETIRER_PRODUIT_EXCEPTION = "Le produit a retirer est null.";
    public final static String PATTERN_FORMAT_DATE = "dd/MM/yyyy";
    public static final String MSG_CLIENT_NON_SELECTIONNE = "Aucun client sélectionné.";
    public static final String MSG_TYPE_PRODUIT_NULL = "Le type de produit ne peut être null";
    public static final String MSG_COMMANDE_CONSTRUCTION_IMPOSSIBLE = "Erreur lors de la création d'une commande.";
    public static final String MSG_LISTE_PRODUIT_VIDE = "La liste des produits est vide.";
    public static final String MSG_AUCUN_PRODUIT_SELECTIONNE = "Aucun produit sélectionné.";
    public static final String MSG_ERREUR_INIT_COMMANDE = "Une erreur s'est produite lors de l'initialisation des données en base.";
    public static final String MSG_SUPPRESSION_IMPOSSIBLE_COMMANDE_EXISTANTE = "Le client possède des commandes, il n'est pas possible de le supprimer.";
    public static final String MSG_MAIL_NON_VALIDE = "Le format de l'adresse courriel est incorrect.";
    public static final String MSG_MAP_PRODUIT_NON_NULL = "La collection de produits ne peut être null.";

    public static final String MSG_PRIX_NON_VALIDE = "Le prix est non valide.";
    public static final String MSG_PRIX_NULL = "Le prix doit être renseigné.";
    public static final int CLIENT_NOM_TAILLE_MIN = 3;
    public static final int CLIENT_NOM_TAILLE_MAX = 150;
    public static final int CLIENT_PRENOM_TAILLE_MIN = 3;
    public static final int CLIENT_PRENOM_TAILLE_MAX = 150;
    public static final int PRODUI_NOM_TAILLE_MAX = 50;
    public static final int PRODUI_NOM_TAILLE_MIN = 2;
    public static final String MSG_COMMANDE_DATE_NON_VALIDE = "La date ne peut être dans le futur.";
    public static final String MSG_COMMANDE_DATE_NULL = "La date doit être renseignée.";
}

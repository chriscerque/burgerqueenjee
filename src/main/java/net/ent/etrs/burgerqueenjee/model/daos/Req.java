package net.ent.etrs.burgerqueenjee.model.daos;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 * @author christophe.cerqueira
 *
 */
public final class Req {
;

	public static final String CLIENT_BY_NOM_PRENOM = " SELECT c FROM Client c WHERE c.nom = :nom AND c.prenom = :prenom ";
	public static final String PRODUIT_BY_NOM_TYPE_TAILLE = " SELECT c FROM Produit c WHERE c.nom = :nom AND c.typeProduit =:typeProduit AND c.tailleProduit = :tailleProduit ";
	public static final String COMMANDE_PAR_CLIENT = " SELECT c FROM Commande c WHERE  c.client = :client";
	public static final String COMMANDE_READ_ALL = " SELECT c FROM Commande c"; 
	
}

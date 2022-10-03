package net.ent.etrs.burgerqueenjee.model.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.burgerqueenjee.model.entities.references.TailleProduit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TypeProduit;
import net.ent.etrs.burgerqueenjee.model.references.ConstantesMetier;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom","tailleProduit"})
@ToString(of = {"nom","tailleProduit","typeProduit", "prix"})

@Entity
@Table(name = "PRODUIT", uniqueConstraints = @UniqueConstraint(name = "PRODUIT__NOM__TAILLE_UK", columnNames = {"NOM", "TAILLE_PRODUIT"}))

public class Produit extends AbstractEntity {

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_NOM_NON_VIDE)
    @NotEmpty(message = ConstantesMetier.MSG_NOM_NON_VIDE)
    @Length(min = ConstantesMetier.PRODUI_NOM_TAILLE_MIN, max = ConstantesMetier.PRODUI_NOM_TAILLE_MAX, message = ConstantesMetier.MSG_NOM_TAILLE_MIN)
    @Column(name = "NOM", length = ConstantesMetier.PRODUI_NOM_TAILLE_MAX, nullable = false)
    String nom;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_PRIX_NULL)
    @Positive(message = ConstantesMetier.MSG_PRIX_NON_VALIDE)
    @Column(name = "PRIX", nullable = false)
    Float prix;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_TYPE_PRODUIT_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PRODUIT", nullable = false)
    TypeProduit typeProduit;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_TAILLE_PRODUIT_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "TAILLE_PRODUIT", nullable = false)
    TailleProduit tailleProduit;

}

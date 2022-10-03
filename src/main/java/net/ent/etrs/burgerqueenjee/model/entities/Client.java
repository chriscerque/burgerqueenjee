package net.ent.etrs.burgerqueenjee.model.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.burgerqueenjee.model.references.ConstantesMetier;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom", "prenom", "courriel"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"courriel"})

@Entity
@Table(name = "CLIENT", uniqueConstraints = @UniqueConstraint(name = "CLIENT__COURRIEL__UK", columnNames = "COURRIEL"))

public class Client extends User {

    @Getter
    @Setter
    @NotEmpty(message = ConstantesMetier.MSG_NOM_NON_VIDE)
    @Length(min = ConstantesMetier.CLIENT_NOM_TAILLE_MIN, max = ConstantesMetier.CLIENT_NOM_TAILLE_MAX, message = ConstantesMetier.MSG_NOM_TAILLE_MIN)
    @Column(name = "NOM", length = ConstantesMetier.CLIENT_NOM_TAILLE_MAX, nullable = false)
    String nom;

    @Getter
    @Setter
    @NotEmpty(message = ConstantesMetier.MSG_PRENOM_NON_VIDE)
    @Length(min = ConstantesMetier.CLIENT_PRENOM_TAILLE_MIN, max = ConstantesMetier.CLIENT_PRENOM_TAILLE_MAX, message = ConstantesMetier.MSG_PRENOM_TAILLE_MIN)
    @Column(name = "PRENOM", length = ConstantesMetier.CLIENT_PRENOM_TAILLE_MAX, nullable = false)
    String prenom;

    @Getter
    @Setter
    @NotEmpty(message = ConstantesMetier.MSG_MAIL_NON_VIDE)
    @Email(message = ConstantesMetier.MSG_MAIL_NON_VALIDE)
    String courriel;



}

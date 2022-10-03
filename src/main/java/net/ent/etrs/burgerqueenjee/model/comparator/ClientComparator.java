package net.ent.etrs.burgerqueenjee.model.comparator;


import net.ent.etrs.burgerqueenjee.model.entities.Client;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {
        //je compare les r�f�rences
        if (o1 == o2) {
            return 0;
        }
        //je compare les noms
        int compar = o1.getNom().compareTo(o2.getNom());

        //si les noms sont identiques
        if (compar == 0) {
            //je compare les pr�noms
            return o1.getPrenom().compareTo(o2.getPrenom());
        }

        return compar;
    }

}

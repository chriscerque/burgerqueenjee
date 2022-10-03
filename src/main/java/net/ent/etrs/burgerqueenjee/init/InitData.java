package net.ent.etrs.burgerqueenjee.init;

import com.github.javafaker.Faker;
import net.ent.etrs.boutik.model.entities.*;
import net.ent.etrs.boutik.model.facades.*;
import net.ent.etrs.boutik.utils.Hash;
import org.apache.commons.collections4.IterableUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Startup
@Singleton
public class InitData {

    private Faker faker = Faker.instance(Locale.FRANCE);

    @Inject
    private FacadeCategorie facadeCategorie;

    @Inject
    private FacadeMarque facadeMarque;
    
    @Inject
    private FacadeProduit facadeProduit;
    
    @Inject
    private FacadeUser facadeUser;
    
    @Inject
    private FacadeCommande facadeCommande;

    @PostConstruct
    public void init() {
        try {
            this.initUser();
            this.initCategorie();
            this.initMarque();
            this.initProduit();
            this.initCommandes();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void initUser() throws Exception {
        if (this.facadeUser.count() == 0) {
            User user = new User();
            user.setLogin("USER");
            user.setPassword(Hash.hash("USER"));
            user.setRole(Role.USER);
            this.facadeUser.save(user);
    
            User admin = new User();
            admin.setLogin("ADMIN");
            admin.setPassword(Hash.hash("ADMIN"));
            admin.setRole(Role.ADMIN);
            this.facadeUser.save(admin);
    
            for (int i = 0; i < 30; i++) {
                User u = new User();
                u.setLogin("USER" + i);
                u.setPassword(Hash.hash("USER" + i));
                u.setRole(Role.USER);
                this.facadeUser.save(u);
            }
        }
    }
    
    private void initMarque() {
        try {
            if (this.facadeMarque.count()==0) {
                Set<Marque> marques = new HashSet<>();
                do {
                    Marque m = new Marque();
                    m.setLibelle(this.faker.company().name());
                    marques.add(m);
                } while (marques.size() != 50);
    
                for (Marque m : marques) {
                    this.facadeMarque.save(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCategorie() {
        try {
            if (this.facadeCategorie.count()==0) {
                Set<Categorie> categories = new HashSet<Categorie>();
                do {
                    Categorie c = new Categorie();
                    c.setLibelle(this.faker.funnyName().name());
                    categories.add(c);
                } while (categories.size() != 50);
    
                for (Categorie c: categories) {
                    this.facadeCategorie.save(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initProduit() {
        try {
            if (this.facadeProduit.count()==0) {
                List<Categorie> categorieList = IterableUtils.toList(this.facadeCategorie.findAll());
                List<Marque> marqueList = IterableUtils.toList(this.facadeMarque.findAll());
                Set<Produit> produits = new HashSet<>();
                do {
                    Produit p = new Produit();
                    p.setLibelle(this.faker.commerce().material());
                    p.setDescription(this.faker.lorem().paragraph(2));
                    p.setPrixHT(this.generateRandomFloat(0.99f, 4999.99f));
                    p.setPoidsKg(this.generateRandomFloat(0.1f, 50f));
                    p.setMarque(marqueList.get(this.faker.random().nextInt(0, marqueList.size()-1)));
                    p.setCategorie(categorieList.get(this.faker.random().nextInt(0, categorieList.size()-1)));
                    produits.add(p);
                } while (produits.size() != 500);
                
                for (Produit p : produits) {
                    this.facadeProduit.save(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initCommandes() {
        try {
            if (this.facadeCommande.count()==0) {
                List<Produit> produitList = IterableUtils.toList(this.facadeProduit.findAll());
                List<User> userList = IterableUtils.toList(this.facadeUser.findAllClient());
                
                Set<Commande> commandes = new HashSet<>();
                do {
                    Commande c = new Commande();
                    c.setCreatedAt(this.generateRandomLocalDateTimePast());
                    c.setUser(userList.get(this.faker.random().nextInt(0, userList.size()-1)));
                    c.setEtat(EtatCommande.values()[this.faker.random().nextInt(0, EtatCommande.values().length-1)]);
                    
                    for (int i = 0; i <= this.faker.random().nextInt(1, 15); i++) {
                        c.addProduit(produitList.get(this.faker.random().nextInt(0, produitList.size()-1)), this.faker.random().nextInt(1,10));
                    }
                    commandes.add(c);
                } while (commandes.size() != 100);
                
                for (Commande c : commandes) {
                    this.facadeCommande.save(c);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private LocalDateTime generateRandomLocalDateTimePast() {
        LocalDateTime now = LocalDateTime.now();
        int year = 60*60*24*365;
        return now.minusSeconds((long) this.faker.random().nextInt(0, 5 * year));
    }
    
    private float generateRandomFloat(float min, float max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be greater than min");
        float result = ThreadLocalRandom.current().nextFloat() * (max - min) + min;
        if (result >= max) // correct for rounding
            result = Float.intBitsToFloat(Float.floatToIntBits(max) - 1);
    
        result = (float) (Math.round(result * 100.0) / 100.0);
        return result;
    }
}

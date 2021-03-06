package entiteti;
// Generated Jan 9, 2019 9:33:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Porudzbina generated by hbm2java
 */
public class Porudzbina  implements java.io.Serializable {


     private Integer idporudzbina;
     private Korisnik korisnik;
     private int cena;
     private Set<Stavka> stavkas = new HashSet<Stavka>(0);

    public Porudzbina() {
    }

	
    public Porudzbina(int cena) {
        this.cena = cena;
    }
    public Porudzbina(Korisnik korisnik, int cena, Set<Stavka> stavkas) {
       this.korisnik = korisnik;
       this.cena = cena;
       this.stavkas = stavkas;
    }
   
    public Integer getIdporudzbina() {
        return this.idporudzbina;
    }
    
    public void setIdporudzbina(Integer idporudzbina) {
        this.idporudzbina = idporudzbina;
    }
    public Korisnik getKorisnik() {
        return this.korisnik;
    }
    
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    public int getCena() {
        return this.cena;
    }
    
    public void setCena(int cena) {
        this.cena = cena;
    }
    public Set<Stavka> getStavkas() {
        return this.stavkas;
    }
    
    public void setStavkas(Set<Stavka> stavkas) {
        this.stavkas = stavkas;
    }




}



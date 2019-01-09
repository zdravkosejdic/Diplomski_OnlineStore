package entiteti;
// Generated Jan 9, 2019 9:33:01 PM by Hibernate Tools 4.3.1



/**
 * Stavka generated by hbm2java
 */
public class Stavka  implements java.io.Serializable {


     private Integer idstavka;
     private Artikal artikal;
     private Porudzbina porudzbina;
     private int kolicina;

    public Stavka() {
    }

	
    public Stavka(int kolicina) {
        this.kolicina = kolicina;
    }
    public Stavka(Artikal artikal, Porudzbina porudzbina, int kolicina) {
       this.artikal = artikal;
       this.porudzbina = porudzbina;
       this.kolicina = kolicina;
    }
   
    public Integer getIdstavka() {
        return this.idstavka;
    }
    
    public void setIdstavka(Integer idstavka) {
        this.idstavka = idstavka;
    }
    public Artikal getArtikal() {
        return this.artikal;
    }
    
    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }
    public Porudzbina getPorudzbina() {
        return this.porudzbina;
    }
    
    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }
    public int getKolicina() {
        return this.kolicina;
    }
    
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }




}


package sn.esmt.entites;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="produits")
public class Produit implements Serializable
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @NotEmpty(message="nom obligatoire")
  private String nomprod;
  @NotNull(message="prix obligatoire")
  private Integer prix;
  
  @Transient
  private String numcateg;
  
  @ManyToOne
  @JoinColumn(name="codecateg")
   private Categorie categorie;
  
  public Produit()
  {
	  
  }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getNomprod() {
	return nomprod;
}

public void setNomprod(String nomprod) {
	this.nomprod = nomprod;
}

public Integer getPrix() {
	return prix;
}

public void setPrix(Integer prix) {
	this.prix = prix;
}

public Categorie getCategorie() {
	return categorie;
}

public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

public String getNumcateg() {
	return numcateg;
}

public void setNumcateg(String numcateg) {
	this.numcateg = numcateg;
}

}

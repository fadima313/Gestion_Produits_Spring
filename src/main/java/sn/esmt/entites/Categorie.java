package sn.esmt.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="categories")
public class Categorie implements Serializable
{
	@Id
	@Column(length=25)
	@NotEmpty(message="code obligatoire")
	private String codecateg;
	@NotEmpty(message="nom obligatoire")
	private String nomcateg;

	@OneToMany(mappedBy="categorie",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Produit> listeprod = new ArrayList<Produit>();
	
	
	public Categorie()
	{
		
	}


	public String getCodecateg() {
		return codecateg;
	}


	public void setCodecateg(String codecateg) {
		this.codecateg = codecateg;
	}


	public String getNomcateg() {
		return nomcateg;
	}


	public void setNomcateg(String nomcateg) {
		this.nomcateg = nomcateg;
	}


	public List<Produit> getListeprod() {
		return listeprod;
	}


	public void setListeprod(List<Produit> listeprod) {
		this.listeprod = listeprod;
	}
	
	
}

package sn.esmt.controleur;

import sn.esmt.entites.*;

import java.util.List;

import javax.naming.BinaryRefAddr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sn.esmt.dao.*;

@Controller
public class Controleur
{
  @Autowired	
  private AccesBD bd;
  
  @GetMapping("/")
  public String accueil()
  {
	  return "index";
  }
  
  @GetMapping("/gescateg")
  public String listeDesCategories(Model modele)
  {
	  List<Categorie> listecateg=bd.listAllCategories();
	  modele.addAttribute("listecateg", listecateg);
	  return "listecategories";
  }
  
  @GetMapping("/creercateg")
  public String chargeFormulaireCategorie(Model modele)
  {
	 Categorie categorie = new Categorie();
	 modele.addAttribute("categorie", categorie);
	  return "ajoutcategorie";
  }
  
  @PostMapping("/enregistrecateg")
  public String enregistreCategorie(@Valid @ModelAttribute("categorie") Categorie categorie,BindingResult resultats)
  {
	 if (resultats.hasErrors())
		 return "ajoutcategorie";
	 bd.saveCategory(categorie);
	 return "redirect:/gescateg";
  }
  
  @GetMapping("/supcateg/{codecateg}")
  public String supprimeCategorie(@PathVariable("codecateg") String codecateg)
  {
	  bd.deleteCategory(codecateg);
	  return "redirect:/gescateg";
  }
  
  
  @GetMapping("/editercateg/{codecateg}")
  public String rechercheCategorie(@PathVariable("codecateg") String codecateg,Model modele)
  {
	  Categorie categorie = bd.findCategory(codecateg);
	  modele.addAttribute("categorie", categorie);  
	  return "modifcategorie";
	  
  }

  
  @PostMapping("/modifiecateg")
  public String modifieCategorie(@Valid @ModelAttribute("categorie") Categorie categorie,BindingResult resultats)
  {
	 if (resultats.hasErrors())
		 return "modifcategorie";
	 bd.saveCategory(categorie);
	 return "redirect:/gescateg";
  }

  @GetMapping("/gesprod")
  public String listeDesProduits(Model modele)
  {
	  List<Produit> listeprod=bd.listAllProducts();
	  modele.addAttribute("listeprod", listeprod);
	  return "listeproduits";
  }

  @GetMapping("/creerprod")
  public String chargeFormulaireProduit(Model modele)
  {
	 Produit produit = new Produit();
	 modele.addAttribute("produit", produit);
	 List<Categorie> listecateg = bd.listAllCategories();
	 modele.addAttribute("listecateg", listecateg);
	  return "ajoutproduit";
  }
  
  @PostMapping("/enregistreprod")
  public String enregistreProduit(@Valid @ModelAttribute("produit") Produit produit,BindingResult resultats,Model modele)
  {
	 if (resultats.hasErrors())
	 {
		 List<Categorie> listecateg = bd.listAllCategories();
		 modele.addAttribute("listecateg", listecateg);
		 return "ajoutproduit";
	 }
		 
	 bd.saveProduct(produit);
	 return "redirect:/gesprod";
  }
  
  @GetMapping("/editerprod/{id}")
  public String rechercheProduit(@PathVariable("id") Integer id,Model modele)
  {
	   
	  Produit produit = bd.findProduct(id);
	  
	  if(produit!=null)
	  {
		  produit.setNumcateg(produit.getCategorie().getCodecateg());
		  modele.addAttribute("produit", produit);
		  List<Categorie> listecateg = bd.listAllCategories();
	      modele.addAttribute("listecateg", listecateg);
			 
	  }
	    
	  return "modifproduit";
	  
  }
  @PostMapping("/modifieprod")
  public String modifieProduit(@Valid @ModelAttribute("produit") Produit produit,BindingResult resultats,Model modele)
  {
	 if (resultats.hasErrors())
	 {
		 List<Categorie> listecateg = bd.listAllCategories();
	     produit.setNumcateg(produit.getNumcateg());
	     modele.addAttribute("produit",produit);
	     modele.addAttribute("listecateg",listecateg);
		 return "modifproduit";
	 }
	   
	 bd.updateProduct(produit);
	 return "redirect:/gesprod";
  }
  @GetMapping("/supprod/{id}")
  public String supprimeProduit(@PathVariable("id") Integer id)
  {
	  bd.deleteProduct(id);
	  return "redirect:/gesprod";
  }

  @GetMapping("/listebycateg")
  public String chargeListeDeroulanteCategories(Model modele)
  {
	  List<Categorie> listecateg=bd.listAllCategories();
	  modele.addAttribute("listecateg", listecateg);
	  ListeDeroulante ld = new ListeDeroulante();
	  modele.addAttribute("ld", ld);
	  return "selectioncateg";
  }
 
  @PostMapping("/selectcateg")
  public String trouveListeProduits(@ModelAttribute("ld") ListeDeroulante ld,Model modele)
  {
	 Categorie c = bd.findCategory(ld.getNumcateg());
	 List<Produit> listeprod = c.getListeprod();
	 modele.addAttribute("listeprod", listeprod);
	 String nomcateg = c.getNomcateg();
	 modele.addAttribute("nomcateg", nomcateg);
	  return "listeproduitscateg";
  }

}

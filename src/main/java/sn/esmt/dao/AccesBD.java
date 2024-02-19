package sn.esmt.dao;

import sn.esmt.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sn.esmt.entites.*;

@Service
public class AccesBD
{
   @Autowired	
   private CategorieRepository crepo;
   @Autowired
   private ProduitRepository  prepo;
   @Autowired
   private UserRepository urepo;
   @Autowired
   private RoleRepository rrepo;
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Transactional
   public void createRole(Role role)
   {
	   rrepo.save(role);
   }

   @Transactional
   public void createUser(User user,String roleName)
   {
	   Role role  = rrepo.findByRoleName(roleName);
	   user.setPassword(passwordEncoder.encode(user.getPassword()));
	   user.setRoles(Arrays.asList(role));
       urepo.save(user);	   

   }
   
   @Transactional
   public void createUser(User user,String roleName1,String roleName2)
   {
	   Role role1  = rrepo.findByRoleName(roleName1);
	   Role role2  = rrepo.findByRoleName(roleName2);
	   user.setPassword(passwordEncoder.encode(user.getPassword()));
	   user.setRoles(Arrays.asList(role1,role2));
       urepo.save(user);	   

   }
   
   
   
   
   @Transactional
   public void saveCategory(Categorie categorie)
   {
	   crepo.save(categorie);
   }
   
   
   public List<Categorie> listAllCategories()
   {
	   List<Categorie> listecateg=crepo.findAll();
	   return listecateg;
   }
   
   @Transactional
   public void deleteCategory(String codecateg)
   {
	   Categorie categorie = findCategory(codecateg);
	   if (categorie!=null)
		   crepo.delete(categorie);
   }
   

   public Categorie findCategory(String codecateg)
   {
	   Categorie categorie=null;
	   categorie = crepo.findByCodecateg(codecateg);
	   return categorie;
   }
  
   
   public List<Produit> listAllProducts()
   {
	   List<Produit> listeprod=prepo.findAll();
	   return listeprod;
   }
   
   @Transactional
   public void saveProduct(Produit produit)
   {
	 Categorie c = findCategory(produit.getNumcateg());
	 produit.setCategorie(c);
	 c.getListeprod().add(produit);
	 prepo.save(produit);
   }
   
   public Produit findProduct(Integer id)
   {
	   Produit produit=null;
	   Optional<Produit> optional = prepo.findById(id);
	   if (optional.isPresent())
		   produit=optional.get();
	   return produit;
	   
   }
   

   public void updateProduct(Produit produit)
   {
        Categorie c = findCategory(produit.getNumcateg());
		 produit.setCategorie(c);
		 prepo.save(produit);
	   
	   
   }
	
   @Transactional
    public void deleteProduct(Integer id)
   {
	   Produit produit =findProduct(id);
	   Categorie c = findCategory(produit.getCategorie().getCodecateg());
	   c.getListeprod().remove(produit);
	   crepo.save(c);
	   prepo.delete(produit);
	   
	   }
   
   }


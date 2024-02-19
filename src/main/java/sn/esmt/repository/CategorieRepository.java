package sn.esmt.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import sn.esmt.entites.*;

public interface CategorieRepository extends JpaRepository<Categorie,String> 
{
    Categorie findByCodecateg(String codecateg);
}

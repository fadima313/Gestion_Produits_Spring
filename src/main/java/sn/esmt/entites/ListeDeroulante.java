package sn.esmt.entites;

import java.io.Serializable;

public class ListeDeroulante implements Serializable
{
   private String numcateg;
   
   public ListeDeroulante()
   {
	   
   }

public String getNumcateg() {
	return numcateg;
}

public void setNumcateg(String numcateg) {
	this.numcateg = numcateg;
}
}

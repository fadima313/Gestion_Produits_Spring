package sn.esmt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sn.esmt.entites.*;
import sn.esmt.dao.*;

@SpringBootTest
public class TestUserRole {

 @Autowired
 private AccesBD bd;
	
 
 //   @Test
	public void testCreateRole()
	{
		Role r1 = new Role();
		r1.setRoleName("ROLE_USER");
	
		Role r2 = new Role();
		r2.setRoleName("ROLE_ADMIN");
	
	    bd.createRole(r1);
	    bd.createRole(r2);
	}

    @Test
    public void testCreateUsers()
    {
    	User user1 = new User();
    	user1.setName("aaa1");
    	user1.setUsername("esmt1");
    	user1.setEmail("esmt1@gmail.com");
    	user1.setPassword("user123");
    	bd.createUser(user1,"ROLE_USER");
    	
    	
    	User user2 = new User();
    	user2.setName("aaa2");
    	user2.setUsername("esmt2");
    	user2.setEmail("esmt2@gmail.com");
    	user2.setPassword("admin123");
    	bd.createUser(user2,"ROLE_ADMIN");
    
	    User user3 = new User();
    	user3.setName("aaa3");
    	user3.setUsername("esmt3");
    	user3.setEmail("esmt3@gmail.com");
    	user3.setPassword("admin456");
    	bd.createUser(user3,"ROLE_ADMIN","ROLE_USER");
    
    	
    }
}

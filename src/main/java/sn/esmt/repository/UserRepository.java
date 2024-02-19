package sn.esmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.esmt.entites.*;

public interface UserRepository extends JpaRepository<User,Long>
{
     public User findByEmail(String email);
     public User findByUsername(String username);
}

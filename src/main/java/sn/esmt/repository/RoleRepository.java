package sn.esmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.esmt.entites.Role;

public interface RoleRepository extends JpaRepository<Role,Long>
{
    public Role findByRoleName (String roleName);
}

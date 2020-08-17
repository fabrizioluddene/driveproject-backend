package it.diriveprojectbe.userservice.api.repository;

import it.diriveprojectbe.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("select u from User u where u.username=?1 and u.password =?2")
    public User getUserByUsernameAndPassword(String username, String password);
    @Query("select u from User u where u.username=?1")
    public User getUserByUsername(String username);
}

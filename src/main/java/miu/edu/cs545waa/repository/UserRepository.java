package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Query(value = "SELECT s FROM User s WHERE s.type = ?1")
    List<User> findByType(String typeValue);
}

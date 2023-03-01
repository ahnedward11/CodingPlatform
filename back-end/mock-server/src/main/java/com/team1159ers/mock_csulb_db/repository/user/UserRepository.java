
package com.team1159ers.mock_csulb_db.repository.user;

import com.team1159ers.mock_csulb_db.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT email FROM User")
    List<String> getAllEmails();

    @Query("SELECT user FROM User user " +
            "WHERE user.csulbId = :csulbId")
    User getUserByCsulbId(@Param("csulbId") int csulbId);

    @Query("SELECT user FROM User user " +
            "WHERE user.email = :email")
    User getUserByEmail(@Param("email") String email);
}

package com.team1159ers.coffee_coder_db.repository.user;

import com.team1159ers.coffee_coder_db.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT COUNT(user.csulbId) " +
                "FROM User user")
    int getNumOfUsers();

    @Query("SELECT user " +
                "FROM User user " +
                "WHERE user.csulbId = :csulbId")
    User getUserByCsulbId(@Param("csulbId") int csulbId);

    @Query("SELECT user " +
                "FROM User user " +
                "WHERE user.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User user " +
                "SET user.profileName = :profileName " +
                "WHERE user.csulbId = :csulbId")
    void updateProfileName(@Param("csulbId") int csulbId, @Param("profileName") String profileName);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User user " +
                "SET user.profileImgSrc = :profileImgSrc " +
                "WHERE user.csulbId = :csulbId")
    void updateProfileImgSrc(@Param("csulbId") int csulbId, @Param("profileImgSrc") String profileImgSrc);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User user " +
                "SET user.currentProblemId = :problemId " +
                "WHERE user.csulbId = :csulbId")
    void updateProblemId(@Param("csulbId") int csulbId, @Param("problemId") int problemId);
}
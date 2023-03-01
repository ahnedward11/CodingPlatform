
package com.team1159ers.coffee_coder_db.repository.admin;

import com.team1159ers.coffee_coder_db.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT admin " +
                "FROM Admin admin " +
                "WHERE admin.csulbId = :csulbId")
    Admin getAdminByCsulbId(@Param("csulbId") int csulbId);

    @Query("SELECT admin " +
                "FROM Admin admin " +
                "WHERE admin.email = :email")
    Admin getAdminByEmail(@Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE " +
            "FROM User user " +
            "WHERE user.csulbId = :csulbId")
    void deleteUserByCsulbId(@Param("csulbId") int csulbId);
}

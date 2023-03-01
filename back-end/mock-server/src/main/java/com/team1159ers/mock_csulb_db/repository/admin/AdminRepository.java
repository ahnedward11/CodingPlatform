
package com.team1159ers.mock_csulb_db.repository.admin;

import com.team1159ers.mock_csulb_db.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT email from Admin")
    List<String> getAllEmails();

    @Query("SELECT admin FROM Admin admin " +
            "WHERE admin.csulbId = :csulbId")
    Admin getAdminByCsulbId(@Param("csulbId") int csulbId);

    @Query("SELECT admin FROM Admin admin " +
            "WHERE admin.email = :email")
    Admin getAdminByEmail(@Param("email") String email);
}

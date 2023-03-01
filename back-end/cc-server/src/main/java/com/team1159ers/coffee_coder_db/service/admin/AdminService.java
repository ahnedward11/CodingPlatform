
package com.team1159ers.coffee_coder_db.service.admin;

import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.model.user.User;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminByCsulbId(int csulbId);
    Admin getAdminByEmail(String email);
    void deleteUserByCsulbId(int csulbId);
}

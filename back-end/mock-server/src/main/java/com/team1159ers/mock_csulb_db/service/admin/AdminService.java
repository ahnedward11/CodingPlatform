
package com.team1159ers.mock_csulb_db.service.admin;

import com.team1159ers.mock_csulb_db.model.admin.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    List<String> getAllEmails();
    Admin getAdminByCsulbId(int csulbId);
    Admin getAdminByEmail(String email);
}

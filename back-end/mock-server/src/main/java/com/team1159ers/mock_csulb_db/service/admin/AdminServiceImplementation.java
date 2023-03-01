
package com.team1159ers.mock_csulb_db.service.admin;

import com.team1159ers.mock_csulb_db.model.admin.Admin;
import com.team1159ers.mock_csulb_db.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public List<String> getAllEmails() {
        return adminRepository.getAllEmails();
    }

    @Override
    public Admin getAdminByCsulbId(int csulbId) {
        return adminRepository.getAdminByCsulbId(csulbId);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.getAdminByEmail(email);
    }
}

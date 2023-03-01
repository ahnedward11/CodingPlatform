
package com.team1159ers.coffee_coder_db.service.admin;

import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.repository.admin.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImplementation(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminByCsulbId(int csulbId) {
        return adminRepository.getAdminByCsulbId(csulbId);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.getAdminByEmail(email);
    }

    @Override
    public void deleteUserByCsulbId(int csulbId)  {
        adminRepository.deleteUserByCsulbId(csulbId);
    }
}

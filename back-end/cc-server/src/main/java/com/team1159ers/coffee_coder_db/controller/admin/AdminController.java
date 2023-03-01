
package com.team1159ers.coffee_coder_db.controller.admin;

import com.team1159ers.coffee_coder_db.model.admin.Admin;
import com.team1159ers.coffee_coder_db.model.user.User;
import com.team1159ers.coffee_coder_db.service.admin.AdminService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/admin", consumes = MediaType.ALL_VALUE)
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public int add(@RequestBody Admin admin, @RequestParam("csulbId") String csulbId) {
        try {
            // Since `csulbId` is an auto-generated primary key, we must set it manually
            int csulbIdAsInt = Integer.parseInt(csulbId);
            Admin adminToSearch = adminService.getAdminByCsulbId(csulbIdAsInt);

            if (adminToSearch == null) {
                admin.setCsulbId(csulbIdAsInt);
                adminService.saveAdmin(admin);
                return 0;
            } else {
                throw new DataIntegrityViolationException("Account already exists.");
            }

            // Account with matching CSULB ID or email already exists
        } catch (DataIntegrityViolationException e) {
            return -1;
        }
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam("csulbId") int csulbId)  {
        try {

            adminService.deleteUserByCsulbId(csulbId);

            return 0;

            // Account with matching CSULB ID does not exist
        } catch (DataIntegrityViolationException e) {
            return -1;
        }
    }

    @GetMapping("/getAll")
    public List<Admin> list() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/getByCsulbId")
    public Admin getAdminByCsulbId(@RequestParam("csulbId") String csulbId) {
        Admin admin;
        try {
            int csulbIdAsInt = Integer.parseInt(csulbId);
            admin = adminService.getAdminByCsulbId(csulbIdAsInt);
        } catch (NumberFormatException e) {
            admin = new Admin();
        }

        return Objects.requireNonNullElseGet(admin, Admin::new);
    }

    @GetMapping("/getByEmail")
    public Admin getAdminByEmail(@RequestParam("email") String email) {
        Admin admin = adminService.getAdminByEmail(email);

        return Objects.requireNonNullElseGet(admin, Admin::new);
    }
}

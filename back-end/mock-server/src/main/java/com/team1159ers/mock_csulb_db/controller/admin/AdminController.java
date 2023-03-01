
package com.team1159ers.mock_csulb_db.controller.admin;

import com.team1159ers.mock_csulb_db.model.admin.Admin;
import com.team1159ers.mock_csulb_db.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public String add(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
        return "New Admin has been added.";
    }

    @GetMapping("/getAll")
    public List<Admin> list(){
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


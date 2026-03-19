package fixer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/client/dashboard")
    public String clientDashboard() {
        return "client_dashboard"; // Thymeleaf view
    }

    @GetMapping("/master/dashboard")
    public String masterDashboard() {
        return "master_dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }
}
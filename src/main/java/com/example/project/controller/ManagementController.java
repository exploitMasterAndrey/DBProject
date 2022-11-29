package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {
    @GetMapping("/auto")
    public String getAutoManagementPage(){
        return "auto_management";
    }

    @GetMapping("/branch")
    public String getBranchManagementPage(){
        return "branch_management";
    }

    @GetMapping("/client")
    public String getClientManagementPage(){
        return "client_management";
    }

    @GetMapping("/provider")
    public String getProviderManagementPage(){
        return "provider_management";
    }

    @GetMapping("/request")
    public String getRequestManagementPage(){
        return "request_management";
    }

    @GetMapping("/worker")
    public String getWorkerManagementPage(){
        return "worker_management";
    }
}

package com.example.project.controller;

import com.example.project.model.Branch;
import com.example.project.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestBranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/createBranch")
    public Branch createBranch(@RequestBody Branch input){
        Branch branch = branchService.create(input.getSpecialization(), input.getAddress());
        return branch;
    }

    @GetMapping("/getAllBranches")
    public List<Branch> getAllBranches(){
        return branchService.readAll();
    }

    @PutMapping("/updateBranch")
    public void updateBranch(@RequestBody Branch input){
        Branch branch = branchService.read(input.getId());
        String specialization = input.getSpecialization();
        String address = input.getAddress();

        specialization =  specialization.equals("") ? branch.getSpecialization() : specialization;
        address =  address.equals("")? branch.getAddress() : address;

        branch.setSpecialization(specialization);
        branch.setAddress(address);
        branchService.update(branch);
    }

    @DeleteMapping("/deleteBranch")
    public void deleteBranch(@RequestHeader("id") Long id){
        branchService.delete(id);
    }


}

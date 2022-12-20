package com.example.project.controller;

import com.example.project.model.Branch;
import com.example.project.model.Provider;
import com.example.project.service.BranchService;
import com.example.project.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestProviderController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private BranchService branchService;

    @PostMapping("/createProvider")
    public Provider createProvider(@RequestBody Provider input, @RequestHeader("branchID") Long branchID){
        Branch branch = branchService.read(branchID);
        Provider provider = providerService.create(input.getDeliveryPartType(), input.getName(), branch);

        branch.getProviders().add(provider);
        branchService.update(branch);

        return provider;
    }

    @GetMapping("/getAllProviders")
    public List<Provider> getAllProviders(){
        return providerService.readAll();
    }

    @DeleteMapping("/deleteProvider")
    public void deleteProvider(@RequestHeader("id") Long id){
        providerService.delete(id);
    }

    @PutMapping("/updateProvider")
    public void updateProvider(@RequestBody Map<String, String> params){
        Provider provider = providerService.read(Long.valueOf(params.get("id")));

        Branch branch = null;
        if (!params.get("branchID").equals("")) branch = branchService.read(Long.valueOf(params.get("branchID")));

        String deliveryPartType = params.get("deliveryPartType");
        String name = params.get("name");

        branch = branch == null ? provider.getBranch() : branch;
        deliveryPartType =  deliveryPartType.equals("") ? provider.getDeliveryPartType() : deliveryPartType;
        name =  name.equals("")? provider.getName() : name;

        provider.setBranch(branch);
        provider.setDeliveryPartType(deliveryPartType);
        provider.setName(name);

        providerService.update(provider);
    }
}

package com.example.project.service;

import com.example.project.model.Branch;
import com.example.project.model.Provider;
import com.example.project.repo.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepo providerRepo;

    public Provider create(String deliveryPartType, String name, Branch branch){
        Provider save = providerRepo.save(new Provider(deliveryPartType, name, branch));
        return save;
    }

    public void delete(Long id){
        providerRepo.deleteById(id);
    }

    public List<Provider> readAll(){
        List<Provider> all = providerRepo.findAll();
        return all;
    }

    public Provider read(Long id){
        return providerRepo.findById(id).get();
    }

    public void update(Provider provider){
        providerRepo.save(provider);
    }
}

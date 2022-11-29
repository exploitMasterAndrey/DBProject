package com.example.project.service;

import com.example.project.model.Auto;
import com.example.project.model.Client;
import com.example.project.repo.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {
    @Autowired
    private AutoRepo autoRepo;

    public Auto create(Client client, String brand, String year, String type){
        Auto save = autoRepo.save(new Auto(client, brand, year, type));
        return save;
    }

    public void delete(Long id){
        autoRepo.deleteById(id);
    }

    public List<Auto> readAll(){
        List<Auto> all = autoRepo.findAll();
        return all;
    }

    public Auto read(Long id){
        return autoRepo.findById(id).get();
    }

    public void update(Auto auto){
        autoRepo.save(auto);
    }

}

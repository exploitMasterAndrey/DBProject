package com.example.project.controller;

import com.example.project.model.Auto;
import com.example.project.model.Client;
import com.example.project.service.AutoService;
import com.example.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestAutoController {
    @Autowired
    private AutoService autoService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/getAllAutos")
    public List<Auto> getAllAutos(){
        return autoService.readAll();
    }


    @DeleteMapping("/deleteAuto")
    public void deleteAuto(@RequestHeader("id") Long id){
        autoService.delete(id);
    }


    @PostMapping("/createAuto")
    public Auto createAuto(@RequestBody Auto input, @RequestHeader("clientID") Long clientID){
        Client client = clientService.read(clientID);
        Auto auto = autoService.create(client, input.getBrand(), input.getYear(), input.getType());

        client.getAutos().add(auto);
        clientService.update(client);

        return auto;
    }

    @PutMapping("/updateAuto")
    public void updateAuto(@RequestBody Map<String, String> params){
        Auto auto = autoService.read(Long.valueOf(params.get("id")));

        Client client = null;
        if (!params.get("clientID").equals("")) client = clientService.read(Long.valueOf(params.get("clientID")));
        String brand = params.get("brand");
        String year = params.get("year");
        String type = params.get("type");

        client = client == null ? auto.getClient() : client;
        brand =  brand.equals("") ? auto.getBrand() : brand;
        year =  year.equals("")? auto.getYear() : year;
        type =  type.equals("")? auto.getType() : type;

        auto.setClient(client);
        auto.setBrand(brand);
        auto.setYear(year);
        auto.setType(type);
        autoService.update(auto);
    }
}

package com.example.project.controller;

import com.example.project.model.Client;
import com.example.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/getAllClients")
    public List<Client> getAllClients(){
        return clientService.readAll();
    }

    @PostMapping("/createClient")
    public Client createClient(@RequestBody Client input){
        Client client = clientService.create(input.getSurname(), input.getName(), input.getPatronymic(), input.getBirthDate(), input.getPhone());
        return client;
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@RequestHeader("id") Long id){
        clientService.delete(id);
    }

    public void updateClient(@RequestBody Client input){
        Client client = clientService.read(input.getId());

        String surname = input.getSurname();
        String name = input.getName();
        String patronymic = input.getPatronymic();
        String birthDate = input.getBirthDate();
        String phone = input.getPhone();

        surname =  surname.equals("") ? client.getSurname() : surname;
        name =  name.equals("")? client.getName() : name;
        patronymic =  patronymic.equals("")? client.getPatronymic() : patronymic;
        birthDate =  birthDate.equals("") ? client.getBirthDate() : birthDate;
        phone =  phone.equals("")? client.getPhone() : phone;

        client.setSurname(surname);
        client.setName(name);
        client.setPatronymic(patronymic);
        client.setBirthDate(birthDate);
        client.setPhone(phone);
        clientService.update(client);
    }
}

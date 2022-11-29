package com.example.project.service;

import com.example.project.model.Auto;
import com.example.project.model.Client;
import com.example.project.model.Request;
import com.example.project.repo.ClientRepo;
import com.example.project.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private RequestRepo requestRepo;

    public Client create(String surname, String name, String patronymic, String birthDate, String phone){
        Client save = clientRepo.save(new Client(surname, name, patronymic, birthDate, phone));
        return save;
    }

    public void delete(Long id){
        Client client = clientRepo.findById(id).get();
        List<Request> requests = client.getRequests();

        for (var request : requests){
            requestRepo.deleteById(request.getId());
        }

        clientRepo.deleteById(id);
    }

    public List<Client> readAll(){
        List<Client> all = clientRepo.findAll();
        return all;
    }

    public Client read(Long id){
        Optional<Client> byId = clientRepo.findById(id);
        return byId.get();
    }

    public Client update(Client client){
        Client save = clientRepo.save(client);
        return save;
    }
}

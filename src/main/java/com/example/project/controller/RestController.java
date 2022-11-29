package com.example.project.controller;

import com.example.project.model.*;
import com.example.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private AutoService autoService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private WorkerService workerService;

//    @PostMapping("/createAuto")
//    public Auto createAuto(@RequestHeader("clientID") Long clientID, @RequestHeader("brand") String brand, @RequestHeader("year") String year, @RequestHeader("type") String type){
//        Client client = clientService.read(clientID);
//        Auto auto = autoService.create(client, brand, year, type);
//
//        client.getAutos().add(auto);
//        clientService.update(client);
//
//        return auto;
//    }

    @PostMapping("/createAuto")
    public Auto createAuto(@RequestBody Auto input, @RequestHeader("clientID") Long clientID){
        Client client = clientService.read(clientID);
        Auto auto = autoService.create(client, input.getBrand(), input.getYear(), input.getType());

        client.getAutos().add(auto);
        clientService.update(client);

        return auto;
    }

    @PostMapping("/createProvider")
    public Provider createProvider(@RequestBody Provider input, @RequestHeader("branchID") Long branchID){
        Branch branch = branchService.read(branchID);
        Provider provider = providerService.create(input.getDeliveryPartType(), input.getName(), branch);

        branch.getProviders().add(provider);
        branchService.update(branch);

        return provider;
    }

    @PostMapping("/createBranch")
    public Branch createBranch(@RequestBody Branch input){
        Branch branch = branchService.create(input.getSpecialization(), input.getAddress());
        return branch;
    }

    @PostMapping("/createClient")
    public Client createClient(@RequestBody Client input){
        Client client = clientService.create(input.getSurname(), input.getName(), input.getPatronymic(), input.getBirthDate(), input.getPhone());
        return client;
    }

    @PostMapping("/createWorker")
    public Worker createWorker(@RequestBody Worker input, @RequestHeader("branchID") Long branchID){
        Branch branch = branchService.read(branchID);
        Worker worker = workerService.create(input.getSurname(), input.getName(), input.getPatronymic(), input.getBirthDate(), input.getPhone(), input.getExperience(), input.getPosition(), branch);

        return worker;
    }


    @PostMapping("/createRequest")
    public Request createRequest(@RequestBody Map<String, String> params, @RequestHeader("clientID") Long clientID){
        Client client = clientService.read(clientID);
        List<Long> workersID = Arrays.stream(params.get("workersID").split(" ")).map(s -> Long.valueOf(s)).collect(Collectors.toList());
        List<Worker> workers = new ArrayList<>();

        for (Long workerID : workersID){
            workers.add(workerService.read(workerID));
        }

        Request request = requestService.create(params.get("date"), Integer.valueOf(params.get("price")), params.get("comment"), client);

        for (Worker worker : workers){
            worker.setRequest(request);
            workerService.update(worker);
        }

        requestService.update(request);
        return request;
    }


    @GetMapping("/getAllAutos")
    public List<Auto> getAllAutos(){
        return autoService.readAll();
    }

    @GetMapping("/getAllBranches")
    public List<Branch> getAllBranches(){
        return branchService.readAll();
    }

    @GetMapping("/getAllClients")
    public List<Client> getAllClients(){
        return clientService.readAll();
    }

    @GetMapping("/getAllProviders")
    public List<Provider> getAllProviders(){
        return providerService.readAll();
    }

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests(){
        return requestService.readAll();
    }

    @GetMapping("/getAllWorkers")
    public List<Worker> getAllWorkers(){
        return workerService.readAll();
    }

    @DeleteMapping("/deleteAuto")
    public void deleteAuto(@RequestHeader("id") Long id){
        autoService.delete(id);
    }

    @DeleteMapping("/deleteBranch")
    public void deleteBranch(@RequestHeader("id") Long id){
        branchService.delete(id);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@RequestHeader("id") Long id){
        clientService.delete(id);
    }

    @DeleteMapping("/deleteProvider")
    public void deleteProvider(@RequestHeader("id") Long id){
        providerService.delete(id);
    }

    @DeleteMapping("/deleteRequest")
    public void deleteRequest(@RequestHeader("id") Long id){
        requestService.delete(id);
    }

    @DeleteMapping("/deleteWorker")
    public void deleteWorker(@RequestHeader("id") Long id){
        workerService.delete(id);
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

    @PutMapping("/updateClient")
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

    @PutMapping("/updateWorker")
    public void updateWorker(@RequestBody Map<String, String> params){
        Worker worker = workerService.read(Long.valueOf(params.get("id")));

        Branch branch = null;
        if (!params.get("branchID").equals("")) branch = branchService.read(Long.valueOf(params.get("branchID")));

        String surname = params.get("surname");
        String name = params.get("name");
        String patronymic = params.get("patronymic");
        String birthDate = params.get("birthDate");
        String phone = params.get("phone");
        String position = params.get("position");

        Integer experience = null;
        if (!params.get("experience").equals("")) experience = Integer.valueOf(params.get("experience"));

        surname =  surname.equals("") ? worker.getSurname() : surname;
        name =  name.equals("")? worker.getName() : name;
        patronymic =  patronymic.equals("")? worker.getPatronymic() : patronymic;
        birthDate =  birthDate.equals("") ? worker.getBirthDate() : birthDate;
        phone =  phone.equals("")? worker.getPhone() : phone;
        position =  position.equals("")? worker.getPosition() : position;
        experience =  experience == null ? worker.getExperience() : experience;
        branch = branch == null ? worker.getBranch() : branch;

        worker.setSurname(surname);
        worker.setName(name);
        worker.setPatronymic(patronymic);
        worker.setBirthDate(birthDate);
        worker.setPhone(phone);
        worker.setPosition(position);
        worker.setExperience(experience);
        worker.setBranch(branch);

        workerService.update(worker);
    }

    @PutMapping("/updateRequest")
    public void updateRequest(@RequestBody Map<String, String> params){
        Request request = requestService.read(Long.valueOf(params.get("id")));

        List<Long> workersID = Arrays.stream(params.get("workersID").split(" ")).map(s -> Long.valueOf(s)).collect(Collectors.toList());
        List<Worker> workers = null;

        if (!workersID.isEmpty()) {
            workers = new ArrayList<>();
            for (Long workerID : workersID) {
                workers.add(workerService.read(workerID));
            }
        }

        Client client = request.getClient();
        if (!params.get("clientID").equals("")) client = clientService.read(Long.valueOf(params.get("clientID")));
        String date = params.get("date");
        Integer price = null;
        if (!params.get("price").equals("")) price= Integer.valueOf(params.get("price"));
        String comment = params.get("comment");

        date = date.equals("") ? request.getDate() : date;
        price = price == null ? request.getPrice() : price;
        comment = comment.equals("") ? request.getComment() : comment;

        request.setClient(client);
        request.setDate(date);
        request.setPrice(price);
        request.setComment(comment);

        requestService.update(request);

        for (Worker worker : workers){
            worker.setRequest(request);
            workerService.update(worker);
        }
    }

}

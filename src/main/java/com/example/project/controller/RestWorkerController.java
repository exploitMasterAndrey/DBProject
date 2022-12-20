package com.example.project.controller;

import com.example.project.model.Branch;
import com.example.project.model.Worker;
import com.example.project.service.BranchService;
import com.example.project.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestWorkerController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private BranchService branchService;

    @PostMapping("/createWorker")
    public Worker createWorker(@RequestBody Worker input, @RequestHeader("branchID") Long branchID){
        Branch branch = branchService.read(branchID);
        Worker worker = workerService.create(input.getSurname(), input.getName(), input.getPatronymic(), input.getBirthDate(), input.getPhone(), input.getExperience(), input.getPosition(), branch);

        return worker;
    }

    @GetMapping("/getAllWorkers")
    public List<Worker> getAllWorkers(){
        return workerService.readAll();
    }

    @DeleteMapping("/deleteWorker")
    public void deleteWorker(@RequestHeader("id") Long id){
        workerService.delete(id);
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
}

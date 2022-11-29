package com.example.project.service;

import com.example.project.model.Branch;
import com.example.project.model.Worker;
import com.example.project.repo.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepo workerRepo;

    public Worker create(String surname, String name, String patronymic, String birthDate, String phone, Integer experience, String position, Branch branch){
        Worker save = workerRepo.save(new Worker(surname, name, patronymic, birthDate, phone, experience, position, branch));
        return save;
    }

    public void delete(Long id){
        workerRepo.deleteById(id);
    }

    public List<Worker> readAll(){
        List<Worker> all = workerRepo.findAll();
        return all;
    }

    public Worker read(Long id){
        return workerRepo.findById(id).get();
    }

    public void update(Worker worker){
        workerRepo.save(worker);
    }

}

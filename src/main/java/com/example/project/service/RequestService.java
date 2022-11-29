package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.model.Request;
import com.example.project.model.Worker;
import com.example.project.repo.RequestRepo;
import com.example.project.repo.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private WorkerRepo workerRepo;

    public Request create(String date, Integer price, String comment, Client client){
        Request save = requestRepo.save(new Request(date, price, comment, client));
        return save;
    }

    public void delete(Long id){
        Request request = requestRepo.findById(id).get();
        List<Worker> workers = request.getWorkers();

        for (var worker : workers){ //ON DELETE SET NULL
            worker.setRequest(null);
            workerRepo.save(worker);
        }

        requestRepo.deleteById(id);
    }

    public List<Request> readAll(){
        List<Request> all = requestRepo.findAll();
        return all;
    }

    public Request read(Long id){
        return requestRepo.findById(id).get();
    }

    public void update(Request request){
        requestRepo.save(request);
    }
}

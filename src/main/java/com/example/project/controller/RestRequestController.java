package com.example.project.controller;

import com.example.project.model.Client;
import com.example.project.model.Request;
import com.example.project.model.Worker;
import com.example.project.service.ClientService;
import com.example.project.service.RequestService;
import com.example.project.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RestRequestController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

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

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests(){
        return requestService.readAll();
    }

    @DeleteMapping("/deleteRequest")
    public void deleteRequest(@RequestHeader("id") Long id){
        requestService.delete(id);
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

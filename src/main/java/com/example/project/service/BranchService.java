package com.example.project.service;

import com.example.project.model.Branch;
import com.example.project.model.Provider;
import com.example.project.model.Worker;
import com.example.project.repo.BranchRepo;
import com.example.project.repo.ProviderRepo;
import com.example.project.repo.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private ProviderRepo providerRepo;
    @Autowired
    private WorkerRepo workerRepo;

    public Branch create(String specialization, String address){
        Branch save = branchRepo.save(new Branch(specialization, address));
        return save;
    }

    public void delete(Long id){
        Branch branch = branchRepo.findById(id).get();
        List<Provider> providers = branch.getProviders();
        List<Worker> workers = branch.getWorkers();

        for(var provider : providers){ //ON DELETE SET NULL
            provider.setBranch(null);
            providerRepo.save(provider);
        }

        for (var worker : workers){ //ON DELETE SET NULL
            worker.setBranch(null);
            workerRepo.save(worker);
        }

        branchRepo.deleteById(id);
    }

    public List<Branch> readAll(){
        List<Branch> all = branchRepo.findAll();
        return all;
    }

    public Branch read(Long id){
        return branchRepo.findById(id).get();
    }

    public void update(Branch branch){
        branchRepo.save(branch);
    }
}

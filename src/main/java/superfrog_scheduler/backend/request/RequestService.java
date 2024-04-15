package superfrog_scheduler.backend.request;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.utils.IdWorker;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;
import superfrog_scheduler.backend.request.RequestRepository;
import superfrog_scheduler.backend.request.Request;

import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;
    private final IdWorker idWorker;

    //Constructor
    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
        this.idWorker = new IdWorker();
    }

    //Use cases
    public List<Request> findAll(){
        return this.requestRepository.findAll();
    }

    public Request findRequestById(String id){ // Find a submitted request by id
        return this.requestRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }

    public List<Request> findByStatus(RequestStatus status){ // Find a submitted request by status
        return this.requestRepository.findByStatus(status);
    }

    public Request updateRequestStatus(String id, RequestStatus status){ //Update status of a request
        return this.requestRepository.findById(id).map(
                pastRequest->{
                    pastRequest.setStatus(status);
                    return this.requestRepository.save(pastRequest);
                }).orElseThrow(()->new ObjectNotFoundException("request",id));
    }

    public Request save(Request newRequest){ //Will be used to save new request
        return this.requestRepository.save(newRequest);
    }

}
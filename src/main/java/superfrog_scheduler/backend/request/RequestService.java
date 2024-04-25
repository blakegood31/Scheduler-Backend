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

    public Request updateRequestInfo(String requestId, Request update){
        return this.requestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setAddress(update.getAddress());
                    oldRequest.setDescription(update.getDescription());
                    oldRequest.setStartTime(update.getStartTime());
                    oldRequest.setEndTime(update.getEndTime());
                    oldRequest.setEventTitle(update.getEventTitle());
                    oldRequest.setStatus(update.getStatus());
                    oldRequest.setSuperfrog(update.getSuperfrog());
                    oldRequest.setCustomer(update.getCustomer());
                    oldRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldRequest.setOther_orgs(update.getOther_orgs());
                    return this.requestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("request", requestId));
    }

    public Request save(Request newRequest){ //Will be used to save new request
        return this.requestRepository.save(newRequest);
    }

    public Request cancelRequest(String requestId){
        Request cancel = this.requestRepository.findById(requestId).orElseThrow(() -> new ObjectNotFoundException("request", requestId));
        cancel.setStatus(RequestStatus.CANCELLED);
        return cancel;
    }

}
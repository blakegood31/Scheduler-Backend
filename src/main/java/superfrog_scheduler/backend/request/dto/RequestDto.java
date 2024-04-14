package superfrog_scheduler.backend.request.dto;

import superfrog_scheduler.backend.request.RequestStatus;

public record RequestDto(String id, String address, String description, String event, RequestStatus status, String sup_id, String cust_id, String info, String other_orgs) {
}

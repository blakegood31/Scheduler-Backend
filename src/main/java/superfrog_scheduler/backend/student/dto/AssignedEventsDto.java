package superfrog_scheduler.backend.student.dto;

import superfrog_scheduler.backend.request.Request;

import java.util.List;

public record AssignedEventsDto(List<Request> assignedEvents) {
}

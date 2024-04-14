package superfrog_scheduler.backend.superfrog_calendar.dto;

import superfrog_scheduler.backend.student.Student;

import java.time.LocalDateTime;

public record SuperfrogCalendarDto (String id, Student superfrog, LocalDateTime start_time, LocalDateTime end_time, boolean is_available) {
}

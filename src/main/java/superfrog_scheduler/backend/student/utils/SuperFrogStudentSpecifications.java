package superfrog_scheduler.backend.student.utils;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.student.Student;

import java.util.ArrayList;
import java.util.List;
@Component
public class SuperFrogStudentSpecifications {
    public Specification<Student> superFrogStudentFilters(String firstName, String lastName, String phoneNumber, String email){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null){
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }
            if (lastName != null){
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
            }
            if (phoneNumber != null){
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
            }
            if (email != null){
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
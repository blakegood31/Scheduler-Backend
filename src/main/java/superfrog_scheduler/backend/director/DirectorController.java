package superfrog_scheduler.backend.director;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.director.converter.DirDtoToDirConverter;
import superfrog_scheduler.backend.director.converter.DirToDirDtoConverter;
import superfrog_scheduler.backend.director.dto.DirectorDto;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.converter.StudentDtoToStudentConverter;
import superfrog_scheduler.backend.student.converter.StudentToStudentDtoConverter;
import superfrog_scheduler.backend.student.dto.StudentDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorService directorService;

    private final DirToDirDtoConverter dirToDirDtoConverter;

    private final DirDtoToDirConverter dirDtoToDirConverter;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;

    public DirectorController(DirectorService directorService, DirToDirDtoConverter dirToDirDtoConverter, DirDtoToDirConverter dirDtoToDirConverter, StudentDtoToStudentConverter studentDtoToStudentConverter, StudentToStudentDtoConverter studentToStudentDtoConverter) {
        this.directorService = directorService;
        this.dirToDirDtoConverter = dirToDirDtoConverter;
        this.dirDtoToDirConverter = dirDtoToDirConverter;
        this.studentDtoToStudentConverter = studentDtoToStudentConverter;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
    }

    @GetMapping
    public Result findAllDirectors() {
        List<Director> foundUsers = this.directorService.findAll();
        List<DirectorDto> dirDtos = foundUsers.stream()
                .map(this.dirToDirDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "Find All Success", dirDtos);
    }

    //UC15-find student frogs by name(first and/or last), email, or phone
    @PostMapping("/searchFrogs")
    public Result findSuperFrogStudent(@Valid @RequestBody StudentDto studentDto){

        Student student = this.studentDtoToStudentConverter.convert(studentDto);

        List<Student> students = this.directorService
                .findSuperFrogStudent(student.getFirstName(), student.getLastName(), student.getPhoneNumber(), student.getEmail());

        List<StudentDto> studentDtos = students
                .stream()
                .map(this.studentToStudentDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find Student Success", studentDtos);
    }
    //UC16
    @GetMapping("/{id}")
    public Result findDirectorById(@PathVariable String id) {
        Director foundUser = this.directorService.findById(id);
        DirectorDto dirDto = this.dirToDirDtoConverter.convert(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", dirDto);
    }

    /*@DeleteMapping("/{id}") -UC14
    public Result deleteUser(@PathVariable String id) {
        this.directorService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PostMapping
    public Result addUser(@Valid @RequestBody Director newFrog) {
        Director savedUser = this.directorService.save(newFrog);
        DirectorDto savedDirDto = this.dirToDirDtoConverter.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedDirDto);
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable String id, @Valid @RequestBody DirectorDto dirDto) {
        Director update = this.dirDtoToDirConverter.convert(dirDto);
        Director updatedUser = this.directorService.update(id, update);
        DirectorDto updatedDirDto = this.dirToDirDtoConverter.convert(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedDirDto);
    }

    @PutMapping("/{email}/disable")
    public Result disableUser(@PathVariable String email) {
        Director updatedUser = this.directorService.disableUser(email);
        DirectorDto updatedDirDto = this.dirToDirDtoConverter.convert(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Disable User Success", updatedDirDto);
    }

    @PutMapping("/{email}/enable")
    public Result enableUser(@PathVariable String email) {
        Director updatedUser = this.directorService.enableUser(email);
        DirectorDto updatedDirDto = this.dirToDirDtoConverter.convert(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Enable User Success", updatedDirDto);
    }*/
}
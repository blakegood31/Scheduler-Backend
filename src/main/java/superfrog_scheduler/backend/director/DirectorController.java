package superfrog_scheduler.backend.director;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.director.converter.DirDtoToDirConverter;
import superfrog_scheduler.backend.director.converter.DirToDirDtoConverter;
import superfrog_scheduler.backend.director.dto.DirectorDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class DirectorController {
    private final DirectorService directorService;

    private final DirToDirDtoConverter dirToDirDtoConverter;

    private final DirDtoToDirConverter dirDtoToDirConverter;

    public DirectorController(DirectorService directorService, DirToDirDtoConverter dirToDirDtoConverter, DirDtoToDirConverter dirDtoToDirConverter) {
        this.directorService = directorService;
        this.dirToDirDtoConverter = dirToDirDtoConverter;
        this.dirDtoToDirConverter = dirDtoToDirConverter;
    }

    @GetMapping
    public Result findAllUsers() {
        List<Director> foundUsers = this.directorService.findAll();
        List<DirectorDto> dirDtos = foundUsers.stream()
                .map(this.dirToDirDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "Find All Success", dirDtos);
    }

    @GetMapping("/{id}")
    public Result findUserById(@PathVariable String id) {
        Director foundUser = this.directorService.findById(id);
        DirectorDto dirDto = this.dirToDirDtoConverter.convert(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", dirDto);
    }

    @DeleteMapping("/{id}")
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
    }
}
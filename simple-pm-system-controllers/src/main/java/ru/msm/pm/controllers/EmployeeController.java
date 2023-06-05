package ru.msm.pm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.services.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

//todo статусы
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> create(@RequestBody CreateEmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
    }

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto update(@RequestBody EditEmployeeDto dto) {
        return employeeService.edit(dto);
    }

    @GetMapping("/{id}")    //todo ?
    public EmployeeDto getById(@PathVariable Long id) {
        GetEmployeeByIdDto dto = new GetEmployeeByIdDto(id);
        return employeeService.getEmployeeById(dto);
    }

    @GetMapping("/account")
    public EmployeeDto getByAccount(@RequestParam String account) {
        GetEmployeeByAccountDto dto = new GetEmployeeByAccountDto(account);
        return employeeService.getEmployeeByAccount(dto);
    }

    @DeleteMapping("/delete")
    public EmployeeDto deleteById(@RequestParam Long id) {
        DeleteEmployeeDto dto = new DeleteEmployeeDto(id);
        return employeeService.delete(dto);
    }

    @GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findByValue(@RequestBody FindEmployeesDto dto) {
        return employeeService.findByValue(dto);
    }

    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getByIds(@RequestBody GetEmployeesByIdsDto dto) {
        return employeeService.getEmployeesByIds(dto);
    }

//    @GetMapping("/")
//    public List<EmployeeDto> getAll() {
//        return null;
//    }
}

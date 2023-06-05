package ru.msm.pm.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.services.EmployeeService;

import java.util.List;
// http://localhost:8080/swagger-ui/index.html
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "EmployeeController", description = "Операции над сотрудником")
public class EmployeeController {
    private final EmployeeService employeeService;

//todo статусы
    @Operation(summary = "Создание карточки сотрудника")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> create(@RequestBody CreateEmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
    }

    @Operation(summary = "Редактирование карточки сотрудника")
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto update(@RequestBody EditEmployeeDto dto) {
        return employeeService.edit(dto);
    }

    @Operation(summary = "Получение карточки сотрудника по личному идентификатору")
    @GetMapping("/{id}")    //todo ?
    public EmployeeDto getById(@PathVariable Long id) {
        GetEmployeeByIdDto dto = new GetEmployeeByIdDto(id);
        return employeeService.getEmployeeById(dto);
    }

    @Operation(summary = "Получение карточки сотрудника по учетной записи")
    @GetMapping("/account")
    public EmployeeDto getByAccount(@RequestParam String account) {
        GetEmployeeByAccountDto dto = new GetEmployeeByAccountDto(account);
        return employeeService.getEmployeeByAccount(dto);
    }

    @Operation(summary = "Удаление карточки сотрудника",
            description = "При удалении сотрудника, сотрудник переводится в статус \"DELETED\".")
    @DeleteMapping("/delete")
    public EmployeeDto deleteById(@RequestParam Long id) {
        DeleteEmployeeDto dto = new DeleteEmployeeDto(id);
        return employeeService.delete(dto);
    }

    @Operation(summary = "Поиск карточек сотрудников по текстовому значению",
            description = "  Поиск осуществляется по текстовому значению, которое проверяется по атрибутам" +
                "Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.")
    @GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findByValue(@RequestBody FindEmployeesDto dto) {
        return employeeService.findByValue(dto);
    }

    @Operation(summary = "Получение карточек сотрудников по списку личных идентификаторов")
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getByIds(@RequestBody GetEmployeesByIdsDto dto) {
        return employeeService.getEmployeesByIds(dto);
    }

//    @GetMapping("/")
//    public List<EmployeeDto> getAll() {
//        return null;
//    }
}

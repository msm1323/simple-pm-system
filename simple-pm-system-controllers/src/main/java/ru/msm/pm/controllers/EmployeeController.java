package ru.msm.pm.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.services.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

// http://localhost:8080/swagger-ui/index.html
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "EmployeeController", description = "Операции над сотрудниками")
public class EmployeeController {

    public static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);//todo logs везде
    private final EmployeeService employeeService;

    @Operation(summary = "Создание карточки сотрудника (упрощенное)")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto create(@RequestBody CreateEmployeeDto dto) {
        return employeeService.create(dto);
    }

    @Operation(summary = "Создание полной карточки сотрудника")
    @PostMapping(value = "/create-full", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createFull(@RequestBody CreateFullEmployeeDto dto) {
        try {
            return employeeService.createFull(dto);
        } catch (IllegalArgumentException e) {//todo чтоб сообщения об ошибках выборочно были видны
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Создание карточки сотрудника")
    @PostMapping(value = "/set-credentials", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto setCredentials(@RequestBody SetEmployeeCredentials dto) {
        try {
            return employeeService.setCredentials(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Редактирование карточки сотрудника")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody UpdateEmployeeDto dto) {
        try {
            return employeeService.editEmployee(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Получение карточки сотрудника по личному идентификатору")
    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        GetEmployeeByIdDto dto = new GetEmployeeByIdDto(id);
        LOGGER.info("id = " + id);
        try {
            return employeeService.getEmployeeById(dto);
        } catch (NoSuchElementException e) {
            LOGGER.debug("NoSuchElementException");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение карточки сотрудника по учетной записи")
    @GetMapping("/account")
    public EmployeeDto getByAccount(@RequestParam String account) {
        GetEmployeeByAccountDto dto = new GetEmployeeByAccountDto(account);
        try {
            return employeeService.getEmployeeByAccount(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Удаление карточки сотрудника",
            description = "При удалении сотрудника, сотрудник переводится в статус \"DELETED\".")
    @DeleteMapping("/delete")
    public EmployeeDto deleteById(@RequestParam Long id) {
        DeleteEmployeeDto dto = new DeleteEmployeeDto(id);
        try {
            return employeeService.deleteEmployee(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Поиск карточек сотрудников по текстовому значению",
            description = "Поиск осуществляется по текстовому значению, которое проверяется по атрибутам" +
                    "Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.")
    @GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findByValue(@RequestBody FindEmployeesDto dto) {
        try {
            return employeeService.findByValue(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Получение карточек сотрудников по списку личных идентификаторов")
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getByIds(@RequestBody GetEmployeesByIdsDto dto) {
        try {
            return employeeService.getEmployeesByIds(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Получение карточек всех сотрудников")
    @GetMapping("/")
    public List<EmployeeDto> getAll() {
        try {
            return employeeService.getAllEmployees();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

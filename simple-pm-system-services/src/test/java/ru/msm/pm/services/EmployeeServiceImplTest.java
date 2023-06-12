package ru.msm.pm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import ru.msm.pm.dao.EmployeeRepository;
import ru.msm.pm.dto.employee.EmployeeDto;
import ru.msm.pm.dto.employee.GetEmployeeByAccountDto;
import ru.msm.pm.dto.employee.GetEmployeeByIdDto;
import ru.msm.pm.model.Employee;
import ru.msm.pm.services.config.ServicesConfig;
import ru.msm.pm.services.impls.EmployeeServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServicesConfig.class)
@PropertySource(value = "application-test.properties")
@EnableAutoConfiguration
public class EmployeeServiceImplTest extends BaseTest {

    @Spy
    EmployeeRepository repository;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    public void testGetEmployeeById() {
        GetEmployeeByIdDto requestDto = new GetEmployeeByIdDto(new Random().nextLong());
        Employee dbEmp = new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        dbEmp.setId(requestDto.getId());
        when(repository.getEmployeeById(requestDto.getId())).thenReturn(Optional.of(dbEmp));

        EmployeeDto resultDto = service.getEmployeeById(requestDto);

        assertThat(resultDto.getId()).isEqualTo(requestDto.getId());
    }

    @Test
    public void testGetEmployeeById_negative() {
        GetEmployeeByIdDto requestDto = new GetEmployeeByIdDto(new Random().nextLong());
        when(repository.getEmployeeById(any())).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.getEmployeeById(requestDto));
    }

    @Test
    public void testGetEmployeeByAccount() {
        GetEmployeeByAccountDto requestDto = new GetEmployeeByAccountDto(UUID.randomUUID().toString());
        Employee dbEmp = new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        dbEmp.setAccount(requestDto.getAccount());
        when(repository.getEmployeeByAccount(requestDto.getAccount())).thenReturn(Optional.of(dbEmp));

        EmployeeDto resultDto = service.getEmployeeByAccount(requestDto);

        assertThat(resultDto.getAccount()).isEqualTo(requestDto.getAccount());
    }

    @Test
    public void testGetEmployeeByAccount_negative() {
        GetEmployeeByAccountDto requestDto = new GetEmployeeByAccountDto(UUID.randomUUID().toString());
        when(repository.getEmployeeByAccount(any())).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.getEmployeeByAccount(requestDto));

        verify(repository, times(1)).getEmployeeByAccount(requestDto.getAccount());
    }

}

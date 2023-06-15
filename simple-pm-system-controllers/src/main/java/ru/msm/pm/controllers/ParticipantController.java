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
import ru.msm.pm.dto.projectTeam.*;
import ru.msm.pm.services.ParticipantService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/participant", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ParticipantController", description = "Операции над командами проектов")
public class ParticipantController {

    private final ParticipantService participantService;

    public static final Logger LOGGER = LogManager.getLogger(ParticipantController.class);

    @Operation(summary = "Получение информации об участнике проекта по его идентификатору участника")
    @GetMapping("/{id}")
    public ParticipantDto getParticipantById(@PathVariable Long id) {
        GetParticipantByIdDto dto = new GetParticipantByIdDto(id);
        try {
            return participantService.getParticipantById(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Добавление участника в проект")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantDto createParticipant(@RequestBody AddParticipantDto dto) {
        try {
            return participantService.createParticipant(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Удаление участника проекта по его идентификатору участника")
    @DeleteMapping("/delete")
    public ParticipantDto deleteParticipantById(@RequestParam Long id){
        DeleteParticipantByIdDto dto = new DeleteParticipantByIdDto(id);
        try {
            return participantService.deleteParticipantById(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации об участниках проекта по идентификатору проекта")
    @GetMapping("/project/{id}")
    public List<ParticipantDto> getParticipantsByProjectId(@PathVariable Long id){
        GetProjectTeamDto dto = new GetProjectTeamDto(id);
        try {
            return participantService.getParticipantsByProjectId(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации об участии в проектах определенного сотрудника по его идентификатору")
    @GetMapping("/employee/{id}")
    public List<ParticipantDto> getParticipantsByEmployeeId(@PathVariable Long id){
        GetProjectTeamDto dto = new GetProjectTeamDto(id);
        try {
            return participantService.getParticipantsByProjectId(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}

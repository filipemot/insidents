package br.com.luisfilipemotame.insidents.controllers;

import br.com.luisfilipemotame.insidents.entity.Incident;
import br.com.luisfilipemotame.insidents.repository.IncidentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

        @Operation(summary = "Create a new incident")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incident created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {
        incident.setCreatedAt(LocalDateTime.now());
        incident.setUpdatedAt(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

        @Operation(summary = "Update an existing incident")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incident updated successfully"),
        @ApiResponse(responseCode = "404", description = "Incident not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incidentDetails) {
        return incidentRepository.findById(id).map(incident -> {
            incident.setName(incidentDetails.getName());
            incident.setDescription(incidentDetails.getDescription());
            incident.setUpdatedAt(LocalDateTime.now());
            return ResponseEntity.ok(incidentRepository.save(incident));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIncident(@PathVariable Long id) {
        return incidentRepository.findById(id).map(incident -> {
            incidentRepository.delete(incident);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        return incidentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/latest")
    public List<Incident> getLatestIncidents() {
        return incidentRepository.findTop20ByOrderByCreatedAtDesc();
    }
}

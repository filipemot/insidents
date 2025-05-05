package br.com.luisfilipemotame.insidents.controller;

import br.com.luisfilipemotame.insidents.controllers.IncidentController;
import br.com.luisfilipemotame.insidents.entity.Incident;
import br.com.luisfilipemotame.insidents.repository.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IncidentControllerTest {
    @InjectMocks
    private IncidentController incidentController;

    @Mock
    private IncidentRepository incidentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIncident() {
        Incident incident = new Incident();
        incident.setName("Incidente 1");
        incident.setDescription("Descrição do incidente 1");

        when(incidentRepository.save(any(Incident.class))).thenReturn(incident);

        Incident createdIncident = incidentController.createIncident(incident);

        assertNotNull(createdIncident);
        assertEquals("Incidente 1", createdIncident.getName());
        verify(incidentRepository).save(any(Incident.class));
    }

    @Test
    public void testUpdateIncident() {
        Long incidentId = 1L;
        Incident existingIncident = new Incident();
        existingIncident.setIdIncident(incidentId);
        existingIncident.setName("Incidente 1");
        existingIncident.setDescription("Descrição do incidente 1");
        existingIncident.setUpdatedAt(LocalDateTime.now());

        Incident updatedIncident = new Incident();
        updatedIncident.setName("Incidente Atualizado");
        updatedIncident.setDescription("Descrição atualizada");

        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(existingIncident));
        when(incidentRepository.save(any(Incident.class))).thenReturn(existingIncident);

        ResponseEntity<Incident> response = incidentController.updateIncident(incidentId, updatedIncident);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Incidente Atualizado", response.getBody().getName());
        verify(incidentRepository).save(existingIncident);
    }

    @Test
    public void testDeleteIncident() {
        Long incidentId = 1L;
        Incident existingIncident = new Incident();
        existingIncident.setIdIncident(incidentId);

        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(existingIncident));

        ResponseEntity<Object> response = incidentController.deleteIncident(incidentId);

        assertEquals(200, response.getStatusCodeValue());
        verify(incidentRepository).delete(existingIncident);
    }

    @Test
    public void testGetAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        incidents.add(new Incident());
        incidents.add(new Incident());

        when(incidentRepository.findAll()).thenReturn(incidents);

        List<Incident> result = incidentController.getAllIncidents();

        assertEquals(2, result.size());
        verify(incidentRepository).findAll();
    }

    @Test
    public void testGetIncidentById() {
        Long incidentId = 1L;
        Incident existingIncident = new Incident();
        existingIncident.setIdIncident(incidentId);

        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(existingIncident));

        ResponseEntity<Incident> response = incidentController.getIncidentById(incidentId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(existingIncident, response.getBody());
    }

    @Test
    public void testGetLatestIncidents() {
        List<Incident> incidents = new ArrayList<>();
        incidents.add(new Incident());
        incidents.add(new Incident());

        when(incidentRepository.findTop20ByOrderByCreatedAtDesc()).thenReturn(incidents);

        List<Incident> result = incidentController.getLatestIncidents();

        assertEquals(2, result.size());
        verify(incidentRepository).findTop20ByOrderByCreatedAtDesc();
    }
}

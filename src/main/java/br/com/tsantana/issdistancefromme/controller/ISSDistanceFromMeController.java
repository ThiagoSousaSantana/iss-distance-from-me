package br.com.tsantana.issdistancefromme.controller;

import br.com.tsantana.issdistancefromme.service.IssDistanceFromMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class ISSDistanceFromMeController {

    private final IssDistanceFromMeService service;

    @GetMapping
    public ResponseEntity<Double> getIssDistance(HttpServletRequest request) {
        return ResponseEntity.ok(service.getIssDistance(request.getRemoteAddr()));
    }
}


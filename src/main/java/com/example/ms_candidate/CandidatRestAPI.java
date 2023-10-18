package com.example.ms_candidate;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/candidats")
public class CandidatRestAPI {

    @Autowired
    private CandidatService candidatService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat c, KeycloakAuthenticationToken auth) {

        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(candidatService.addCandidat(c), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/getAll")
    @ResponseStatus
    public List<Candidat> getAllCandidatsC() {
        return candidatService.getAllCandidats();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus
    public Optional<Candidat> getCandidatByIdC(@PathVariable("id") Integer id) {
        return candidatService.getCandidatById(id);
    }


    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Candidat> updateCandidat(@RequestBody Candidat c, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(candidatService.updateCandidat(c, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable("id") Integer id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(candidatService.deleteCandidate(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



    @PutMapping( value = "/updateLike/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Candidat> updateCandidatLike(@PathVariable("id") Integer id){
        return new ResponseEntity<>(candidatService.updateLikes(id), HttpStatus.OK);
    }

    @PutMapping( value = "/updateDislike/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Candidat> updateCandidatDislike(@PathVariable("id") Integer id){
        return new ResponseEntity<>(candidatService.updateDislike(id), HttpStatus.OK);
    }


}

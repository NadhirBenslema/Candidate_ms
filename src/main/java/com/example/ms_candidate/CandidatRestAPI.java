package com.example.ms_candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/candidats")
public class CandidatRestAPI {

    @Autowired
    private CandidatService candidatService;

    @PostMapping("/add")
    @ResponseStatus
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat c){
        return new ResponseEntity<>(candidatService.addCandidat(c), HttpStatus.CREATED);
    }


    @PutMapping( value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Candidat> updateCandidat(@RequestBody Candidat c,@PathVariable("id") Integer id){
        return new ResponseEntity<>(candidatService.updateCandidat(c,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus
    public ResponseEntity<String> deleteCandidat(@PathVariable("id") Integer id){
        return new ResponseEntity<>(candidatService.deleteCandidate(id), HttpStatus.OK);
    }





}

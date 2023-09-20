package com.example.ms_candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public Candidat addCandidat(Candidat candidat){
        return candidatRepository.save(candidat);
    }

    public List<Candidat> getAllCandidats(){
        return candidatRepository.findAll();
    }

    public Optional<Candidat> getCandidatById(Integer id){
        return candidatRepository.findById(id);
    }

    public Candidat updateCandidat(Candidat c,Integer id) {
        if (candidatRepository.findById(id).isPresent()) {
            Candidat existingCandidat = candidatRepository.findById(id).get();
            existingCandidat.setNom(c.getNom());
            existingCandidat.setPrenom(c.getPrenom());
            existingCandidat.setEmail(c.getEmail());
            return candidatRepository.save(existingCandidat);
        }
        return null;
    }

    public String deleteCandidate(Integer id){
        if (candidatRepository.findById(id).isPresent()){
            Candidat existingC = candidatRepository.findById(id).get();
            candidatRepository.delete(existingC);
            return "Candidat deleted successfully !";
        }
        return "Introuvable !";
    }


}

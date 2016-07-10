package ca.uqam.projet.controllers;

import java.util.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CitationController {

  @Autowired CitationRepository repository;

  @RequestMapping("/citations")
  public List<Citation> findAll() {
    return repository.findAll();
  }

  @RequestMapping("/citations/contenu")
  public List<Citation> findByContenu(@RequestParam("term") String[] tsterms) {
    return (tsterms.length == 0) ? repository.findAll() : repository.findByContenu(tsterms);
  }

  @RequestMapping("/citations/{id}")
  public Citation findById(@PathVariable("id") int id) {
    return repository.findById(id);
  }
}

package ca.uqam.projet.repositories;

import java.util.*;
import java.sql.*;

import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

@Component
public class CitationRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  private static List<Citation> citations = Arrays.asList(
      new Citation(1, "Si l’on sait exactement ce qu’on va faire, à quoi bon le faire ?", "Picasso")
    , new Citation(2, "Le métier, c'est ce qui ne s'apprend pas.", "Picasso")
  );

  public List<Citation> findAll() {
    return citations;
  }

  public Citation findById(int id) {
    return citations.get(id-1);
  }

  private static final String INSERT_STMT =
      " insert into citations (id, auteur, contenu)"
    + " values (?, ?, ?)"
    + " on conflict do nothing"
    ;

  public int insert(Citation citation) {
    return jdbcTemplate.update(conn -> {
      PreparedStatement ps = conn.prepareStatement(INSERT_STMT);
      ps.setInt(1, citation.getId());
      ps.setString(2, citation.getAuteur());
      ps.setString(3, citation.getContenu());
      return ps;
    });
  }
}

package ca.uqam.projet.repositories;

import java.util.*;
import java.util.stream.*;
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

  private static final String FIND_ALL_STMT =
      " select"
    + "     id"
    + "   , auteur"
    + "   , contenu"
    + " from"
    + "   citations"
    ;

  public List<Citation> findAll() {
    return jdbcTemplate.query(FIND_ALL_STMT, new CitationRowMapper());
  }

  private static final String FIND_BY_ID_STMT =
      " select"
    + "     id"
    + "   , auteur"
    + "   , contenu"
    + " from"
    + "   citations"
    + " where"
    + "   id = ?"
    ;

  public Citation findById(int id) {
    return jdbcTemplate.queryForObject(FIND_BY_ID_STMT, new Object[]{id}, new CitationRowMapper());
  }

  private static final String FIND_BY_CONTENU_STMT =
      " select"
    + "     id"
    + "   , ts_headline(contenu, q, 'HighlightAll = true') as contenu"
    + "   , auteur"
    + " from"
    + "     citations"
    + "   , to_tsquery(?) as q"
    + " where"
    + "   contenu @@ q"
    + " order by"
    + "   ts_rank_cd(to_tsvector(contenu), q) desc"
    ;

  public List<Citation> findByContenu(String... tsterms) {
    String tsquery = Arrays.stream(tsterms).collect(Collectors.joining(" & "));
    return jdbcTemplate.query(FIND_BY_CONTENU_STMT, new Object[]{tsquery}, new CitationRowMapper());
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

class CitationRowMapper implements RowMapper<Citation> {
  public Citation mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Citation(
        rs.getInt("id")
      , rs.getString("contenu")
      , rs.getString("auteur")
    );
  }
}

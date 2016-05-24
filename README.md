# Projet de démarrage

## Prérequis

- Java 1.8+
- Maven 3.0+
- PostgreSQL 9.4+ avec PostGIS 2.0+

## PostgreSQL

- [Installation de PostgreSQL et de PostGIS pour Windows](http://www.bostongis.com/PrinterFriendly.aspx?content_name=postgis_tut01)
- [PostgreSQL.app pour OSX](http://postgresapp.com/)

## Compilation et exécution

    $ mvn spring-boot:run

Le projet est alors disponible à l'adresse [http://localhost:8080/](http://localhost:8080/)

## Routes disponibles

- [http://localhost:8080/](http://localhost:8080/)
- [http://localhost:8080/citations](http://localhost:8080/citations)
- [http://localhost:8080/citations/1](http://localhost:8080/citations/1)
- [http://localhost:8080/citations/2](http://localhost:8080/citations/2)

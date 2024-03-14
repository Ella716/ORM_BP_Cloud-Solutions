package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.GebruikerFavorieten;
import sr.unasat.bp24.hibernate.repository.GebruikerFavorietenRepository;

import java.util.List;

public class GebruikerFavorietenService {
    private final GebruikerFavorietenRepository repository;

    public GebruikerFavorietenService() {
        this.repository = new GebruikerFavorietenRepository(JPAConfiguration.getEntityManager());
    }

    public GebruikerFavorieten createGebruikerFavorieten(GebruikerFavorieten gebruikerFavorieten) {
        return repository.createGebruikerFavorieten(gebruikerFavorieten);
    }
}

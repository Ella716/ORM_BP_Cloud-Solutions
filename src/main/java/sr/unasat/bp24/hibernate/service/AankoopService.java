package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Aankoop;
import sr.unasat.bp24.hibernate.repository.AankoopRepository;

import java.util.List;

public class AankoopService {
    private final AankoopRepository repository;

    public AankoopService() {
        this.repository = new AankoopRepository(JPAConfiguration.getEntityManager());
    }

    public List<Aankoop> getAankopen() {
        return repository.getAankopen();
    }

    public Aankoop createAankoop(Aankoop aankoop) {
        return repository.createAankoop(aankoop);
    }
}


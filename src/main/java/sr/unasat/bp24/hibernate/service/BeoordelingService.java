package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Beoordeling;
import sr.unasat.bp24.hibernate.repository.BeoordelingRepository;

import java.util.List;

public class BeoordelingService {
    private final BeoordelingRepository repository;

    public BeoordelingService() {
        this.repository = new BeoordelingRepository(JPAConfiguration.getEntityManager());
    }

    public List<Beoordeling> getBeoordelingen() {
        return repository.getBeoordelingen();
    }

    public Beoordeling createBeoordeling(Beoordeling beoordeling) {
        return repository.createBeoordeling(beoordeling);
    }
}

package sr.unasat.bp24.hibernate.service;

import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.SpecialeAanbieding;
import sr.unasat.bp24.hibernate.repository.SpecialeAanbiedingRepository;

import java.util.List;

public class SpecialeAanbiedingService {
    private final SpecialeAanbiedingRepository repository;

    public SpecialeAanbiedingService() {
        this.repository = new SpecialeAanbiedingRepository(JPAConfiguration.getEntityManager());
    }

    public SpecialeAanbieding createSpecialeAanbieding(SpecialeAanbieding specialeAanbieding) {
        return repository.createSpecialeAanbieding(specialeAanbieding);
    }
}

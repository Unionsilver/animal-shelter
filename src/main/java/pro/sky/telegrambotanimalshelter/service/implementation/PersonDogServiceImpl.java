package pro.sky.telegrambotanimalshelter.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.PersonDogNotFoundException;
import pro.sky.telegrambotanimalshelter.models.PersonDog;
import pro.sky.telegrambotanimalshelter.repository.PersonDogRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.PersonDogService;

import java.util.Collection;


@Service
public class PersonDogServiceImpl implements PersonDogService {

    private final PersonDogRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PersonDogServiceImpl.class);

    public PersonDogServiceImpl(PersonDogRepository repository) {
        this.repository = repository;
    }


    @Override
    public PersonDog getByIdPersonDog(Long id) {
        logger.info("Was invoked method to get a personDog by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(PersonDogNotFoundException::new);
    }


    @Override
    public PersonDog addPersonDog(PersonDog personDog) {
        logger.info("Was invoked method to add a personDog");
        return this.repository.save(personDog);
    }


    @Override
    public PersonDog updatePersonDog(PersonDog personDog) {
        logger.info("Was invoked method to update a personDog");
        if (personDog.getId() != null) {
            if (getByIdPersonDog(personDog.getId()) != null) {
                return this.repository.save(personDog);
            }
        }
        throw new PersonDogNotFoundException();
    }


    @Override
    public Collection<PersonDog> getAllPersonDog() {
        logger.info("Was invoked method to get all personsDod");
        return this.repository.findAll();
    }

    @Override
    public void removeByIdPersonDog(Long id) {
        logger.info("Was invoked method to remove a personDog by id={}", id);
        this.repository.deleteById(id);
    }
}
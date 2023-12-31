package pro.sky.telegrambotanimalshelter.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.PersonCatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.PersonCat;
import pro.sky.telegrambotanimalshelter.repository.PersonCatRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.PersonCatService;

import java.util.Collection;


@Service
public class PersonCatServiceImpl implements PersonCatService {

    private final PersonCatRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PersonCatServiceImpl.class);

    public PersonCatServiceImpl(PersonCatRepository personCatRepository) {
        this.repository = personCatRepository;
    }


    @Override
    public PersonCat getByIdPersonCat(Long id) {
        logger.info("Was invoked method to get a personCat by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(PersonCatNotFoundException::new);
    }


    @Override
    public PersonCat addPersonCat(PersonCat personCat) {
        logger.info("Was invoked method to add a personCat");
        return this.repository.save(personCat);
    }


    @Override
    public PersonCat updatePersonCat(PersonCat personCat) {
        logger.info("Was invoked method to update a personCat");
        if (personCat.getId() != null) {
            if (getByIdPersonCat(personCat.getId()) != null) {
                return repository.save(personCat);
            }
        }
        throw new PersonCatNotFoundException();
    }


    @Override
    public Collection<PersonCat> getAllPersonCat() {
        logger.info("Was invoked method to get all personsCat");
        return this.repository.findAll();
    }


    @Override
    public void removeByIdPersonCat(Long id) {
        logger.info("Was invoked method to remove a personCat by id={}", id);
        this.repository.deleteById(id);
    }
}
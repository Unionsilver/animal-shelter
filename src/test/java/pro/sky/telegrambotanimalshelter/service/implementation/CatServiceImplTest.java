package pro.sky.telegrambotanimalshelter.service.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class CatServiceImplTest {

    private static final String NAME = "Барсик";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String BREED = "Британский";
    private static final int AGE = Integer.parseInt("10");

    private static final List<Cat> cats = new ArrayList<>(Arrays.asList(
            new Cat(NAME, BREED, AGE, DESCRIPTION),
            new Cat(NAME, BREED, AGE, DESCRIPTION),
            new Cat(NAME, BREED, AGE, DESCRIPTION)));
    private static final Cat cat = new Cat("Барсик", "Британский", 10, "Description");
    @Mock
    private CatRepository catRepositoryMock;
    @InjectMocks
    private CatServiceImpl catService;

    @Test
    public void getByIdCat() {
        Mockito.when(catRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(cat));
        Cat cat1 = catService.getByIdCat(1L);
        Assertions.assertThat(cat1.getName()).isEqualTo(cat.getName());
        Assertions.assertThat(cat1.getBreed()).isEqualTo(cat.getBreed());
        Assertions.assertThat(cat1.getDescription()).isEqualTo(cat.getDescription());
        Assertions.assertThat(cat1.getAge()).isEqualTo(cat.getAge());
    }

    @Test
    public void addCat() {
        Mockito.when(catRepositoryMock.save(any(Cat.class))).thenReturn(cat);
        Cat cat1 = catService.addCat(cat);
        Assertions.assertThat(cat1.getName()).isEqualTo(cat.getName());
        Assertions.assertThat(cat1.getBreed()).isEqualTo(cat.getBreed());
        Assertions.assertThat(cat1.getDescription()).isEqualTo(cat.getDescription());
        Assertions.assertThat(cat1.getAge()).isEqualTo(cat.getAge());
    }

    @Test
    public void updateCat() {
        Cat cat1 = new Cat();
        cat1.setName("Мартышка");
        cat1.setDescription("Description");
        cat1.setBreed("Африкансий");
        cat1.setAge(15);
        cat1.setId(1L);
        Mockito.when(catRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(cat1));
        Mockito.when(catRepositoryMock.save(any(Cat.class))).thenReturn(cat1);
        Cat cat2 = catService.updateCat(cat1);
        Assertions.assertThat(cat2.getName()).isEqualTo(cat1.getName());
        Assertions.assertThat(cat2.getBreed()).isEqualTo(cat1.getBreed());
        Assertions.assertThat(cat2.getDescription()).isEqualTo(cat1.getDescription());
        Assertions.assertThat(cat2.getAge()).isEqualTo(cat1.getAge());
    }


    @Test
    public void getAllCats() {
        Mockito.when(catRepositoryMock.findAll()).thenReturn(cats);
        Collection<Cat> cat = catService.getAllCat();
        Assertions.assertThat(cat.size()).isEqualTo(cats.size());
        Assertions.assertThat(cat).isEqualTo(cats);
    }
}
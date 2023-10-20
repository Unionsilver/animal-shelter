package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Cat {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String breed;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserForCatsShelter owner;

    public Cat() {
        // Конструктор по умолчанию
    }

    public Cat(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    // Геттеры и сеттеры для всех полей, например:
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserForCatsShelter getOwner() {
        return owner;
    }

    public void setOwner(UserForCatsShelter owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat catModel = (Cat) o;
        return age == catModel.age && Objects.equals(id, catModel.id) && Objects.equals(name, catModel.name) && Objects.equals(breed, catModel.breed) && Objects.equals(owner, catModel.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, owner);
    }

    @Override
    public String toString() {
        return "CatModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                '}';
    }
}
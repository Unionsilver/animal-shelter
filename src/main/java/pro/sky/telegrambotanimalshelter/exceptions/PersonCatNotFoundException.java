package pro.sky.telegrambotanimalshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonCatNotFoundException extends RuntimeException {

    public PersonCatNotFoundException() {
        super("Усыновитель кота не найден!");
    }
}

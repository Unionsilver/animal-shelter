package pro.sky.telegrambotanimalshelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.Collection;


@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @Operation(summary = "Получение кота по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Кот по id найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/{id}")
    public Cat getById(@Parameter(description = "cat id") @PathVariable Long id) {
        return this.catService.getByIdCat(id);
    }

    @Operation(summary = "Создание нового кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Кот создан",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PostMapping()
    public Cat save(@RequestBody Cat cat) {
        return this.catService.addCat(cat);
    }

    @Operation(summary = "Редактирование данных кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные кота отредактированы",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PutMapping()
    public Cat update(@RequestBody Cat cat) {
        return this.catService.updateCat(cat);
    }

    @Operation(summary = "Удаление кота по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Кот удален по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "cat id") @PathVariable Long id) {
        this.catService.removeByIdCat(id);
    }

    @Operation(summary = "Получение всех котов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список всех котов",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/all")
    public Collection<Cat> getAll() {
        return this.catService.getAllCat();
    }
}
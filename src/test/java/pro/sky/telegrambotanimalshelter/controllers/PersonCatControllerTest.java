package pro.sky.telegrambotanimalshelter.controllers;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.PersonCatController;
import pro.sky.telegrambotanimalshelter.models.PersonCat;
import pro.sky.telegrambotanimalshelter.service.implementation.PersonCatServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonCatController.class)
class PersonCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonCatServiceImpl personCatService;


    @Test
    void getByIdPersonCat() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);

        when(personCatService.getByIdPersonCat(anyLong())).thenReturn(personCat);

        mockMvc.perform(
                        get("/person-cat/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personCatService).getByIdPersonCat(1L);
    }


    @Test
    void addPersonCat() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);
        personCat.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personCatService.addPersonCat(personCat)).thenReturn(personCat);

        mockMvc.perform(
                        post("/person-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personCatService).addPersonCat(personCat);
    }


    @Test
    void updatePersonCat() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);
        personCat.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personCatService.addPersonCat(personCat)).thenReturn(personCat);

        mockMvc.perform(
                        put("/person-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personCatService).addPersonCat(personCat);
    }


    @Test
    void deletePersonCat() throws Exception {
        mockMvc.perform(
                        delete("/person-cat/{id}", 1))
                .andExpect(status().isOk());
        verify(personCatService).removeByIdPersonCat(1L);
    }


    @Test
    void getAllPersonCat() throws Exception {
        when(personCatService.getAllPersonCat()).thenReturn(List.of(new PersonCat()));

        mockMvc.perform(
                        get("/person-cat/all"))
                .andExpect(status().isOk());
    }
}
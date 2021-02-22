package com.geekbrains.socket;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class Controller {

    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person(1, "Name1"));
        persons.add(new Person(2, "Name2"));
        persons.add(new Person(3, "Name3"));
    }

    @GetMapping("/{id}")
    public Person getById (@PathVariable int id){
       for (Person p : persons) {
           if (p.getId() == id) {
               return p;
           }
       }
        return null;
    }

    @PostMapping
    public Person createPerson(@RequestParam int id,
                               @RequestParam String name){
        Person p = new Person(id, name);
        if (!persons.contains(p)){
            persons.add(p);
            return p;
        }
        return null;
    }
}

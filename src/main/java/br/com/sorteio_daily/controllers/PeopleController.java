package br.com.sorteio_daily.controllers;

import br.com.sorteio_daily.dtos.DtoCreatePeople;
import br.com.sorteio_daily.dtos.DtoPeoplesList;
import br.com.sorteio_daily.services.PeopleService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoPeoplesList>>listAll() throws IOException {
        System.out.println("Entrou aqui");
        List<DtoPeoplesList> allPeoples = peopleService.getAllPeoples();
        if(allPeoples.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allPeoples);
    }

    @PostMapping("/create")
    public ResponseEntity createPeople(@RequestBody DtoCreatePeople dto) throws IOException {
        peopleService.createPeople(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/actives")
    public ResponseEntity<List<DtoPeoplesList>>returnPeoplesActiviesToGiveway(){
        List<DtoPeoplesList> dtoPeoplesLists = peopleService.peoplesWithStatusOn();
        if(dtoPeoplesLists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoPeoplesLists);
    }

    @PostMapping("/giveaway")
    public ResponseEntity givewayPeople() throws IOException {
        Integer i = peopleService.givewayPeople();
        if(i != 0){
            return ResponseEntity.status(HttpStatus.OK).body(i);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

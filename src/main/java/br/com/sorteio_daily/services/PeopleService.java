package br.com.sorteio_daily.services;

import br.com.sorteio_daily.dtos.DtoCreatePeople;
import br.com.sorteio_daily.dtos.DtoPeoplesList;
import br.com.sorteio_daily.models.People;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@Service
public class PeopleService {
    private List<People>peoples = new ArrayList<>();
//    private final static String FILE_PATH = "data/database.json";
    private static final String FILE_PATH = "src/main/resources/data/database.json";


    private void loadPeoples(){
        try(FileReader fileReader = new FileReader(FILE_PATH)){
            ObjectMapper objectMapper = new ObjectMapper();
            peoples = objectMapper.readValue(fileReader, new TypeReference<List<People>>() {});
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Not found archive");

        }

    }

    public List<DtoPeoplesList>getAllPeoples() throws IOException {
        loadPeoples();

        if(this.peoples != null){
            return peoples.stream().map(p -> new DtoPeoplesList(p)).toList();
        }
        return null;
    }

    public void createPeople(DtoCreatePeople dto) throws IOException {
        loadPeoples();
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        this.peoples.add(new People(this.peoples.size()+1, dto.name(), dto.cargo(), dto.status()));
        fileWriter.write(objectMapper.writeValueAsString(peoples));
        fileWriter.close();




    }

    public List<DtoPeoplesList>peoplesWithStatusOn(){
        List<People> ativos = peoplesWithStatusOnObject();
        return ativos.stream().map( a -> new DtoPeoplesList(a)).toList();


    }

    //SORTEIO
    //CASO ZERE TODOS AS PESSOAS SORTEADAS, TODOS DEVEM ESTAR ATIVOS NOVAMENTES
    public Integer givewayPeople() throws IOException {
        List<People>peoplesActivies = peoplesWithStatusOnObject();
        if(peoplesActivies == null || peoplesActivies.size() == 0){
            return 0;
        }
        List<Integer>ids = new ArrayList<>();
        for(int i = 0; i < peoplesActivies.size(); i++){
            ids.add(peoplesActivies.get(i).getId());
        }
        Collections.shuffle(ids);
        Integer idSorteado = ids.get(0);

        for(int i = 0; i < peoples.size(); i++){
            if(peoples.get(i).getId().equals(idSorteado)){
                peoples.get(i).setStatus("off");
            }
        }
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        fileWriter.write(objectMapper.writeValueAsString(peoples));
        fileWriter.close();
        return idSorteado;



    }

    private List<People>peoplesWithStatusOnObject(){
        loadPeoples();
        List<People> ativos = this.peoples.stream().filter(p -> p.getStatus().equals("Ativo")).toList();
        return ativos;
    }



}

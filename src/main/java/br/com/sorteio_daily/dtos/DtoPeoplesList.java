package br.com.sorteio_daily.dtos;

import br.com.sorteio_daily.models.People;

public record DtoPeoplesList(
        String name,
        String cargo,
        String status
) {
    public DtoPeoplesList(People people){
        this(people.getName(), people.getCargo(), people.getStatus());
    }
}

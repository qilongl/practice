package com.lql.test;

import lombok.Data;

import java.util.List;

/**
 * Created by StrangeDragon on 2019/8/23 14:32
 **/
@Data
public class City {
    private String name;
    private List<Road> afterSuccessor;
    private List<Road> beforeSuccessor;

    public City(String name) {
        this.name = name;
    }

    public void beforeSuccessor(List<Road> beforeList,City airCity) {
        for (Road road : beforeList) {
            
        }
        
        
    }
}

package com.lql.test.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Way implements Serializable {

    private String startName;
    private String end;
    private List<String> names = new ArrayList<>();
    private int distance;

    Way(Node start, Node end) {
        startName = start.getName();
        this.end = end.getName();
        names.add(startName);
    }

    void add(String name, int distance) {
        names.add(name);
        this.distance += distance;
    }

    @Override
    public String toString() {
        return names.toString() + ":" + distance;
    }
}

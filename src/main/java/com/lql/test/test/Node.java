package com.lql.test.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class Node {
    private String        name;

    private List<String>  names;

    private List<Integer> nums;

    Node(String name, List<String> names, List<Integer> nums) {
        this.name = name;
        this.names = names;
        this.nums = nums;
    }

    int getDistance(String name) {
        return nums.get(names.indexOf(name));
    }
}


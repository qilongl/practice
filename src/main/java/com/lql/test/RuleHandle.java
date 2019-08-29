package com.lql.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class RuleHandle {
    protected RuleHandle father;
    protected List<RuleHandle> wayList = new ArrayList<>();
    protected String name;

    protected RuleHandle(String name) {
        this.name = name;
    }

    public List<Road> afterSuccessor;
    public List<Road> beforeSuccessor;

    private static int pathNum = 0;
    private static StringBuffer pathStr = new StringBuffer();

    public void setAfterNode(RuleHandle ruleHandle) {
        wayList.add(ruleHandle);
    }

    //    public abstract void afterSuccessor(List<Road> afterList,City city);//去往的方向
    public void afterSuccessor(List<Road> afterList, City city) {
        outer1:
        for (Road road : afterList) {
            City city1 = road.getCity();
            if (city1.equals(this.getFather())) {
                continue;
            } else {
                outer2:
                for (Road road1 : city1.getBeforeSuccessor()) {
                    City city2 = road1.getCity();
                    if (city2.equals(this)) {
                        continue;
                    } else {
                        if (city1.equals(city)) {
                            pathNum++;
                            pathStr.append(name + "--" + city.getName());
                            break;
                        } else {
                            if (!wayList.contains(this)) {
                                setAfterNode(this);
                            }
                            for (RuleHandle cityxx : wayList) {
                                if (city1.equals(cityxx)) {
                                    continue outer2;
                                }
                            }
//                            setAfterNode(city1);
//                            city1.afterSuccessor(city1.getAfterSuccessor(), city);
                        }
                    }
                }
            }

//            afterList.remove(road);

        }
        System.out.println(pathStr);
        System.out.println(name + "到" + city.getName() + "的线路一共有：" + pathNum + "条！\n");

    }

    public void beforeSuccessor(List<Road> beforeList) {
    }

    ;//进来的方向
}
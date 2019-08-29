package com.lql.test.test;

import java.io.*;
import java.util.*;

public class Main {
    private static Map<String, Node> cities;

    public static void main(String[] args) {
        // data
        Node sh = new Node("上海", Collections.singletonList("深圳"), Collections.singletonList(3));
        Node sz = new Node("深圳", Arrays.asList("上海", "北京", "厦门"), Arrays.asList(3, 1, 2));
        Node bj = new Node("北京", Arrays.asList("上海", "深圳", "南京", "厦门"), Arrays.asList(3, 1, 1, 3));
        Node nj = new Node("南京", Arrays.asList("深圳", "北京", "厦门"), Arrays.asList(5, 1, 1));
        Node xm = new Node("厦门", Collections.singletonList("南京"), Collections.singletonList(1));

        cities = new HashMap<>();
        cities.put("上海", sh);
        cities.put("深圳", sz);
        cities.put("北京", bj);
        cities.put("南京", nj);
        cities.put("厦门", xm);

        List<Way> ways = getWays2(sh, nj);
        ways.forEach(System.out::println);
    }

    private static List<Way> getWays2(Node start, Node end) {
        ArrayList<Way> ways = new ArrayList<>();
        way(start, new Way(start, end), end.getName(), ways);
        return ways;
    }

    private static void way(Node start, Way way, String name, ArrayList<Way> ways) {
        List<String> names = start.getNames();
        List<String> oldNames = way.getNames();
        for (String startName : names) {
            if (oldNames.contains(startName))
                continue;

            // 复制 way
            Way newWay = clone(way);

            // 处理 way
            if (startName.equals(name)) {
                newWay.add(name, start.getDistance(name));
                ways.add(newWay);
            } else {
                Node nextNode = cities.get(startName);
                newWay.add(startName, start.getDistance(startName));
                way(nextNode, newWay, name, ways);
            }
        }
    }

    /**
     * Java 浅拷贝和深拷贝
     * https://www.cnblogs.com/qlky/p/7348353.html
     * @param obj
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T clone(T obj) {
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}

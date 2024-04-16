package l3m.cyber.planner.utils;

import java.util.ArrayList;

public class Auxiliere {

    public final static ArrayList<Integer> integerList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            list.add(i);
        }

        return list;
    }
}

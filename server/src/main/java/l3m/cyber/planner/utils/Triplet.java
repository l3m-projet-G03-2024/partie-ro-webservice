package l3m.cyber.planner.utils;

import lombok.Getter;

public class Triplet implements Comparable<Triplet>{

    @Getter
    private int c1;

    @Getter
    private int c2;

    private double c3;

    public Triplet(int a, int b, double poids) {
        this.c1 = a;
        this.c2 = b;
        this.c3 = poids;
    }

    public int get(int i){
        if (i == 0) {
            return c1;
        }
        return c2;
    }

    public double getPoids() {
        return this.c3;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "c1=" + c1 +
                ", c2=" + c2 +
                ", c3=" + c3 +
                '}';
    }

    @Override
    public int compareTo(Triplet t2) {
        return Double.compare(this.c3,t2.c3);
    }
}

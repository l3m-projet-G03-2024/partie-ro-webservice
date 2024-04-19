package l3m.cyber.planner.utils;

import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Planner{

    private Double[][] distances;

    private int k;

    private  int debut;

    @Getter
    private Partition p ;

    @Getter
    private ArrayList<ArrayList<Integer>> tournees ;

    private ArrayList<Double> longTournees;

    public Planner(PlannerParameter param){
        this(param.matrix(), param.k(), param.start());
    }

    public Planner(Double[][] distances, int k, int debut){
        this.distances = distances;
        this.k = k ;
        this.debut = debut;
    }

    public Planner(){
        this(null, 0, 0);
    }


    
    public PlannerResult result(){ 
          
        return new PlannerResult(tournees, longTournees);
        //return new PlannerResult(new ArrayList<ArrayList<Integer>>(), new ArrayList<Double>()); // TODO: remplacer par la ligne precedente
    }

    public void divise() {
        this.p = new PartitionKCentre(this.distances.length, this.k);
        p.partitionne(distances);
        tournees = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<k; i++) {
            tournees.add(p.getPartie(i));
        }
    }


    public Double[][] getSousMatrice(List<Integer> selec) {
        int n = selec.size();
        Double[][] sousMatrice = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sousMatrice[i][j] = distances[selec.get(i)][selec.get(j)];
            }
        }
        return sousMatrice;
    }
    
}
package l3m.cyber.planner.utils;

import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;

import java.util.ArrayList;
import java.util.List;


public class Planner{

    private Double[][] distances;

    private int k;

    private  int debut;

    private Partition p ;

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


    
}
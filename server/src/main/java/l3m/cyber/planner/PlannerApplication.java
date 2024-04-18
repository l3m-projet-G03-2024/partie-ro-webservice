package l3m.cyber.planner;

// Aucune modification necessaire dans ce fichier

import l3m.cyber.planner.utils.PartitionAlea;
import l3m.cyber.planner.utils.PartitionKCentre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);


		PartitionKCentre partitionKCentre = new PartitionKCentre(7,2);
		Double[][] dist = {
				{ 0.0 , 2.5, 3.0  , 0.1   , 17.0 , 15.5, 8.2},
				{2.5 , 0.0  , 1.0  , 5.25, 18.0 , 3.5 , 12.0},
				{3.0   , 1.0  , 0.0  , 0.0   , 3.4, 9.9 , 14.0},
				{0.1   , 5.25, 0.0 , 0.0   , 7.7, 8.8 , 6.8},
				{17.0  , 18.0 , 3.4, 7.7 , 0.0  , 2.0   , 2.2},
				{15.5, 3.5, 9.9, 8.8 , 2.0  , 0.0   , 3.3},
				{8.2 , 12.0 , 14.0 , 6.8 , 2.2, 3.3 , 0.0}
		};
		partitionKCentre.partitionne(dist);

		for(int i = 0; i<2; i++) {
			System.out.println(partitionKCentre.getPartie(i));
		}
	}

}

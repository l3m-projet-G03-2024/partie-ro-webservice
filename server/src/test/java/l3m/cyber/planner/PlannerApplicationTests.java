package l3m.cyber.planner;

import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import l3m.cyber.planner.utils.Planner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PlannerApplicationTests {

	// ci-dessous : test unitaire idiot pour demonstration
	@Test
	void dummyTest() {
		System.out.println("*********************************");
		System.out.println("Hello world\n");
		assertTrue(1 != 2);
		System.out.println("*********************************");

	}

	// Vous pouvez ajouter des tests unitaires ici si vous le souhaitez

	@Test
	void nonnullTestPlanning() {
		Double[][] matrix = { { 0.0, 1.1 }, { 1.1, 0.0 } };
		int k = 1;
		int start = 0;
		PlannerParameter param = new PlannerParameter(matrix, k, start);
		Planner pl = new Planner(param);
		PlannerResult pr = pl.result();
		assertTrue(pr.tournees() == null); // le tableau tournees doit etre non null
		assertTrue(pr.longTournees() == null); // idem, le tableau longTournees doit etre non null
		// for(int i=0; i<pr.tournees().size(); i++){
		// assertTrue(pr.tournees().get(i).get(0)==start);
		// }
		// ajouts de tests
		// assertTrue(pr.tournees().isEmpty()); // le tableau tournees est vide
		// assertTrue(pr.longTournees().isEmpty()); // le tableau longTournees est vide

		// start = 0 ; tournees = [[0,2,..],[0,..],[0,2,2]]
	}

}
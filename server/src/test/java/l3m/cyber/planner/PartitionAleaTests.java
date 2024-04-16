package l3m.cyber.planner;

import l3m.cyber.planner.utils.Partition;
import l3m.cyber.planner.utils.PartitionAlea;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PartitionAleaTests {


    @Test
    void partitionneTest() {

        PartitionAlea partitionAlea = new PartitionAlea(4,2);

        partitionAlea.partitionVide();

    }


}

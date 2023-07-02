package bike.drive.com.handler;

import bike.drive.com.enums.Direction;
import bike.drive.com.model.Bike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportHandlerTest {
    ReportHandler testObject;

    @BeforeEach
    void init(){
        testObject = new ReportHandler();
    }

    @Test
    void test_gpsReport(){
        Bike bike = null;
        Assertions.assertEquals("", testObject.getGpsReport(bike));

        bike = new Bike();
        bike.setX(3);
        bike.setY(4);
        bike.setFacing(Direction.EAST);
        Assertions.assertEquals("(3,4), EAST", testObject.getGpsReport(bike));
    }
}

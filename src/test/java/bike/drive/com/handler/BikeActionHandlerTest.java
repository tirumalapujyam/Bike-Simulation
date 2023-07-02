package bike.drive.com.handler;

import bike.drive.com.enums.Direction;
import bike.drive.com.exception.BikeHandlerException;
import bike.drive.com.model.Bike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BikeActionHandlerTest {

    BikeActionHandler testObject;

    @BeforeEach
    void init(){
        testObject = new BikeActionHandler(7, 7);
    }

    @Test
    void test_placeTheBike_when_theInitCoordinatesAreInvalid(){
        Assertions.assertNull(testObject.placeTheBike(7, 0, Direction.EAST));
        Assertions.assertNull(testObject.placeTheBike(7, 7, Direction.WEST));
        Assertions.assertNull(testObject.placeTheBike(1, 7, Direction.EAST));
        Assertions.assertNull(testObject.placeTheBike(6, 8, Direction.EAST));
        Assertions.assertNull(testObject.placeTheBike(-1, 6, Direction.EAST));
    }

    @Test
    void test_placeTheBike_withValidInitCoordinates(){
        Bike bike = testObject.placeTheBike(0, 0, Direction.EAST);
        Assertions.assertNotNull(bike);
        Assertions.assertEquals(0, bike.getX());
        Assertions.assertEquals(0, bike.getY());
        Assertions.assertEquals(Direction.EAST, bike.getFacing());
        Assertions.assertNotNull(testObject.placeTheBike(6, 6, Direction.NORTH));
        Assertions.assertNotNull(testObject.placeTheBike(1, 6, Direction.SOUTH));
        Assertions.assertNotNull(testObject.placeTheBike(3, 3, Direction.WEST));
    }

    @Test
    void test_bikeStatus_forException(){
        BikeHandlerException exp =  Assertions.assertThrows(BikeHandlerException.class, ()->{
            testObject.bikeStatus();
        });
        Assertions.assertEquals("Please initiate the bike ride by calling PLACE command.", exp.getMessage());
    }

    @Test
    void test_bikeStatus_forSuccess(){
        testObject.placeTheBike(3,4, Direction.SOUTH);
        Bike bike = testObject.bikeStatus();
        Assertions.assertNotNull(bike);
        Assertions.assertEquals(3, bike.getX());
    }

    @Test
    void test_moveForwardDirection_East(){
        testObject.placeTheBike(3,4, Direction.EAST);
        testObject.moveForward();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(4, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.EAST, bike.getFacing());
    }
    @Test
    void test_moveForwardDirection_North(){
        testObject.placeTheBike(3,4, Direction.NORTH);
        testObject.moveForward();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(5, bike.getY());
        Assertions.assertEquals(Direction.NORTH, bike.getFacing());
    }
    @Test
    void test_moveForwardDirection_South(){
        testObject.placeTheBike(3,4, Direction.SOUTH);
        testObject.moveForward();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(3, bike.getY());
        Assertions.assertEquals(Direction.SOUTH, bike.getFacing());
    }
    @Test
    void test_moveForwardDirection_West(){
        testObject.placeTheBike(3,4, Direction.WEST);
        testObject.moveForward();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(2, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.WEST, bike.getFacing());
    }

    @Test
    void test_makeRightTurn(){

        //When Facing WEST
        testObject.placeTheBike(3,4, Direction.WEST);
        testObject.makeRightTurn();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.NORTH, bike.getFacing());

        //When Facing NORTH
        testObject.makeRightTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.EAST, bike.getFacing());

        //When Facing EAST
        testObject.makeRightTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.SOUTH, bike.getFacing());

        //When Facing SOUTH
        testObject.makeRightTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.WEST, bike.getFacing());

        //in the end test gps_report
        Assertions.assertEquals("(3,4), WEST", testObject.getGpsReport());
    }

    @Test
    void test_makeLeftTurn(){

        //When Facing WEST
        testObject.placeTheBike(3,4, Direction.WEST);
        testObject.makeLeftTurn();
        Bike bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.SOUTH, bike.getFacing());

        //When Facing SOUTH
        testObject.makeLeftTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.EAST, bike.getFacing());

        //When Facing EAST
        testObject.makeLeftTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.NORTH, bike.getFacing());

        //When Facing NORTH
        testObject.makeLeftTurn();
        bike = testObject.bikeStatus();
        Assertions.assertEquals(3, bike.getX());
        Assertions.assertEquals(4, bike.getY());
        Assertions.assertEquals(Direction.WEST, bike.getFacing());

        //in the end test gps_report
        Assertions.assertEquals("(3,4), WEST", testObject.getGpsReport());
    }

}

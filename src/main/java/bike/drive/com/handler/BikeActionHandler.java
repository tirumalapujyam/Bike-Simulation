package bike.drive.com.handler;

import bike.drive.com.exception.BikeHandlerException;
import bike.drive.com.model.Bike;
import bike.drive.com.enums.Direction;

import java.util.Objects;

public class BikeActionHandler {
    private Bike bike;
    private Integer maxX;
    private Integer maxY;
    private final Integer INIT_FAIL_CODE = 1000;
    private final String INIT_FAILURE_MESSAGE = "Please initiate the bike ride by calling PLACE command.";

    public BikeActionHandler(Integer maxX, Integer maxY){
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Bike placeTheBike(Integer x, Integer y, Direction facing){
        if((x >= maxX || x < 0) || (y >= maxY || y < 0)){
            return null;
        }
        if(Objects.isNull(bike)){
            bike = new Bike();
        }
        bike.setFacing(facing);
        bike.setX(x);
        bike.setY(y);
        return bike;
    }

    public Bike bikeStatus(){
        if(Objects.isNull(bike)){
            throw new BikeHandlerException(INIT_FAILURE_MESSAGE, INIT_FAIL_CODE);
        }
        return bike;
    }

    /**
     * (0,0) corner is South-West means
     * Y axis facing North and X axis facing East
     */
    public void moveForward(){
        try {
            bike = bikeStatus();
            switch (bike.getFacing()) {
                case EAST:
                    int newX = bike.getX() + 1;
                    if (maxX > newX) {
                        bike.setX(newX);
                    }
                    break;
                case NORTH:
                    int newY = bike.getY() + 1;
                    if (maxY > newY) {
                        bike.setY(newY);
                    }
                    break;
                case WEST:
                    newX = bike.getX() - 1;
                    if (newX >= 0) {
                        bike.setX(newX);
                    }
                    break;
                case SOUTH:
                    newY = bike.getY() - 1;
                    if (newY >= 0) {
                        bike.setY(newY);
                    }
                    break;
                default:
                    // do nothing
                    break;
            }
        }catch (BikeHandlerException exp){
            // ignore the command execution
            //System.err.println(exp.getMessage());
        }
    }

    public void makeRightTurn(){
        try {
            bike = bikeStatus();
            switch (bike.getFacing()) {
                case EAST:
                    bike.setFacing(Direction.SOUTH);
                    break;
                case NORTH:
                    bike.setFacing(Direction.EAST);
                    break;
                case WEST:
                    bike.setFacing(Direction.NORTH);
                    break;
                case SOUTH:
                    bike.setFacing(Direction.WEST);
                    break;
                default:
                    // do nothing
                    break;
            }
        }catch(BikeHandlerException exp){
            // ignore the command execution
            //System.err.println(exp.getMessage());
        }
    }

    public void makeLeftTurn(){
        try {
            bike = bikeStatus();
            switch (bike.getFacing()) {
                case EAST:
                    bike.setFacing(Direction.NORTH);
                    break;
                case NORTH:
                    bike.setFacing(Direction.WEST);
                    break;
                case WEST:
                    bike.setFacing(Direction.SOUTH);
                    break;
                case SOUTH:
                    bike.setFacing(Direction.EAST);
                    break;
                default:
                    // do nothing
                    break;
            }
        }catch(BikeHandlerException exp){
            // ignore the command execution
            //System.err.println(exp.getMessage());
        }
    }

    public String getGpsReport(){
        try {
            bike = bikeStatus();
            return "(" + bike.getX() + "," + bike.getY() + "), " + bike.getFacing();
        }catch(BikeHandlerException exp){
            // ignore the command execution
            //System.err.println(exp.getMessage());
        }
        return "";
    }

}

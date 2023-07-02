package bike.drive.com.model;


import bike.drive.com.enums.Direction;

public class Bike {
    private Direction facing;
    private Integer X;
    private Integer Y;

    public Bike(){}

    public Bike(Direction direction, Integer x, Integer y){
        this.facing = direction;
        this.X = x;
        this.Y = y;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer x) {
        this.X = x;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer y) {
        this.Y = y;
    }


}

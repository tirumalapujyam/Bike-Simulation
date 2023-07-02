package bike.drive.com.handler;

import bike.drive.com.exception.BikeHandlerException;
import bike.drive.com.model.Bike;

import java.util.Objects;

public class ReportHandler {
    public String getGpsReport(Bike bike){
        try {
            if(!Objects.isNull(bike))
                return "(" + bike.getX() + "," + bike.getY() + "), " + bike.getFacing();
        }catch(BikeHandlerException exp){
            // ignore the command execution
            System.err.println(exp.getMessage());
        }
        return "";
    }

    public void printReport(Bike bike){
        System.out.println(getGpsReport(bike));
    }
}

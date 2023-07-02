package bike.drive.com;

import bike.drive.com.enums.Direction;
import bike.drive.com.handler.BikeActionHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    private static final String COM_EXIT = "EXIT";
    private static final String COM_PLACE = "PLACE";
    private static final String COM_FORWARD = "FORWARD";
    private static final String COM_TURN_LEFT = "TURN_LEFT";
    private static final String COM_TURN_RIGHT = "TURN_RIGHT";
    private static final String COM_GPS_REPORT = "GPS_REPORT";

    private BikeActionHandler handler;

    public Application(){}

    public Application(BikeActionHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args){
        Application app = new Application(new BikeActionHandler(7,7));
        List<String> fileCommands = app.getCommandsFromFile("input.txt");
        if(fileCommands.isEmpty()) {
            Scanner input = new Scanner(System.in);
            String command = "";
            while(!COM_EXIT.equalsIgnoreCase(command)){
                command = input.nextLine().trim();
                app.commandExecution(app.handler, command);
            }
        }else{
            fileCommands.forEach( command -> app.commandExecution(app.handler, command));
        }

    }

    private List<String> getCommandsFromFile(final String fileName) {
        List<String> commands = new ArrayList<>();
        InputStream fileInputStream =  this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if(!Objects.isNull(fileInputStream)){
            Scanner input = new Scanner(fileInputStream);
            while(input.hasNext()){
                commands.add(input.nextLine());
            }
            if(commands.size() > 0) commands.add(COM_EXIT);
        }
        return commands;
    }

    private void commandExecution(BikeActionHandler handler, String command) {
        switch(command){
            case COM_FORWARD:
                handler.moveForward();
                break;
            case COM_TURN_LEFT:
                handler.makeLeftTurn();
                break;
            case COM_TURN_RIGHT:
                handler.makeRightTurn();
                break;
            case COM_GPS_REPORT:
                System.out.println(handler.getGpsReport());
                break;
            case "":
                infoUseTheValidCommands();
                break;
            default:
                if(command.startsWith(COM_PLACE) && command.contains(",")){
                    String[] arguments = command.replace(COM_PLACE,"").trim().split(",");
                    try {
                        handler.placeTheBike(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]), Direction.valueOf(arguments[2]));
                    }catch(ArrayIndexOutOfBoundsException exp){
                        infoUseTheValidCommands();
                    }
                }else if(command.equalsIgnoreCase(COM_EXIT)){
                    System.out.println("Thanks for testing. Good bye.\n");
                }else{
                    infoUseTheValidCommands();
                }
            break;
        }

    }

    private static void infoUseTheValidCommands(){
        System.err.println("Please use the valid commands");
    }

}

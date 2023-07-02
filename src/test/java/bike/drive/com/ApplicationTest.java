package bike.drive.com;

import bike.drive.com.enums.Direction;
import bike.drive.com.handler.BikeActionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ApplicationTest {
    Application testApp;

    @BeforeEach
    void init(){
        testApp = new Application();
    }

    @Test
    void test_getCommandsFromFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method pm = Application.class.getDeclaredMethod("getCommandsFromFile", String.class);
        pm.setAccessible(true);
        List<String> commands = (List<String>) pm.invoke(testApp, "test_input.txt");
        Assertions.assertNotNull(commands);
        Assertions.assertTrue(commands.stream().anyMatch(command -> command.equals("GPS_REPORT")));
    }

    @Test
    void test_commandExecution_forProperCommand_PLACE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method pm = Application.class.getDeclaredMethod("commandExecution", BikeActionHandler.class, String.class);
        pm.setAccessible(true);
        BikeActionHandler mockHandler = mock(BikeActionHandler.class);
        pm.invoke(testApp, mockHandler, "PLACE 2,3,WEST");

        verify(mockHandler, atLeastOnce()).placeTheBike(anyInt(), anyInt(),any(Direction.class));
    }

    @Test
    void test_commandExecution_forWrongCommand_PLACE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method pm = Application.class.getDeclaredMethod("commandExecution", BikeActionHandler.class, String.class);
        pm.setAccessible(true);
        BikeActionHandler mockHandler = mock(BikeActionHandler.class);
        pm.invoke(testApp, mockHandler, "PLACE 2,3,");

        verify(mockHandler, never()).placeTheBike(anyInt(), anyInt(),any(Direction.class));
    }

    @Test
    void test_commandExecution_forWrongCommand_EMPTY_STRING() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method pm = Application.class.getDeclaredMethod("commandExecution", BikeActionHandler.class, String.class);
        pm.setAccessible(true);
        BikeActionHandler mockHandler = mock(BikeActionHandler.class);
        pm.invoke(testApp, mockHandler, "");

        verify(mockHandler, never()).placeTheBike(anyInt(), anyInt(),any(Direction.class));
        verify(mockHandler, never()).moveForward();
        verify(mockHandler, never()).makeLeftTurn();
        verify(mockHandler, never()).makeRightTurn();
        verify(mockHandler, never()).getGpsReport();

    }

}

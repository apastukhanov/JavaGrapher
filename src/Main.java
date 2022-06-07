import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws ScriptException {

        EventQueue.invokeLater(() -> {
            try{
                JFrame frameMain = new CubicCurve();
                frameMain.setVisible(true);
                frameMain.setExtendedState(Frame.MAXIMIZED_BOTH);
            } catch (Exception ex ){
                System.out.println(ex);
            }
        });
    }
}

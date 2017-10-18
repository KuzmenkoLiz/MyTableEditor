import controller.EditorManager;
import model.EditorModel;
import view.EditorMainFrame;

import javax.swing.*;

/**
 * Created by Лиза on 12.05.2017.
 */
public class TableEditor {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new EditorManager(new EditorModel(), new EditorMainFrame());
    }
}

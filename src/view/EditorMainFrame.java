package view;

import controller.EditorManager;
import model.Student;
import view.surface.WorkingSurface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class EditorMainFrame {

    final public static int OPEN_BUTTON_INDEX = 0;
    final public static int SAVE_BUTTON_INDEX = 1;
    final public static int SEARCH_BUTTON_INDEX = 2;
    final public static int ADD_BUTTON_INDEX = 3;
    final public static int DELETE_BUTTON_INDEX = 4;

    private JFrame mainFrame;
    private JToolBar toolBar;
    private List<JButton> toolPanelButtons;
    private List<JMenuItem> menuButtons;
    private EditorManager controller;
    private Table table;


    public EditorMainFrame() {
        toolPanelButtons = new ArrayList<>();
        menuButtons = new ArrayList<>();
        setFrame();
        mainFrame.validate();
        mainFrame.repaint();
    }

    private void setFrame() {
        this.mainFrame = new JFrame("TableEditor");
        this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addFrameListener();
        addToolPanel();
        addMenuBar();

        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.mainFrame.setVisible(true);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Меню");

        JMenuItem menuItem = new JMenuItem("Сохранить");
        menu.add(menuItem);
        menuButtons.add(menuItem);

        menuItem = new JMenuItem("Открыть");
        menu.add(menuItem);
        menuButtons.add(menuItem);

        menuItem = new JMenuItem("Добавить студента");
        menu.add(menuItem);
        menuButtons.add(menuItem);

        menuItem = new JMenuItem("Удалить студента");
        menu.add(menuItem);
        menuButtons.add(menuItem);

        menuItem = new JMenuItem("Найти студента");
        menu.add(menuItem);
        menuButtons.add(menuItem);

        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void addToolPanel() {
        toolBar = new JToolBar();
        toolBar.setPreferredSize(new Dimension(mainFrame.getWidth(), 68));
        toolBar.setFloatable(false);
        Box buttonsBox = Box.createHorizontalBox();
        setButtons(buttonsBox);
        toolBar.add(buttonsBox);
        mainFrame.add(toolBar, BorderLayout.NORTH);

    }

    private void setButtons(Box buttonsBox) {
        addButton("pics/open.png", buttonsBox);
        addButton("pics/save.png", buttonsBox);
        addButton("pics/search.png", buttonsBox);
        addButton("pics/add.png", buttonsBox);
        addButton("pics/remove.png", buttonsBox);
    }

    private void addButton(String filename, Box buttonsBox) {
        JButton button = new JButton(new ImageIcon(filename));
        button.setBackground(null);
        button.setPreferredSize(new Dimension(200, 200));
        buttonsBox.add(button);
        buttonsBox.add(Box.createHorizontalStrut(50));
        toolPanelButtons.add(button);
    }

    public void addWorkingArea(WorkingSurface workingArea) {
        mainFrame.add(workingArea.getWorkingAreaPanel());
        mainFrame.validate();
        mainFrame.repaint();
    }


    private void addFrameListener() {
        this.mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
              /*  Object[] options = {"Да", "Нет"};
                int n = JOptionPane
                        .showOptionDialog(e.getWindow(), "Выйти?",
                                "Уточнение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    e.getWindow().setVisible(false);*/
                System.exit(0);
                //}
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void update() {
        List<Student> students = controller.getAllStudents();
        this.table.update(students);
    }

    public List<JMenuItem> getMenuButtons() {
        return menuButtons;
    }

    public List<JButton> getToolPanelButtons() {
        return toolPanelButtons;
    }

}

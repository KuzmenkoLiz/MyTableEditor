package view;

import model.Student;

import javax.swing.*;
import java.util.List;

/**
 * Created by Лиза on 20.06.2017.
 */
public class Table extends JPanel {

    public void update(List<Student> students) {
        List<Student> students1 = students;

        update(students1);
        removeAll();
        revalidate();
        repaint();

    }
}
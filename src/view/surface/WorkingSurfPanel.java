package view.surface;

import model.Student;
import view.surface.WorkingSurfData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class WorkingSurfPanel extends JPanel {

    final public static int BACK_BUTTON_INDEX = 1;
    final public static int NEXT_BUTTON_INDEX = 2;
    final public static int HOME_BUTTON_INDEX = 0;
    final public static int END_BUTTON_INDEX = 3;


    private List<JButton> buttons;
    private JTextField records;
    private JTextField record;
    private WorkingSurfData workingAreaData;
    private JLabel pageAmmountLabel;

    WorkingSurfPanel(WorkingSurfData workingAreaData) {
        this.buttons = new ArrayList<>();
        this.workingAreaData = workingAreaData;
        this.setLayout(new BorderLayout());
    }

    private void addToolPanel() {
        buttons.clear();
        JToolBar toolBar = new JToolBar();
        toolBar.setPreferredSize(new Dimension(this.getWidth(), 72));
        toolBar.setFloatable(false);
        setToolBarComponents(toolBar);
        setBackground(Color.LIGHT_GRAY);
        this.add(toolBar, BorderLayout.SOUTH);
    }

    private void setToolBarComponents(JToolBar toolBar) {
        Container componentsContainer = new Container();
        toolBar.setLayout(new BorderLayout());
        componentsContainer.setLayout(new GridBagLayout());
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridy = 0;
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridx = GridBagConstraints.RELATIVE;
        cell.insets = new Insets(0, 20, 0, 5);

        records = new JTextField(5);
        JLabel label = new JLabel("Всего на страницу:");
        componentsContainer.add(label, cell);
        records.setText(String.valueOf(workingAreaData.getAmmountOfRecords()));

        componentsContainer.add(records, cell);

        record = new JTextField(5);
        JLabel label1 = new JLabel("Всего:");
        componentsContainer.add(label1, cell);
        record.setText(String.valueOf(workingAreaData.getAllrecords()));

        componentsContainer.add(record, cell);

        label = new JLabel("Стр:");
        componentsContainer.add(label, cell);
        label = new JLabel(String.valueOf(workingAreaData.getCurrentPage()) + " из " +
                String.valueOf(workingAreaData.getAmmountOfPages()));
        pageAmmountLabel = label;

        componentsContainer.add(label);

        createButtons(componentsContainer);

        toolBar.add(componentsContainer, BorderLayout.CENTER);
    }

    private void createButtons(Container componentsContainer) {
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridy = 0;
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridx = GridBagConstraints.RELATIVE;
        cell.insets = new Insets(0, 40, 0, 0);

        addButton("pics/home.png", cell, componentsContainer);
        addButton("pics/back.png", cell, componentsContainer);
        addButton("pics/next.png", cell, componentsContainer);
        addButton("pics/end.png", cell, componentsContainer);
    }


    private void addButton(String filename, GridBagConstraints cell, Container componentsContainer) {
        JButton button = new JButton(new ImageIcon(filename));
        button.setBackground(null);
        button.setPreferredSize(new Dimension(48, 48));
        componentsContainer.add(button, cell);
        buttons.add(button);
    }


    public void drawPage(List<Student> page) {
        this.removeAll();
        GridBagLayout tableLayout = new GridBagLayout();
        Container tableContainer = new Container();
        tableContainer.setLayout(tableLayout);
        printTableHeader(tableContainer);
        if (page != null) printTableBody(tableContainer, page);
        addToolPanel();
        this.add(tableContainer, BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }

    private void printTableBody(Container tableContainer, List<Student> list) {
        GridBagConstraints cell = new GridBagConstraints();

        cell.anchor = GridBagConstraints.CENTER;
        cell.fill = GridBagConstraints.BOTH;

        cell.gridy = 2;
        for (Student student : list) {
            cell.gridheight = 1;
            cell.gridwidth = 1;
            cell.weightx = 1;
            cell.gridx = GridBagConstraints.RELATIVE;
            cell.gridy++;
            tableContainer.add(addLabel(student.Surname + " " + student.FirstName + " "
                    + student.SecondName), cell);
            tableContainer.add(addLabel("улица " + student.address.street + ", " + "дом " + student.address.house + ", кв."
                    + student.address.flat), cell);
            tableContainer.add(addLabel(String.valueOf(student.FamilyMembers)), cell);
            tableContainer.add(addLabel(String.valueOf(student.livingArea.areaOfHouse)), cell);
            tableContainer.add(addLabel(String.valueOf(student.livingArea.livingAreaPerPerson)), cell);
        }
    }

    private void printTableHeader(Container tableContainer) {
        GridBagConstraints cell = new GridBagConstraints();

        cell.anchor = GridBagConstraints.CENTER;
        cell.fill = GridBagConstraints.BOTH;
        cell.gridheight = 3;
        cell.gridwidth = 1;
        cell.weightx = 1;
        cell.gridx = 0;
        cell.gridy = 0;
        tableContainer.add(addLabel("ФИО студента"), cell);

        cell.gridx = GridBagConstraints.RELATIVE;
        tableContainer.add(addLabel("Адрес проживания"), cell);

        cell.gridx = GridBagConstraints.RELATIVE;
        tableContainer.add(addLabel("Число членов семьи"), cell);

        cell.gridx = GridBagConstraints.RELATIVE;
        tableContainer.add(addLabel("Размер общей жилой площади"), cell);

        cell.gridx = GridBagConstraints.RELATIVE;
        tableContainer.add(addLabel("Жилая площадь на человека"), cell);
    }

    private JLabel addLabel(String name) {
        JLabel newLabel = new JLabel(name, SwingConstants.CENTER);
        newLabel.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.WHITE));
        return newLabel;
    }

    public java.util.List<JButton> getButtons() {
        return buttons;
    }

    public JTextField getRecords() {
        return records;
    }

    public JLabel getPageAmmountLabel() {
        return pageAmmountLabel;
    }
}

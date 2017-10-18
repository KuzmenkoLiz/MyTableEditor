package view.dialogs;

import model.Address;
import model.LivingArea;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */

public class AddPersonDialog implements ActionListener {
    public static final int ID_OK = 1;
    public static final int ID_CANCEL = 0;

    private int exitCode = ID_CANCEL;
    private JDialog addPersonDialog;
    private Student student;
    private List<JTextField> textFieldList;

    public AddPersonDialog(int ammount) {
        textFieldList = new ArrayList<>();
        this.addPersonDialog = new JDialog();
        createGUI();
    }

    private void createGUI() {
        addPersonDialog.setPreferredSize(new Dimension(800, 350));
        addPersonDialog.setTitle("Добавить студента");
        addPersonDialog.setLayout(new BorderLayout());
        createBody();
        addPersonDialog.pack();
    }

    private void createBody() {
        GridBagConstraints cell = new GridBagConstraints();
        Container dialogBody = new Container();
        dialogBody.setLayout(new GridBagLayout());
        cell.anchor = GridBagConstraints.SOUTH;
        cell.gridheight = 1;
        cell.insets = new Insets(20, 20, 0, 20);

        addLabel("ФИО студента:", cell, dialogBody, 1, 0, 0);
        addTextField(cell, dialogBody, 1, 1, 0);
        addTextField(cell, dialogBody, 1, 2, 0);
        addTextField(cell, dialogBody, 1, 3, 0);

        addLabel("Адрес (улица,дом,квартира):", cell, dialogBody, 1, 0, 1);
        addTextField(cell, dialogBody, 1, 1, 1);
        addTextField(cell, dialogBody, 1, 2, 1);
        addTextField(cell, dialogBody, 1, 3, 1);

        addLabel("Число членов семьи:", cell, dialogBody, 1, 0, 2);
        addTextField(cell, dialogBody, 1, 1, 2);

        addLabel("Жилая площадь:", cell, dialogBody, 1, 0, 3);
        addTextField(cell, dialogBody, 1, 1, 3);

        addLabel("Жилая площадь на человека:", cell, dialogBody, 1, 0, 4);
        addTextField(cell, dialogBody, 1, 1, 4);

        addSubmitButton(dialogBody);
        addPersonDialog.add(dialogBody, BorderLayout.NORTH);
    }

    private void addSubmitButton(Container dialogBody) {
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridx = 0;
        cell.insets = new Insets(40, 100, 0, 0);
        cell.anchor = GridBagConstraints.CENTER;

        JButton submitButton = new JButton("Добавить студента");

        submitButton.addActionListener(this);
        dialogBody.add(submitButton, cell);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        createNewStudent();
        exitCode = ID_OK;
        addPersonDialog.dispose();
    }

    private void createNewStudent() {

        Address address = new Address();
        LivingArea livingArea = new LivingArea();

        String studentSurname = textFieldList.get(0).getText();
        String studentName = textFieldList.get(1).getText();
        String studentSecondName = textFieldList.get(2).getText();

        String street = textFieldList.get(3).getText();
        int house = Integer.parseInt(textFieldList.get(4).getText());
        int flat = Integer.parseInt(textFieldList.get(5).getText());

        address = new Address(street, house, flat);

        int studentFamilyMembers = Integer.parseInt(textFieldList.get(6).getText());

        int areaOfHouse = Integer.parseInt(textFieldList.get(7).getText());
        int livingAreaPerPerson = Integer.parseInt(textFieldList.get(8).getText());

        livingArea = new LivingArea(areaOfHouse, livingAreaPerPerson);
        student = new Student(studentSurname, studentName, studentSecondName, studentFamilyMembers, address, livingArea);
    }


    private void addLabel(String name, GridBagConstraints cell, Container dialogBody, int width, int x, int y) {
        cell.gridx = x;
        cell.gridy = y;
        cell.ipadx = 20;
        cell.gridwidth = width;
        cell.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        dialogBody.add(label, cell);
    }

    private void addTextField(GridBagConstraints cell, Container dialogBody, int width, int x, int y) {
        cell.gridx = x;
        cell.gridy = y;
        cell.ipadx = 100;
        cell.gridwidth = width;
        cell.fill = GridBagConstraints.BOTH;
        JTextField textField = new JTextField(20);
        dialogBody.add(textField, cell);
        textFieldList.add(textField);
    }


    public int startDialog() {
        addPersonDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addPersonDialog.setModal(true);
        addPersonDialog.setVisible(true);

        return exitCode;
    }

    public Student getStudent() {
        return student;
    }

    public JDialog getDialog() {
        return addPersonDialog;
    }
}

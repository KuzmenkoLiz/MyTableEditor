package view.dialogs;

import model.EditorModel;
import view.surface.WorkingSurfManager;
import view.surface.WorkingSurface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by Лиза on 12.05.2017.
 */

public class SearchPersonDialog {


    public static final int ID_CANCEL = 0;

    private int exitCode = ID_CANCEL;
    private boolean isDeletedMod;
    private String dialogTitle;
    private EditorModel tableModel;

    private JDialog searchPersonDialog;
    final JTabbedPane tabbedPane = new JTabbedPane();


    public SearchPersonDialog(boolean isDeletedMod, EditorModel tableModel) {
        this.tableModel = tableModel;
        this.isDeletedMod = isDeletedMod;
        this.searchPersonDialog = new JDialog();
        createGUI();
    }

    private void createGUI() {
        if (!isDeletedMod) searchPersonDialog.setPreferredSize(new Dimension(900, 600));
        else searchPersonDialog.setPreferredSize(new Dimension(500, 300));
        if (isDeletedMod) dialogTitle = "Удалить студента";
        else dialogTitle = "Найти студента";
        searchPersonDialog.setTitle(dialogTitle);
        setTabs();
        searchPersonDialog.pack();
    }

    private void setTabs() {
        searchMembersSurname();
        searchSurnameLivingArea();
        searchLivingAreaLimits();
        searchMembersLivingArea();
        searchPersonDialog.add(tabbedPane);
    }

    private void searchMembersSurname() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        EditorModel searchResult = new EditorModel();
        WorkingSurface workingArea = new WorkingSurface();
        workingArea.addListener(new WorkingSurfManager(searchResult));

        if (!isDeletedMod) {
            panel.add(workingArea.getWorkingAreaPanel(), BorderLayout.CENTER);
            workingArea.validate();
        }

        java.util.List<JTextField> textFieldList = new ArrayList<>();
        GridBagConstraints cell = new GridBagConstraints();
        Container dialogBody = new Container();
        dialogBody.setLayout(new GridBagLayout());
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridheight = 1;
        cell.insets = new Insets(20, 20, 0, 0);

        addLabel("Фамилия: ", cell, dialogBody, 1, 0, 0);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 0);

        addLabel("Число членов семьи: ", cell, dialogBody, 1, 0, 1);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 1);

        JButton submitButton = new JButton(dialogTitle);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    if (isDeletedMod) {
                        JOptionPane.showMessageDialog(searchPersonDialog, "записей удалено " +
                                tableModel.deleteStudent(textFieldList.get(0).getText(),
                                        Integer.parseInt(textFieldList.get(1).getText())));
                        searchPersonDialog.dispose();
                    } else {
                        searchResult.setTableData(tableModel.searchStudent(textFieldList.get(0).getText(),
                                Integer.parseInt(textFieldList.get(1).getText())));
                        workingArea.validate();
                    }
                } else {
                    JOptionPane.showMessageDialog(searchPersonDialog, "проверьте правильность ввода");
                }
            }
            private boolean isInputValid() {
                boolean isInputCorrect = true;
                Pattern surname = Pattern.compile("([А-Я])[а-я]+");
                Pattern FamilyMembers = Pattern.compile("\\d+");
                if (!surname.matcher(textFieldList.get(0).getText()).matches() ||
                        !FamilyMembers.matcher(textFieldList.get(1).getText()).matches()) isInputCorrect = false;
                return isInputCorrect;
            }
        });
        addButton(submitButton, dialogBody);
        panel.add(dialogBody, BorderLayout.NORTH);
        tabbedPane.addTab("Фамилия и число членов семьи", panel);
    }

    private void searchSurnameLivingArea() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        EditorModel searchResult = new EditorModel();
        WorkingSurface workingArea = new WorkingSurface();
        workingArea.addListener(new WorkingSurfManager(searchResult));

        if (!isDeletedMod) {
            panel.add(workingArea.getWorkingAreaPanel(), BorderLayout.CENTER);
            workingArea.validate();
        }

        java.util.List<JTextField> textFieldList = new ArrayList<>();
        GridBagConstraints cell = new GridBagConstraints();
        Container dialogBody = new Container();
        dialogBody.setLayout(new GridBagLayout());
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridheight = 1;
        cell.insets = new Insets(20, 20, 0, 0);

        addLabel("Фамилия: ", cell, dialogBody, 1, 0, 0);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 0);

        addLabel("Занимаемая жилая площадь: ", cell, dialogBody, 1, 0, 1);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 1);

        JButton submitButton = new JButton(dialogTitle);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    if (isDeletedMod) {
                        JOptionPane.showMessageDialog(searchPersonDialog, "записей удалено " +
                                tableModel.deleteStudent1(textFieldList.get(0).getText(),
                                        Integer.parseInt(textFieldList.get(1).getText())));
                        searchPersonDialog.dispose();
                    } else {
                        searchResult.setTableData(tableModel.searchStudent1(textFieldList.get(0).getText(),
                                Integer.parseInt(textFieldList.get(1).getText())));
                        workingArea.validate();
                    }
                } else {
                    JOptionPane.showMessageDialog(searchPersonDialog, "проверьте правильность ввода");
                }
            }
            private boolean isInputValid() {
                boolean isInputCorrect = true;
                Pattern Surname = Pattern.compile("([А-Я])[а-я]+");
                Pattern areaOfHouse = Pattern.compile("\\d+");
                if (!Surname.matcher(textFieldList.get(0).getText()).matches() ||
                        !areaOfHouse.matcher(textFieldList.get(1).getText()).matches()) isInputCorrect = false;
                return isInputCorrect;
            }
        });
        addButton(submitButton, dialogBody);
        panel.add(dialogBody, BorderLayout.NORTH);
        tabbedPane.addTab("Фамилия и занимаемая жилая площадь", panel);
    }

    private void searchLivingAreaLimits() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        EditorModel searchResult = new EditorModel();
        WorkingSurface workingArea = new WorkingSurface();
        workingArea.addListener(new WorkingSurfManager(searchResult));

        if (!isDeletedMod) {
            panel.add(workingArea.getWorkingAreaPanel(), BorderLayout.CENTER);
            workingArea.validate();
        }

        java.util.List<JTextField> textFieldList = new ArrayList<>();
        GridBagConstraints cell = new GridBagConstraints();
        Container dialogBody = new Container();
        dialogBody.setLayout(new GridBagLayout());
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridheight = 1;
        cell.insets = new Insets(20, 20, 0, 0);

        addLabel("Нижний предел: ", cell, dialogBody, 1, 0, 0);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 0);

        addLabel("Верхний предел: ", cell, dialogBody, 1, 0, 1);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 1);

        JButton submitButton = new JButton(dialogTitle);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    if (isDeletedMod) {
                        JOptionPane.showMessageDialog(searchPersonDialog, "записей удалено " +
                                tableModel.deleteStudent(Integer.parseInt(textFieldList.get(0).getText()),
                                        Integer.parseInt(textFieldList.get(1).getText())));
                        searchPersonDialog.dispose();
                    } else {
                        searchResult.setTableData(tableModel.searchStudent(Integer.parseInt(textFieldList.get(0).getText()),
                                Integer.parseInt(textFieldList.get(1).getText())));
                        workingArea.validate();
                    }
                } else {
                    JOptionPane.showMessageDialog(searchPersonDialog, "проверьте правильность ввода");
                }
            }
            private boolean isInputValid() {
                boolean isInputCorrect = true;
                Pattern minResult = Pattern.compile("\\d+");
                Pattern maxResult = Pattern.compile("\\d+");
                if (!minResult.matcher(textFieldList.get(0).getText()).matches() ||
                        !maxResult.matcher(textFieldList.get(1).getText()).matches()) isInputCorrect = false;
                return isInputCorrect;
            }
        });
        addButton(submitButton, dialogBody);
        panel.add(dialogBody, BorderLayout.NORTH);
        tabbedPane.addTab("Жилая площадь на человека", panel);
    }

    private void searchMembersLivingArea() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        EditorModel searchResult = new EditorModel();
        WorkingSurface workingArea = new WorkingSurface();
        workingArea.addListener(new WorkingSurfManager(searchResult));

        if (!isDeletedMod) {
            panel.add(workingArea.getWorkingAreaPanel(), BorderLayout.CENTER);
            workingArea.validate();
        }

        java.util.List<JTextField> textFieldList = new ArrayList<>();
        GridBagConstraints cell = new GridBagConstraints();
        Container dialogBody = new Container();
        dialogBody.setLayout(new GridBagLayout());
        cell.anchor = GridBagConstraints.CENTER;
        cell.gridheight = 1;
        cell.insets = new Insets(20, 20, 0, 0);

        addLabel("Число членов семьи: ", cell, dialogBody, 1, 0, 0);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 0);

        addLabel("Нижний предел: ", cell, dialogBody, 1, 0, 1);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 1);

        addLabel("Верхний предел: ", cell, dialogBody, 1, 0, 2);
        addTextField(textFieldList, cell, dialogBody, 1, 1, 2);

        JButton submitButton = new JButton(dialogTitle);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    if (isDeletedMod) {
                        JOptionPane.showMessageDialog(searchPersonDialog, "записей удалено " +
                                tableModel.deleteStudent(Integer.parseInt(textFieldList.get(0).getText()),
                                        Integer.parseInt(textFieldList.get(1).getText()),
                                        Integer.parseInt(textFieldList.get(2).getText())));
                        searchPersonDialog.dispose();
                    } else {
                        searchResult.setTableData(tableModel.searchStudent(Integer.parseInt(textFieldList.get(0).getText()),
                                Integer.parseInt(textFieldList.get(1).getText()),
                                Integer.parseInt(textFieldList.get(2).getText())));
                        workingArea.validate();
                    }
                } else {
                    JOptionPane.showMessageDialog(searchPersonDialog, "проверьте правильность ввода");
                }
            }
            private boolean isInputValid() {
                boolean isInputCorrect = true;
                Pattern members = Pattern.compile("\\d+");
                Pattern min = Pattern.compile("\\d+");
                Pattern max = Pattern.compile("\\d+");
                if (!members.matcher(textFieldList.get(0).getText()).matches() && !min.matcher(textFieldList.get(1).getText()).matches() &&
                        !max.matcher(textFieldList.get(2).getText()).matches()) isInputCorrect = false;
                return isInputCorrect;
            }
        });
        addButton(submitButton, dialogBody);
        panel.add(dialogBody, BorderLayout.NORTH);
        tabbedPane.addTab("Число членов семьи и занимаемая жилплощадь", panel);
    }


    private void addButton(JButton button, Container tableBody) {
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridx = 0;
        cell.gridy = GridBagConstraints.RELATIVE;
        cell.insets = new Insets(20, 20, 0, 0);
        tableBody.add(button, cell);
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

    private void addTextField(List<JTextField> textFieldList, GridBagConstraints cell, Container dialogBody, int width, int x, int y) {
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
        searchPersonDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        searchPersonDialog.setModal(true);
        searchPersonDialog.setVisible(true);

        return exitCode;
    }

    public JDialog getDialog() {
        return searchPersonDialog;
    }

}
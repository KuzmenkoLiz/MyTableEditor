package controller;

import model.EditorFileFilter;
import model.EditorModel;
import model.Student;
import view.EditorMainFrame;
import view.surface.WorkingSurfManager;
import view.surface.WorkingSurface;
import view.dialogs.AddPersonDialog;
import view.dialogs.SearchPersonDialog;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class EditorManager {
    EditorMainFrame tableEditorMainFrame;
    WorkingSurface workingArea;
    EditorModel tableModel;

    public EditorManager(EditorModel tableModel, EditorMainFrame tableEditorMainFrame) {
        this.tableEditorMainFrame = tableEditorMainFrame;
        this.workingArea = new WorkingSurface();
        this.workingArea.addListener(new WorkingSurfManager(tableModel));
        this.tableEditorMainFrame.addWorkingArea(workingArea);
        this.tableModel = tableModel;
        addListeners();
        workingArea.validate();
        tableEditorMainFrame.update();
    }

    private void addListeners() {
        addButtonsListeners();
        addMenuListeners();
    }

    private void addButtonsListeners() {
        tableEditorMainFrame.getToolPanelButtons().get(EditorMainFrame.OPEN_BUTTON_INDEX).
                addActionListener(new OpenButtonListener());
        tableEditorMainFrame.getToolPanelButtons().get(EditorMainFrame.SAVE_BUTTON_INDEX).
                addActionListener(new SaveButtonListener());
        tableEditorMainFrame.getToolPanelButtons().get(EditorMainFrame.ADD_BUTTON_INDEX).
                addActionListener(new AddButtonListener());
        tableEditorMainFrame.getToolPanelButtons().get(EditorMainFrame.DELETE_BUTTON_INDEX).
                addActionListener(new DeleteButtonListener());
        tableEditorMainFrame.getToolPanelButtons().get(EditorMainFrame.SEARCH_BUTTON_INDEX).
                addActionListener(new SearchButtonListener());
    }

    private void addMenuListeners() {
        tableEditorMainFrame.getMenuButtons().get(EditorMainFrame.SAVE_BUTTON_INDEX).
                addActionListener(new SaveButtonListener());
        tableEditorMainFrame.getMenuButtons().get(EditorMainFrame.OPEN_BUTTON_INDEX).
                addActionListener(new OpenButtonListener());
        tableEditorMainFrame.getMenuButtons().get(EditorMainFrame.ADD_BUTTON_INDEX).
                addActionListener(new AddButtonListener());
        tableEditorMainFrame.getMenuButtons().get(EditorMainFrame.DELETE_BUTTON_INDEX).
                addActionListener(new DeleteButtonListener());
        tableEditorMainFrame.getMenuButtons().get(EditorMainFrame.SEARCH_BUTTON_INDEX).
                addActionListener(new SearchButtonListener());
    }

    public List<Student> getAllStudents() {
        return tableModel.getTableData();
    }

    class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            JFileChooser fileChooser = new JFileChooser();
            EditorFileFilter xmlFilter = new EditorFileFilter(".xml");

            fileChooser.addChoosableFileFilter(xmlFilter);

            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel.saveAction(fileChooser.getSelectedFile().getAbsolutePath() + ".xml");
                } catch (TransformerException exeption) {
                    exeption.printStackTrace();
                    JOptionPane.showMessageDialog(workingArea.getWorkingAreaPanel(), "ошибка записи");
                } catch (IOException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(workingArea.getWorkingAreaPanel(), "ошибка записи");
                }
            }
        }
    }

    class OpenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            JFileChooser fileChooser = new JFileChooser();

            EditorFileFilter xmlFilter = new EditorFileFilter(".xml");

            fileChooser.addChoosableFileFilter(xmlFilter);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {
                    tableModel.openAction(fileChooser.getSelectedFile().getAbsolutePath());
                    workingArea.getWorkingAreaData().setAllrecords(tableModel.getTableData().size());
                    workingArea.validate();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(workingArea.getWorkingAreaPanel(), "ошибка чтения");
                }
            }
        }
    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AddPersonDialog dialog = new AddPersonDialog(workingArea.getAmmount());
            dialog.getDialog().setLocationRelativeTo(workingArea.getWorkingAreaPanel());
            if (dialog.startDialog() == AddPersonDialog.ID_OK) {
                tableModel.addStudent(dialog.getStudent());
                workingArea.getWorkingAreaData().setAllrecords(tableModel.getTableData().size());
                workingArea.validate();
            }
        }
    }

    class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        SearchPersonDialog dialog = new SearchPersonDialog(true,tableModel);
        dialog.getDialog().setLocationRelativeTo(workingArea.getWorkingAreaPanel());
        dialog.startDialog();
        workingArea.validate();
        }
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            workingArea.validate();
            SearchPersonDialog dialog = new SearchPersonDialog(false,tableModel);
            dialog.getDialog().setLocationRelativeTo(workingArea.getWorkingAreaPanel());
            dialog.startDialog();
            workingArea.validate();
        }
    }
}

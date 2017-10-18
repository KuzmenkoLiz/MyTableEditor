package view.surface;

import model.EditorModel;
import model.Student;
import view.surface.WorkingSurfListener;
import view.surface.WorkingSurface;

import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class WorkingSurfManager implements WorkingSurfListener {
    private EditorModel tableModel;

    public WorkingSurfManager(EditorModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public void validateWorkingArea(WorkingSurface workingArea) {
        List<Student> page;
        page = tableModel.getPage(workingArea.getCurrentPage(), workingArea.getAmmountOfRecords());
        if (tableModel.getTableData() != null) workingArea.setAmmountOfPages(tableModel.getTableData().size());
        workingArea.getWorkingAreaData().setAllrecords(tableModel.getTableData().size());
        workingArea.getWorkingAreaPanel().drawPage(page);
    }
}

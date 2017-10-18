package view.surface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class WorkingSurface {

    private List<WorkingSurfListener> listeners = new ArrayList<WorkingSurfListener>();

    private WorkingSurfData workingAreaData;
    private WorkingSurfPanel workingAreaPanel;

    public WorkingSurface() {
        this.workingAreaData = new WorkingSurfData();
        this.workingAreaPanel = new WorkingSurfPanel(workingAreaData);
        workingAreaPanel.drawPage(null);
        setViewListeners();
    }

    private void setViewListeners() {
        workingAreaPanel.getRecords().addActionListener(e -> {
            workingAreaData.setAmmountOfRecords(Integer.parseInt(workingAreaPanel.getRecords().getText()));
            workingAreaData.setCurrentPage(1);
            workingAreaPanel.getPageAmmountLabel().setText(String.valueOf(workingAreaData.getCurrentPage())
                    + "из" + String.valueOf(workingAreaData.getAmmountOfPages()));
            validate();
        });

        workingAreaPanel.getButtons().get(WorkingSurfPanel.BACK_BUTTON_INDEX).addActionListener(e -> {
            if (workingAreaData.getCurrentPage() > 1) {
                workingAreaData.setCurrentPage(workingAreaData.getCurrentPage() - 1);
                workingAreaPanel.getPageAmmountLabel().setText(String.valueOf(workingAreaData.getCurrentPage()) +
                        "из" + String.valueOf(workingAreaData.getAmmountOfPages()));
                validate();
            }
        });

        workingAreaPanel.getButtons().get(WorkingSurfPanel.NEXT_BUTTON_INDEX).addActionListener(e -> {
            if (workingAreaData.getCurrentPage() < workingAreaData.getAmmountOfPages()) {
                workingAreaData.setCurrentPage(workingAreaData.getCurrentPage() + 1);
                workingAreaPanel.getPageAmmountLabel().setText(String.valueOf(workingAreaData.getCurrentPage()) +
                        "из" + String.valueOf(workingAreaData.getAmmountOfPages()));
                validate();
            }
        });

        workingAreaPanel.getButtons().get(WorkingSurfPanel.HOME_BUTTON_INDEX).addActionListener(e -> {
            workingAreaData.setCurrentPage(1);
            workingAreaPanel.getPageAmmountLabel().setText(String.valueOf(workingAreaData.getCurrentPage()) +
                    "из" + String.valueOf(workingAreaData.getAmmountOfPages()));
            validate();
        });

        workingAreaPanel.getButtons().get(WorkingSurfPanel.END_BUTTON_INDEX).addActionListener(e -> {
            workingAreaData.setCurrentPage(workingAreaData.getAmmountOfPages());
            workingAreaPanel.getPageAmmountLabel().setText(String.valueOf(workingAreaData.getCurrentPage()) +
                    "из" + String.valueOf(workingAreaData.getAmmountOfPages()));
            validate();
        });
    }

    public void addListener(WorkingSurfListener listener) {
        listeners.add(listener);
    }

    public void removeListener(WorkingSurfListener listener) {
        listeners.remove(listener);
    }

    public void validate() {
        for (WorkingSurfListener listener : listeners) {
            listener.validateWorkingArea(this);
        }
        setViewListeners();
    }

    public WorkingSurfPanel getWorkingAreaPanel() {
        return workingAreaPanel;
    }

    public int getCurrentPage() {
        return workingAreaData.getCurrentPage();
    }

    public int getAmmountOfRecords() {
        return workingAreaData.getAmmountOfRecords();
    }

    public int getAmmount() {
        return workingAreaData.getAmmount();
    }

    public void setAmmountOfPages(int pageSize) {
        if (pageSize != 0)
            workingAreaData.setAmmountOfPages((int) Math.ceil((double) pageSize / workingAreaData.getAmmountOfRecords() ));
        else workingAreaData.setAmmountOfPages(1);
    }

    public WorkingSurfData getWorkingAreaData() {
        return workingAreaData;
    }

}

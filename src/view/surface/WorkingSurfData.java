package view.surface;

/**
 * Created by Лиза on 12.05.2017.
 */
public class WorkingSurfData {
    private int ammount;
    private int allrecords;
    private int currentPage;
    private int ammountOfPages;
    private int ammountOfRecords;

    WorkingSurfData() {
        ammount = 0;
       // allrecords = 100;
        ammountOfPages = 1;
        ammountOfRecords = 5;
        currentPage = 1;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setAmmountOfPages(int ammountOfPages) {
        this.ammountOfPages = ammountOfPages;
    }

    public void setAmmountOfRecords(int ammountOfRecords) {
        this.ammountOfRecords = ammountOfRecords;
        if (ammountOfRecords <= 0) this.ammountOfRecords = 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getAmmountOfPages() {
        return ammountOfPages;
    }

    public int getAmmountOfRecords() {
        return ammountOfRecords;
    }

    public int getAmmount() { return ammount; }

    public int getAllrecords() {
        return allrecords; }

    public void setAllrecords(int allrecords) {
        this.allrecords = allrecords;
    }



}
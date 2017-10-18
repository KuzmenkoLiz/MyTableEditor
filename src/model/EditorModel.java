package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лиза on 12.05.2017.
 */
public class EditorModel {
    private List<Student> tableData;

    public EditorModel() {
        tableData = new ArrayList<>();
        addALotOfStudents(200);
    }

    public void addALotOfStudents(int n){
        String[] surnames = {
                "Кузьменко",
                "Кирьянова",
                "Толмачева",
                "Хомицкий",
                "Иванов",
                "Алишевич",
                "Хорошко",
                "Ива",
                "Дюбин",
                "Милашевская"
        };

        String[] names = {
                "Александр",
                "Елизавета",
                "Виктор",
                "Анна",
                "Варвара",
                "Ольга",
                "Владимир",
                "Ярослав",
                "Дмитрий",
                "Никита",
                "Михаил"
        };

        String[] secondNames = {
                "Владимировна",
                "Александрович",
                "Игоревна",
                "Олегович",
                "Михайлович",
                "Юрьевна",
                "Эдуардович"
        };

        String[] street = {
                "Карвата",
                "Мельникайте",
                "Связистов",
                "Дзержиского",
                "Загородная",
                "Солнечная"
        };

        for (int i=0; i<n; i++) {
            Student student = new Student();
            LivingArea livingArea = new LivingArea();
            Address address = new Address();

            student.livingArea = livingArea;
            student.address = address;

            student.FirstName = names[(int) (Math.random() * names.length)];
            student.SecondName = secondNames[(int) (Math.random() * secondNames.length)];
            student.Surname = surnames[(int) (Math.random() * surnames.length)];
            student.FamilyMembers = (int) (Math.random() * 8);

            student.livingArea = new LivingArea();
            student.livingArea.areaOfHouse = (int) (Math.random() * 100);
            student.livingArea.livingAreaPerPerson = (int) (Math.random() * 40);

            student.address.street = street[(int) (Math.random() * street.length)];
            student.address.flat = (int) (Math.random() * 100);
            student.address.house = (int) (Math.random() * 100);

            addStudent(student);
        }
    }

    public void addStudent(Student student) {
        tableData.add(student);
    }


    public List<Student> getTableData() {
        return tableData;
    }

    public List<Student> getPage(int pageNumber, int amountOfRecords) {
        List<Student> page = new ArrayList<>();
        int firstRecordIndex = (pageNumber - 1) * amountOfRecords;
        for (int index = firstRecordIndex; index < firstRecordIndex + amountOfRecords; index++) {
            if (index > tableData.size() - 1) break;
            page.add(tableData.get(index));
        }
        return page;
    }

    public List<Student> searchStudent(String surname, int FamilyMembers) {
        List<Student> searchResult = new ArrayList<>();

        for (Student student : tableData) {
            boolean isSurnameMatches = student.Surname.equals(surname);
            if ((isSurnameMatches) && (student.FamilyMembers == FamilyMembers)) searchResult.add(student);
        }

        return searchResult;
    }

    public int deleteStudent(String surname, int FamilyMembers){
        List<Student> searchResult = this.searchStudent(surname, FamilyMembers);
        tableData.removeAll(searchResult);
        return searchResult.size();
    }

    public List<Student> searchStudent(int minResult, int maxResult) {
        List<Student> searchResult = new ArrayList<>();
        for (Student student : tableData) {
            if (student.livingArea.livingAreaPerPerson >= minResult && student.livingArea.livingAreaPerPerson <= maxResult)
                searchResult.add(student);
        }
        return searchResult;
    }

    public int deleteStudent(int minResult, int maxResult){
        List<Student> searchResult = this.searchStudent(minResult, maxResult);
        tableData.removeAll(searchResult);
        return searchResult.size();
    }

    public List<Student> searchStudent(int members, int min, int max) {
        List<Student> searchResult = new ArrayList<>();
        for (Student student : tableData) {
            if ((student.FamilyMembers == members) && (student.livingArea.areaOfHouse >= min && student.livingArea.areaOfHouse <= max))
                searchResult.add(student);
        }
        return searchResult;
    }

    public int deleteStudent(int members, int min, int max){
        List<Student> searchResult = this.searchStudent(members, min, max);
        tableData.removeAll(searchResult);
        return searchResult.size();
    }

    public List<Student> searchStudent1(String surname, int areaOfHouse) {
        List<Student> searchResult = new ArrayList<>();

        for (Student student : tableData) {
            boolean isSurnameMatches = student.Surname.equals(surname);
            if ((student.livingArea.areaOfHouse == areaOfHouse) && (isSurnameMatches)) searchResult.add(student);
        }
        return searchResult;
    }

    public int deleteStudent1(String surname, int areaOfHouse){
        List<Student> searchResult = this.searchStudent1(surname, areaOfHouse);
        tableData.removeAll(searchResult);
        return searchResult.size();
    }

    public void saveAction(String path) throws TransformerException, IOException {
        writeParamXML(paramLangXML(), path);
    }


    private DocumentBuilder paramLangXML() {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private void writeParamXML(DocumentBuilder builder, String path) throws TransformerException, IOException {

        Document document = builder.newDocument();
        Element RootElement = document.createElement("studentsList");

        for (Student student : tableData) {
            Element tableRow = document.createElement("student");

            Element studentSurname = document.createElement("Surname");
            studentSurname.appendChild(document.createTextNode(student.Surname));
            tableRow.appendChild(studentSurname);

            Element studentFirstName = document.createElement("FirstName");
            studentFirstName.appendChild(document.createTextNode(student.FirstName));
            tableRow.appendChild(studentFirstName);

            Element studentSecondName = document.createElement("SecondName");
            studentSecondName.appendChild(document.createTextNode(student.SecondName));
            tableRow.appendChild(studentSecondName);

            Element address = document.createElement("address");
            //      tableRow.appendChild(address);

            Element street = document.createElement("street");
            street.appendChild(document.createTextNode(student.address.street));
            address.appendChild(street);

            Element house = document.createElement("house");
            house.appendChild(document.createTextNode(String.valueOf(student.address.house)));
            address.appendChild(house);

            Element flat = document.createElement("flat");
            flat.appendChild(document.createTextNode(String.valueOf(student.address.flat)));
            address.appendChild(flat);

            tableRow.appendChild(address);

            Element livingArea = document.createElement("livingArea");
            // tableRow.appendChild(livingArea);

            Element areaOfHouse = document.createElement("areaOfHouse");
            areaOfHouse.appendChild(document.createTextNode(String.valueOf(student.livingArea.areaOfHouse)));
            livingArea.appendChild(areaOfHouse);

            Element livingAreaPerPerson = document.createElement("livingAreaPerPerson");
            livingAreaPerPerson.appendChild(document.createTextNode(String.valueOf(student.livingArea.livingAreaPerPerson)));
            livingArea.appendChild(livingAreaPerPerson);

            tableRow.appendChild(livingArea);

            Element studentFamilyMembers = document.createElement("FamilyMembers");
            studentFamilyMembers.appendChild(document.createTextNode(String.valueOf(student.FamilyMembers)));
            tableRow.appendChild(studentFamilyMembers);

            RootElement.appendChild(tableRow);
        }

        document.appendChild(RootElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(path)));

    }

    public void setTableData(List<Student> tableData) {
        this.tableData = tableData;
    }

    public void openAction(String path) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
    //    DefaultHandler handler = new DefaultHandler();
        tableData = new EditorParser().parse(path, saxParser);
    }
}


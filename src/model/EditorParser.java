package model;
/**
 * Created by Лиза on 12.05.2017.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.util.ArrayList;
import java.util.List;

public class EditorParser {
    final String STUDENT = "student";
    final String NAME = "FirstName";
    final String SURNAME = "Surname";
    final String SECONDNAME = "SecondName";
    final String ADDRESS = "address";
    final String STREET = "street";
    final String HOUSE = "house";
    final String FLAT = "flat";
    final String FAMILY_MEMBERS = "FamilyMembers";
    final String LIVING_AREA = "livingArea";
    final String AREA_OF_HOUSE = "areaOfHouse";
    final String LIVING_AREA_PER_PERSON = "livingAreaPerPerson";

    private List<Student> table;
    private Student currentStudent;
    private Address currentAddress;
    private LivingArea currentLivingArea;
    private String tag;

    EditorParser() {
        table = new ArrayList<>();
        currentStudent = new Student();
        currentAddress = new Address();
        currentLivingArea = new LivingArea();
    }


    public List<Student> parse(String path, SAXParser parser) throws Exception {
        parser.parse(path, new DefaultHandler() {

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                tag = qName;
                switch (tag) {
                    case STUDENT:
                      currentStudent = new Student();
                      table.add(currentStudent);
                        break;
                    case ADDRESS:
                        currentAddress = new Address();
                        currentStudent.address = currentAddress;
                        break;
                    case LIVING_AREA:
                        currentLivingArea = new LivingArea();
                        currentStudent.livingArea = currentLivingArea;
                        break;
                }
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                switch (tag) {
                    case SURNAME:
                        currentStudent.Surname = new String(ch, start, length);
                        //   System.out.println("Фамилия" + new String(ch, start, length));
                        break;
                    case NAME:
                        currentStudent.FirstName = new String(ch, start, length);
                        break;
                    case SECONDNAME:
                        currentStudent.SecondName = new String(ch, start, length);
                        break;
                    case FAMILY_MEMBERS:
                        currentStudent.FamilyMembers = Integer.valueOf(new String(ch, start, length));
                        break;
                    case STREET:
                        currentAddress.street = new String(ch, start, length);
                        break;
                    case HOUSE:
                        currentAddress.house = Integer.valueOf(new String(ch, start, length));
                        break;
                    case FLAT:
                        currentAddress.flat = Integer.valueOf(new String(ch, start, length));
                        break;
                    case AREA_OF_HOUSE:
                        currentLivingArea.areaOfHouse = Integer.valueOf(new String(ch, start, length));
                        break;
                    case LIVING_AREA_PER_PERSON:
                        currentLivingArea.livingAreaPerPerson = Integer.valueOf(new String(ch, start, length));
                        break;
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
                //table.add(currentStudent);
                //currentStudent.address = currentAddress;
                //currentStudent.livingArea = currentLivingArea;
            }

            @Override
            public void startDocument() throws SAXException {
                System.out.println("Start parse XML...");
            }

            @Override
            public void endDocument() throws SAXException {
            }
        });
        return table;
    }
}
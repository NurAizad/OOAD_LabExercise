package user;

import java.util.ArrayList;

public class Student extends User
{
    private ArrayList<String> studentList = new ArrayList<>();

    public Student()
    {
        fillStudentNames();
    }

    public void fillStudentNames()
    {
        nameList(studentList, "Student");
    }

    public ArrayList<String> getStudentList()
    {
        return studentList;
    }

}
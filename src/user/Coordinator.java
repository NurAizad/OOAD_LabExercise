package user;

import java.util.ArrayList;

public class Coordinator extends User
{
    private ArrayList<String> coordinatorList = new ArrayList<>();

    public Coordinator()
    {
        fillCoordinatorNames();
    }

    public void fillCoordinatorNames()
    {
        nameList(coordinatorList, "Student");
    }

    public ArrayList<String> getCoordinatorList()
    {
        return coordinatorList;
    }

}
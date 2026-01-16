package user;

import java.util.ArrayList;

public class Evaluator extends User
{
    private ArrayList<String> evaluatorList = new ArrayList<>();

    public Evaluator()
    {
        fillEvaluatorNames();
    }

    public void fillEvaluatorNames()
    {
        nameList(evaluatorList, "Student");
    }

    public ArrayList<String> getEvaluatorList()
    {
        return evaluatorList;
    }

}
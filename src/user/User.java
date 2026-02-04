package user;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

public class User
{
    //private ArrayList<String> nameList = new ArrayList<>();
    public void nameList(ArrayList<String> arrName, String role)
    {
        File file = new File("csvFiles/usersCSV.csv");
        try
        {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) 
            {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                String name = parts[2];
                if (parts[3].equals(role))
                {
                    arrName.add(name);
                }
                
            }
            fileReader.close();

        }

            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(null, "Error reading user file.","Error!", JOptionPane.ERROR_MESSAGE);
            }
    }   
}


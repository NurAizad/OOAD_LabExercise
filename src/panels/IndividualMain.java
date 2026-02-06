package panels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class IndividualMain {

    CardLayout cardLayout = new CardLayout();
    JPanel cardManager = new JPanel(cardLayout);

    public static void main(String[] args) {
        new IndividualMain();
    }
}

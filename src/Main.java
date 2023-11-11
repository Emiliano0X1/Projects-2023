
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)throws Exception {

        int ancho= 700;
        int alto= 700;

        JFrame frame= new JFrame("Given-Taken: Snake Game");
        frame.setVisible(true);
        frame.setSize(ancho,alto);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Given try1= new Given(ancho,alto);

        frame.add(try1);
        frame.pack();
        try1.requestFocus();





    }

}


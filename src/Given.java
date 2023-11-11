
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Given extends JPanel implements ActionListener,KeyListener{
    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
       move();

       if(gameOver){
           loop.stop();
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W && velocityY !=1){

            velocityX=0;
            velocityY=-1;
        }

        else if(e.getKeyCode()==KeyEvent.VK_S && velocityY !=-1){
            velocityX=0;
            velocityY=1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_A && velocityX !=1){
            velocityX=-1;
            velocityY=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_D && velocityX !=-1){
            velocityX=1;
            velocityY=0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class Taken{
        int x;
        int y;

        Taken(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    int ancho;
    int alto;
    int size=25;

    //Snake
    Taken snakehead;
    ArrayList<Taken> snakebody;

    //Power
    Taken power;
    Random random;

//mecanica
    Timer loop;
    int velocityX;
    int velocityY;

    boolean gameOver= false;
    Given(int ancho,int alto){
        this.ancho= ancho;
        this.alto=alto;
        setPreferredSize(new Dimension(this.ancho,this.alto));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);


        snakehead= new Taken(5, 5);
        snakebody= new ArrayList<Taken>();

        power= new Taken(10, 10);
        random=new Random();
        drivetru();

        velocityX=0;
        velocityY=0;

        loop= new Timer(100,this);
        loop.start();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        //xd

        for(int i=0;i< alto/size ;i++){
            g.drawLine(i*size,0,i*size,alto);
            g.drawLine(0,i*size,ancho,i*size);

        }

        //powers
        g.setColor(Color.red);
        g.fillRect(power.x*size, power.y*size,size,size);
        //Player
        g.setColor(Color.BLUE);
        g.fillRect(snakehead.x*size, snakehead.y*size, size,size);
        //Body
        for(int i=0;i< snakebody.size();i++){

            Taken snakeroller= snakebody.get(i);
            g.fillRect(snakeroller.x*size, snakeroller.y*size,size,size);


        }

        //Score
        g.setFont(new Font("Arial",Font.PLAIN,20));
        if(gameOver){
            g.setColor(Color.cyan);
            g.drawString("Game Over :( : " + String.valueOf(snakebody.size()),size-20,size);

        }
        else{
            g.drawString("Score : " + String.valueOf(snakebody.size()),size-20,size);
        }
    }

    public void drivetru() {
        power.x = random.nextInt(ancho / size);
        power.y = random.nextInt(alto / size);
    }

    public boolean collision(Taken taken1,Taken taken2){

        return taken1.x == taken2.x && taken1.y==taken2.y;

    }


    public void move(){

        //Power move
        if(collision(snakehead,power)){
            snakebody.add(new Taken(power.x,power.y));
            drivetru();
        }

        //SnakeBody move
        for(int i=snakebody.size()-1;i>=0;i--){
            Taken snakeroller = snakebody.get(i);

            if(i==0){

                snakeroller.x= snakehead.x;
                snakeroller.y= snakehead.y;

            }

            else{
                  Taken previewSnakeroll = snakebody.get(i-1);
                  snakeroller.x= previewSnakeroll.x;
                  snakeroller.y= previewSnakeroll.y;

            }

        }

        //Snakehead move
        snakehead.x += velocityX;
        snakehead.y += velocityY;

        //Game Over

        for(int i=0;i< snakebody.size();i++){

            Taken snakeroller = snakebody.get(i);

            if(collision(snakehead,snakeroller)){
                gameOver=true;
            }


        }

        if(snakehead.x*size<0 || snakehead.x*size>ancho || snakehead.y*size<0 || snakehead.y>alto){

            gameOver=true;

        }

    }



}

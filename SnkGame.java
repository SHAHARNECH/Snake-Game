import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * The SnkGame implements the old 'Snake' game we used to have
 * play around and try to eat as many of the apples without hitting the boundaries or yourself
 *
 * @author  Shahar
 * @version 1.0
 */
public class SnkGame extends JFrame implements Runnable, KeyListener {
    Thread thread;
    Snake snake;
    Apple apple;
    boolean lostGame;


    public SnkGame(){
        super("Oldies- Snake Game");
        snake= new Snake();
        apple = new Apple(snake);
        lostGame=false;
        addKeyListener(this);
        thread = new Thread(this);
        thread.start();
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    public void paint(Graphics g){
        g.fillRect(0,0,500,500);
        if(!lostGame){
            snake.draw(g);
            apple.draw(g);
        }
        else{
            g.setColor(Color.RED);
            g.drawString("****GAME OVER!****", 200,200);
            g.drawString("         Score: "+apple.getTotal(), 200,260);
        }

    }

    @Override
    public void run() {
        for(;;){ //infinite loop for game
            if(!lostGame) { //while not lost -> move-> check if move is valid-> check if eaten apple..
                snake.move();
                this.isValidSnake();
                apple.hasEaten();
            }
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
    }



    private void isValidSnake() {
        //outOfBounds or bumps into itself
        if(snake.getHeadX()<0 || snake.getHeadX()>491){
            lostGame= true;}
        if(snake.getHeadY()<0 || snake.getHeadY()>491){
            lostGame= true;}
        if(snake.isMove() && snake.bump()){
            lostGame=true;}
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!snake.isMove()){             //start game
            if(e.getKeyCode()== KeyEvent.VK_UP||e.getKeyCode()== KeyEvent.VK_DOWN||
                    e.getKeyCode()== KeyEvent.VK_LEFT||e.getKeyCode()== KeyEvent.VK_RIGHT){
                snake.setMove(true);
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_UP){
            if(snake.getDirectionY()!=1){ //already going up
                snake.setDirectionY(-1);
                snake.setDirectionX(0);
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_DOWN){
            if(snake.getDirectionY()!=-1){ //already going down
                snake.setDirectionY(1);
                snake.setDirectionX(0);
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(snake.getDirectionX()!=-1){ //already going left
                snake.setDirectionY(0);
                snake.setDirectionX(1);
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            if(snake.getDirectionX()!=1){ //already going right
                snake.setDirectionY(0);
                snake.setDirectionX(-1);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args){
        SnkGame game = new SnkGame();
    }
}

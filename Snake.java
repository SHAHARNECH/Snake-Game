import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private ArrayList<Point> snkPoint;
    private int directionX,directionY; // x=1 right x=-1 left, y=1 down y=-1 up
    private boolean move, ate;
    private final int STARTSIZE=10, START=200,WIDTH=9;

    public Snake(){
        snkPoint=new ArrayList<>();
        directionX=0;
        directionY=0;
        move=false;
        ate=false;
        snkPoint.add(new Point(START,START));
        for(int i=1; i<STARTSIZE;i++){
            snkPoint.add(new Point(START-i*WIDTH,START));
        }
    }
public void draw(Graphics g){
        g.setColor(Color.CYAN);
        for(Point p: snkPoint){
            g.fillRect(p.getX(),p.getY(),WIDTH,WIDTH);
        }

}
public void ate(boolean b){ate=b;}

public void increase(){
        if(ate){   //add length to snake
            for(int i=1; i<10;i++){
                snkPoint.add(new Point(snkPoint.get(snkPoint.size()-1).getX()-i*WIDTH,snkPoint.get(snkPoint.size()-1).getY()));
            }
        }

}
    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public int getHeadX(){
        return snkPoint.get(0).getX();
    }
    public int getHeadY(){
        return snkPoint.get(0).getY();
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void move(){
        if(move){
            Random r = new Random(490);
            Point oldStart = snkPoint.get(0);
            Point oldLast = snkPoint.get(snkPoint.size()-1);
            Point newStart = new Point(oldStart.getX()+ directionX*WIDTH,oldStart.getY()+ directionY*WIDTH);
            for(int i= snkPoint.size()-1;i>0;i--){
                snkPoint.set(i,snkPoint.get(i-1)); //each point moves forward one point
            }//place zero is left
            snkPoint.set(0,newStart);
        }

    }

    public boolean bump(){
        int x= this.getHeadX();
        int y = this.getHeadY();
        for(int i=1;i<snkPoint.size();i++){   //head is at point that is already in list-> bumped into itself
            if(snkPoint.get(i).getX() == x && snkPoint.get(i).getY()==y){
                return true;
            }
        }
        return false;
    }

}

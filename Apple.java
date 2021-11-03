import java.awt.*;

public class Apple {
    private int x, y,total;
    private Snake snk;
    public Apple(Snake snake){
        snk= snake;
        x= (int)(Math.random()*490+1);
        y= (int)(Math.random()*490+1);
        total=0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTotal() {
        return total;
    }


    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(getX(),getY(),9,9);
    }

    public boolean hasEaten(){
        if(snk.isMove()){
            int snakeX= snk.getHeadX();
            int snakeY= snk.getHeadY();
            //snakes head where apple is
            if((snakeX >= x-5  && snakeX<= x+9) &&(snakeY >= y-5  && snakeY<= y+9)){ //needs adjust
                total++;
                setX((int)(Math.random()*480+1));
                setY((int)(Math.random()*480+1));
                snk.ate(true);
                snk.increase();
                return true;
            }
        }
        return false;
    }

}

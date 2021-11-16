import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y){
        super(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "@");
    }

    public Position move(){
        Random r = new Random();
        int ran = r.nextInt(4);
        switch (ran){
            case 0: return new Position(position.getX()-1, position.getY());
            case 1: return new Position(position.getX()+1, position.getY());
            case 2: return new Position(position.getX(), position.getY()-1);
            case 3: return new Position(position.getX(), position.getY()+1);
        }
        return new Position(position.getX(), position.getY());
    }
}

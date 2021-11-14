import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width, height;
    private Position position = new Position(10, 10);
    private Hero hero = new Hero(position);

    public Hero getHero(){
        return hero;
    }

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void draw(Screen screen) throws IOException {
        screen.setCharacter(hero.getPosition().getX(), hero.getPosition().getY(), TextCharacter.fromCharacter('X')[0]);
    }
    public void processKey(KeyStroke key){
        //System.out.println(key);
        String keyString = key.getKeyType().toString();
        switch(keyString){
            case "ArrowLeft": moveHero(hero.moveLeft()); break;
            case "ArrowRight": moveHero(hero.moveRight()); break;
            case "ArrowDown": moveHero(hero.moveDown()); break;
            case "ArrowUp": moveHero(hero.moveUp()); break;
        }
    }

    public void moveHero(Position position){
        System.out.print(position.getX());
        System.out.print("-");
        System.out.println(position.getY());
        if(canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position){
        if(position.getX() < width && 0 <= position.getX())
            if(position.getY() < height && 0 <= position.getY())
                return true;
        return false;
    }
}

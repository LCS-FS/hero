import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width, height;
    private final Position position = new Position(10, 10);
    private final Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

    public Hero getHero(){
        return hero;
    }

    public Arena(int width, int height){
        this.width = width;
        this.height = height;

        this.walls = createWalls();
        hero = new Hero(position.getX(), position.getY());
        this.coins = createCoins();

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

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);

        for(Wall wall: walls){
            wall.draw(graphics);
        }

        for(Coin coin : coins){
            coin.draw(graphics);
        }
    }
    public void processKey(KeyStroke key){
        String keyString = key.getKeyType().toString();
        switch(keyString){
            case "ArrowLeft": moveHero(hero.moveLeft()); break;
            case "ArrowRight": moveHero(hero.moveRight()); break;
            case "ArrowDown": moveHero(hero.moveDown()); break;
            case "ArrowUp": moveHero(hero.moveUp()); break;
        }
    }

    public void moveHero(Position position){
        if(canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position){
        if(position.getX() < 0) return false;
        if(position.getY() < 0) return false;
        if(position.getX() > width) return false;
        if(position.getY() > height) return false;

        for(Wall wall: walls){
            if(wall.getPosition().equals(position)) return false;
        }
        return true;
    }
    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for(int c = 0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height -1));
        }

        for(int r = 1; r < height -1; r++){
            walls.add(new Wall(0, r));
            walls.add(new Wall(width-1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return coins;
    }
    public void retrieveCoins(){
        for (Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())){
                coins.remove(coin);
                break;
            }
        }
    }
}

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private static Screen screen;
    private Arena arena;
    public Game() throws IOException {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary();

            arena = new Arena(40, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        while(true) {
            arena.retrieveCoins();
            arena.verifyMonsterCollisions();
            arena.moveMonsters();
            if(arena.finish){
                screen.stopScreen();
                System.out.println("Game Over");
                break;
            }
            else{
                draw();
                KeyStroke key = screen.readInput();
                if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.stopScreen();
                if(key.getKeyType()== KeyType.EOF) break;
                processKey(key);
                screen.setCharacter(arena.getHero().getPosition().getX(), arena.getHero().getPosition().getY(), TextCharacter.fromCharacter('X')[0]);
            }
        }
    }
    private void processKey(KeyStroke key){
        arena.processKey(key);
    }
}

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
    private int x = 10;
    private int y = 10;
    public Game() throws IOException {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.stopScreen();
            if(key.getKeyType()== KeyType.EOF) break;
            processKey(key);
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        }
    }
    private void processKey(KeyStroke key){
        //System.out.println(key);
        String keyString = key.getKeyType().toString();
        switch(keyString){
            case "ArrowLeft": x=x-1; break;
            case "ArrowRight": x++; break;
            case "ArrowDown": y=y+1; break;
            case "ArrowUp": y--; break;
        }
    }
}

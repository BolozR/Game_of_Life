package life;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLife extends JFrame {

    MyPanel panel = new MyPanel();;
    public JLabel generationLabel = new JLabel();
    public JLabel aliveLabel = new JLabel();
    public JToggleButton playButton = new JToggleButton("Start");
    public JButton resetButton = new JButton("Reset");
    public boolean play;
    public boolean reset;


    public GameOfLife(){
        super("Game Of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        generationLabel.setText("Generation #0");
        aliveLabel.setText("Alive: 0");
        generationLabel.setName("GenerationLabel");
        aliveLabel.setName("AliveLabel");
        playButton.setName("PlayToggleButton");
        resetButton.setName("ResetButton");
        add(generationLabel);
        add(aliveLabel);
        add(playButton);
        add(resetButton);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel);

        play = false;
        playButton.addActionListener(actionEvent -> {
            if(playButton.getText().equals("Start")) {
                playButton.setText("Stop");
                play = true;
            } else {
                playButton.setText("Start");
                play = false;
            }
        });

        reset = false;
        resetButton.addActionListener(actionEvent -> {
            reset = true;
        });
    }

    public void draw(Universe universe){
        generationLabel.setText("Generation #"+universe.getGeneration());
        aliveLabel.setText("Alive: "+universe.getAlive());
        //panel = new MyPanel();
        //add(GenerationLabel);
        //add(AliveLabel);
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //add(panel);
        panel.setUniverse(universe);
        panel.repaint(0,0,0,600,600);
        setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        //Scanner scanner = new Scanner(System.in);
        //int size = scanner.nextInt();
        int size = 25;
        int step = 0;
        Random random = new Random(12);
        Universe universe = new Universe(size);
        universe.createUniverse(random.nextInt());
        //Print.print(universe);
        GameOfLife gol = new GameOfLife();
        gol.draw(universe);
        while(step != 15) {
            if(gol.play) step =  universe.step();
            if(gol.reset){
                universe.createUniverse(random.nextInt());
                gol.reset = false;
            }
            Thread.sleep(1000);
            //Print.print(universe);
            gol.draw(universe);
        }
    }
}

class MyPanel extends JPanel {

    Universe universe;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int size = universe.getSize();
        boolean[][] state = universe.getState();
        g2.setColor(Color.BLACK);
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                g2.drawRect(j*15 + 15,i*15 + 5,15,15);
                if (state[i][j]) g2.fillRect(j*15 + 15,i*15 + 5,15,15);
            }
        }
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }
}

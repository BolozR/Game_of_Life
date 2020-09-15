package life.model;

import java.io.IOException;
import java.util.Random;

public class Universe {
    private boolean[][] state;
    private int generation;

    private int alive;
    private final int size;

    public Universe(int size){
        this.alive = 0;
        this.generation = 0;
        this.size = size;
        this.state = new boolean[size][size];
    }

    public void createUniverse(int seed){
        generation = 0;
        alive = 0;
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = random.nextBoolean();
                if(state[i][j]) alive++;
            }
        }
        generation++;
    }

    public int step(){
        int count;
        boolean[][] nextMatr = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count = around(i, j);
                nextMatr[i][j] = false;
                if(state[i][j]) {
                    if(count == 2 || count == 3) {
                        nextMatr[i][j] = true;
                    } else alive--;
                } else if(count == 3){
                    nextMatr[i][j] = true;
                    alive++;
                }

            }
        }
        state = nextMatr;
        generation++;
        return generation;
    }

    private int around(int i, int j){
        int count = 0;
        int upBorder = i-1<0?size-1:i-1;
        int leftBorder = j-1<0?size-1:j-1;
        int rightBorder = (j+1)%size;
        int downBorder = (i+1)%size;
        if(state[upBorder][leftBorder]) count++;
        if(state[upBorder][j]) count++;
        if(state[upBorder][rightBorder]) count++;
        if(state[i][leftBorder]) count++;
        if(state[i][rightBorder]) count++;
        if(state[downBorder][leftBorder]) count++;
        if(state[downBorder][j]) count++;
        if(state[downBorder][rightBorder]) count++;
        return count;
    }

    public boolean[][] getState(){
        return state;
    }

    public int getGeneration() {
        return generation;
    }

    public int getAlive() {
        return alive;
    }

    public int getSize() {
        return size;
    }
}

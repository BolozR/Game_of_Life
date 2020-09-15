package life.view;

import life.model.Universe;

public class Print {

    public static void print(Universe universe) {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            //Runtime.getRuntime().exec("cls");
        }
        catch (Exception e) {
            System.out.println("error!");
        }
        boolean[][] state = universe.getState();
        System.out.println("Generation #"+universe.getGeneration());
        System.out.println("Alive: "+universe.getAlive());
        for (int i = 0; i < universe.getSize(); i++) {
            for (int j = 0; j < universe.getSize(); j++) {
                System.out.print(state[i][j]?"O":" ");
            }
            System.out.println();
        }
    }
}

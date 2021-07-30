package minigame;

import java.util.Random;

public class Dice extends GameTool{

    // METHOD
    @Override
    public int generateResult() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(6) + 1;
    }

    @Override
    public String convertResultToString(int result) {
        String hsl = null;
        if (result == 1){
            hsl = "one";
        }
        else if (result == 2){
            hsl = "two";
        }
        else if(result == 3){
            hsl = "three";
        }
        else if(result == 4){
            hsl = "four";
        }
        else if(result == 5){
            hsl = "five";
        }
        else if (result == 6){
            hsl = "six";
        }
        else{
            System.out.println("Invalid!");
        }
        return hsl;
    }

    public int playDice(){
        return generateResult();
    }
}

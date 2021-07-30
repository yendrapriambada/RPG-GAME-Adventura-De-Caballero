package minigame;

import java.util.Random;

public class Coin extends GameTool{

    @Override
    public int generateResult() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(2);
    }

    @Override
    public String convertResultToString(int result) {
        String coin = null;
        if(result==0){
            coin = "head";
        }
        else  if(result==1){
             coin = "tail";
        }
        return coin;
    }

    public String  playCoin(){
        return convertResultToString(generateResult());
    }

}

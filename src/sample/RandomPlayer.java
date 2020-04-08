package sample;

import java.util.Random;

public class RandomPlayer extends Player {
    RandomPlayer(String name, int order){
        this.name=name;
        this.order = order;
        this.running = true;
    }

    public String makeMove(){
        Random random = new Random();
        int pozX,pozY;
        if (board.getAvailableCells().size()>0){
            int randomAvailableCell = random.nextInt(board.getAvailableCells().size());
            pozX = board.getAvailableCells().get(randomAvailableCell).getKey();
            pozY = board.getAvailableCells().get(randomAvailableCell).getValue();
            board.playerMove(pozX,pozY,order);
            return "Success";
        } return "Fail";
    }

}

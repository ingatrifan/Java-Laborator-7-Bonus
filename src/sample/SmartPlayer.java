package sample;

import java.util.Random;

public class SmartPlayer extends Player {
    int pozX,pozY;
    Boolean changeStrategy;
    SmartPlayer(String name, int order){
        this.name=name;
        this.order = order;
        this.running = true;
        this.pozX = 1;
        this.pozY = 1;
        changeStrategy = false;
    }
    private Boolean searchValidRow(int table [][],int row){
        for(int iterator = 1; iterator <= board.getSize();iterator++){
            if(table[row][iterator]!=order && table[row][iterator]!=0) return false;
        }
        return true;
    }
    public String makeMove() {
        if (board.getAvailableCells().size()>0){
            if (changeStrategy == false){
                while (true){
                    if (searchValidRow(board.getTable(),pozX)==true){
                        board.playerMove(pozX,pozY,order);
                        pozY++;
                        return "Success";
                    } else {
                        pozX++;
                        pozY=1;
                    }
                    if (pozX>board.getSize()){
                        changeStrategy = true;
                    }
                }
            } else randomMove();
            return "Success";
        }
        return "Fail";
    }

    private void randomMove(){
        Random random = new Random();
        int randomAvailableCell = random.nextInt(board.getAvailableCells().size());
        pozX = board.getAvailableCells().get(randomAvailableCell).getKey();
        pozY = board.getAvailableCells().get(randomAvailableCell).getValue();
        board.playerMove(pozX,pozY,order);
    }
}

package sample;

import java.util.Scanner;

public class ManualPlayer extends Player {
    ManualPlayer(String name, int order){
        this.name=name;
        this.order = order;
        this.running = true;
    }

    public String makeMove() {
        int pozX,pozY;
        Scanner in = new Scanner(System.in);
        board.printTable();
        System.out.println("It is your turn:");
        while (true) {
            if (board.getAvailableCells().size() > 0) {
                pozX = in.nextInt();
                pozY = in.nextInt();
                if (board.playerMove(pozX, pozY, order)!="Done"){
                    System.out.println("This cell is ocupaid, please select a valid one");
                } else return "Success";
            } else return "Fail";
        }
    }

}

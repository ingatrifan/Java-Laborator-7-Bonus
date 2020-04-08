package sample;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private int table[][];
    private  int size;
    private Tile[][] graphicTable;
    private List<Map.Entry<Integer,Integer>> availableCells= new ArrayList<>();
    Board(int size,Tile[][] graphicTable){
        this.size = size;
        this.graphicTable = graphicTable;
        table = new int[size+1][size+1];
        for (int iterator1 = 1 ;iterator1 <= size;iterator1++ ){
           for (int iterator2 = 1 ;iterator2 <= size;iterator2++ ){
               table[iterator1][iterator2]=0;
               Map.Entry<Integer,Integer> pair=new AbstractMap.SimpleEntry<>(iterator1,iterator2);
               availableCells.add(pair);
           }
        }
    }

    public String playerMove(int pozX, int pozY,int playersOrder){
        if (this.table[pozX][pozY]==0){
            this.table[pozX][pozY]=playersOrder;
            System.out.println(pozX+" "+pozY);
            if(playersOrder == 1)
                this.graphicTable[pozX][pozY].drawX();
            else
                this.graphicTable[pozX][pozY].drawO();
            Map.Entry<Integer,Integer> pair=new AbstractMap.SimpleEntry<>(pozX,pozY);
            availableCells.remove(pair);
            if(checkWinner()!=0) availableCells.clear();
            return "Done";
        } else return "This cell is occupied, see available cells!";
    }

    public List<Map.Entry<Integer, Integer>> getAvailableCells() {
        return availableCells;
    }

    public int getSize() {
        return size;
    }

    public int[][] getTable() {
        return table;
    }
    public void printTable(){
        for (int iterator1 = 1 ;iterator1 <= size;iterator1++ ) {
            for (int iterator2 = 1; iterator2 <= size; iterator2++) {
                if (table[iterator1][iterator2]==1)System.out.printf("X "); else
                if (table[iterator1][iterator2]==2)System.out.printf("0 "); else System.out.printf("N ");
            }
            System.out.printf("\n");
        }
    }
    public int checkWinner(){
        //check on rows
        int winner = 0;
        for (int iterator1 = 1 ;iterator1<=size && winner==0;iterator1++){
            for(int iterator2 =2 ;iterator2<=size;iterator2++)
                if (table[iterator1][iterator2] != table[iterator1][iterator2-1]){
                    winner = 0;
                    break;
                } else winner = table[iterator1][iterator2];
        }
        //check on columns
        if(winner == 0){
            for (int iterator2 = 1 ;iterator2<=size && winner==0;iterator2++){
                for(int iterator1 =2 ;iterator1<=size;iterator1++)
                    if (table[iterator1][iterator2] != table[iterator1-1][iterator2]){
                        winner = 0;
                        break;
                    } else winner = table[iterator1][iterator2];
            }
        }
        //check on main diagonal
        if (winner == 0){
            for (int iterator =2;iterator<=size;iterator++){
                if (table[iterator][iterator]!=table[iterator-1][iterator-1]){
                    winner = 0;
                    break;
                } else winner = table[iterator][iterator];
            }
        }
        if (winner == 0){
            for (int iterator =2;iterator<=size;iterator++){
                if (table[iterator][size-iterator+1]!=table[iterator-1][size-iterator+2]){
                    winner = 0;
                    break;
                } else winner = table[iterator][size-iterator+1];
            }

        }
        return winner;
    }
}

package sample;

public class Game {
    Board board;
    Player player1,player2;
    Thread thread1,thread2;
    Tile [][] tableX0;
    Timekeeper timekeeper;
    Game(Player player1, Player player2, int size,Tile[][] tableX0){
        board = new Board(size,tableX0);
        this.player1 = player1;
        this.player2=player2;
        this.tableX0 = tableX0;
        this.timekeeper = new Timekeeper(50,this);
        initPlayers();
        startGame();
    }
    private void initPlayers(){
        player1.setBoard(this.board);
        player1.setGame(this);
        player2.setBoard(this.board);
        player2.setGame(this);
    }
    private void startGame(){
        System.out.printf("Game starts\n");
        Thread timekeeper = new Thread(this.timekeeper);
        timekeeper.setDaemon(true);
        timekeeper.start();
        thread1=new Thread(player1);
        thread2=new Thread(player2);
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        thread2.start();
        synchronized (board){
            board.notify();
        }
    }
    public void endGame() throws InterruptedException {
        Thread.sleep(500);
        player1.stopThread();
        player2.stopThread();
        Thread.sleep(500);
        board.printTable();
        System.out.println("The winner is : "+board.checkWinner());
    }

    public Timekeeper getTimekeeper() {
        return timekeeper;
    }
}

package sample;

public abstract class Player implements Runnable {
    protected String name;
    protected Board board;
    protected Game game;
    protected int order;
    protected boolean running;
    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
    public void setGame(Game game){
        this.game = game;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract String makeMove();

    public void stopThread(){
        this.running =false;
    }
    @Override
    public void run() {
        System.out.printf("Player %d running\n",order);
        while (running) {
            synchronized (board) {
                try {
                    board.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String message = makeMove();
                if (message == "Fail") {
                    try {
                        game.endGame();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
            synchronized (board){
                board.notify();
            }
        }
    }

}

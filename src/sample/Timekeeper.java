package sample;

public class Timekeeper implements Runnable{
    int time;
    Game game;
    Timekeeper(int time,Game game){
        this.time = time;
        this.game = game;
    }
    @Override
    public void run() {
        while (time>0){
            try {
                Thread.sleep(1000);
                time--;
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        try {
            game.endGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

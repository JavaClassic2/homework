import java.util.Random;
import java.util.Scanner;

public class Player {
    private String sign;
    private String name;

    public Player(String sign, String name){
        this.sign = sign;
        this.name = name;
    }

    public void put(String[][] board, Scanner scanner) {
        System.out.print("Enter x, y : ");

        String line = scanner.next();
        int x = Integer.parseInt(line);

        line = scanner.next();
        int y = Integer.parseInt(line);

        if (board[x][y] != null) {
            System.out.println("already put anybody");
            put(board, scanner);
            return;
        }

        board[x][y] = sign;
    }

    public void randomPut(String[][] board, Scanner scanner) {
        Random random = new Random();
        int x = random.nextInt(board.length);
        int y = random.nextInt(board.length);

        if (board[x][y] != null) {
            randomPut(board, scanner);
            return;
        }

        board[x][y] = sign;
    }

    public String getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }
}

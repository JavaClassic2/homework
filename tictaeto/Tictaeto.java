import java.util.Scanner;

public class Tictaeto {
    private static int boardSize = 3;
    private static String[][] board = new String[boardSize][boardSize];
    // private static String[][] board = {
    //     {"O13", "O1", "O1"},
    //     {"O1", "O1", "O1"},
    //     {"O2", "O2", "O2"}
    // };

    public static void main(String[] args) {
        // test();
        Player player = new Player("O", "player");
        Player bot = new Player("X", "bot");
        printBoard();
        play(player, bot);
    }

    private static void test() {
        Player player = new Player("O2", "tester");
        Player[] players = {player};

        System.out.println(Tictaeto.vertical(players));
        System.out.println(Tictaeto.horizontal(players));
        System.out.println(Tictaeto.diagonal(players));
    }

    private static void printBoard() {
        System.out.print("j\\i");

        for (int i=0; i<boardSize; i++) {
            System.out.print("| " + i + " |");
        }
        System.out.println();

        for (int i=0; i<boardSize; i++) {
            System.out.print(" " + i + " ");
            for (int j=0; j<boardSize; j++) {
                String sign = board[i][j];
                
                if (sign == null) {
                    sign = " ";
                }
                
                System.out.printf("| %s |", sign);
            }

            System.out.println();
        }
    }

    private static void play(Player... players) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                for (Player player : players) {
                    System.out.println(player.getName() + "'s turn");
                    
                    if (player.getName().equals("bot")) {
                        player.randomPut(board, scanner);
                    } else {
                        player.put(board, scanner);
                    }
                    
                    printBoard();
    
                    if (isEnd(players)) {
                        scanner.close();
                        return;
                    }
                }
    
            }
        }
    }

    private static boolean isEnd(Player[] players) {
        boolean end = horizontal(players) || vertical(players) || diagonal(players);
        return end;
    }
    
    private static void printWinner(Player[] players, String sign) {
        for (Player player : players) {
            if (player.getSign().equals(sign)){
                System.out.println("Game Over!");
                System.out.println(player.getName() + " is win!");
                return;
            }
        }
    }
    
    private static boolean horizontal(Player[] players) {
        for (int i=0; i<boardSize; i++) {
            String sign = board[i][0];
            int count = 0;

            for (int j=0; j<boardSize; j++) {
                if (board[i][j] == sign && board[i][j] !=null) {
                    count++;
                }
            }

            if (count == boardSize) {
                printWinner(players, sign);
                return true;
            }
        }

        return false;
    }

    private static boolean vertical(Player[] players) {
        for (int j=0; j<boardSize; j++) {
            String sign = board[0][j];
            int count = 0;

            for (int i=0; i<boardSize; i++) {
                if (board[i][j] == sign && board[i][j] !=null) {
                    count++;
                }
            }

            if (count == boardSize) {
                printWinner(players, sign);
                return true;
            }
        }
        
        return false;
    }

    private static boolean diagonal(Player[] players) {
        int countp = 0;
        int countm = 0;
        
        String sign = board[0][0];
        for (int i=0, j=0; i<boardSize; i++, j++) {
            if (board[i][j] == sign && board[i][j] !=null) {
                countm++;
            }
        }

        if (countm == boardSize) {
            printWinner(players, sign);
            return true;
        }
        
        sign = board[0][boardSize-1];
        for (int i=0, j=boardSize-1; i<boardSize; i++, j--) {
            if (board[i][j] == sign && board[i][j] !=null) {
                countp++;
            }
        }

        if (countp == boardSize) {
            printWinner(players, sign);
            return true;
        }

        return false;
    }
}
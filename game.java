import java.util.*;

public class MemoryGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int n = 4; // number of rows and columns
        int numPairs = n * n / 2; // number of pairs of symbols
        char[][] board = new char[n][n]; // the game board
        char[] symbols = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'}; // array of symbols
        List<Character> list = new ArrayList<Character>(); // list of symbols
        for (int i = 0; i < numPairs; i++) {
            list.add(symbols[i]);
            list.add(symbols[i]);
        }
        Collections.shuffle(list); // shuffle the list of symbols
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = list.get(index); // assign a symbol to each cell
                index++;
            }
        }
        boolean[][] flipped = new boolean[n][n]; // keep track of flipped cells
        int numFlipped = 0; // number of flipped cells
        while (numFlipped < n * n) {
            // print the board
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (flipped[i][j]) {
                        System.out.print(board[i][j] + " ");
                    } else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
            // get user input
            System.out.print("Enter row and column of cell to flip: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (flipped[row][col]) {
                System.out.println("Cell already flipped. Try again.");
            } else {
                flipped[row][col] = true;
                numFlipped++;
                if (numFlipped % 2 == 0) {
                    // check if the two flipped cells match
                    int r1 = -1, c1 = -1, r2 = -1, c2 = -1;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (flipped[i][j]) {
                                if (r1 == -1) {
                                    r1 = i;
                                    c1 = j;
                                } else {
                                    r2 = i;
                                    c2 = j;
                                }
                            }
                        }
                    }
                    if (board[r1][c1] == board[r2][c2]) {
                        System.out.println("Match!");
                    } else {
                        System.out.println("No match.");
                        flipped[r1][c1] = false;
                        flipped[r2][c2] = false;
                        numFlipped -= 2;
                    }
                }
            }
        }
        System.out.println("You win!");
    }
}
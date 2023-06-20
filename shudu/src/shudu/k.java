package shudu;

import java.util.Random;
import javax.swing.JOptionPane;

public class k {
    private int[][] board = new int[4][4];
    private long startTime;

    public void generateBoard() {
        Random rand = new Random();
        int num;
        boolean valid;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                valid = false;
                while (!valid) {
                    num = rand.nextInt(4) + 1;
                    if (isValid(row, col, num)) {
                        board[row][col] = num;
                        valid = true;
                    }
                }
            }
        }
    }

    public boolean isValid(int row, int col, int num) {
        // check row
        for (int i = 0; i < 4; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // check column
        for (int i = 0; i < 4; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // check box
        int boxRow = row - row % 2;
        int boxCol = col - col % 2;
        for (int i = boxRow; i < boxRow + 2; i++) {
            for (int j = boxCol; j < boxCol + 2; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        String boardString = "";
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                boardString += board[row][col] + " ";
            }
            boardString += "\n";
        }
        JOptionPane.showMessageDialog(null, boardString);
    }

    public void startGame() {
        generateBoard();
        displayBoard();
        startTime = System.currentTimeMillis();
        boolean solved = false;
        while (!solved) {
            String input = JOptionPane.showInputDialog("Enter row, column, and number (separated by spaces) or type 'exit' to quit:");
            if (input.equals("exit")) {
                return;
            }
            String[] inputArr = input.split(" ");
            int row = Integer.parseInt(inputArr[0]) - 1;
            int col = Integer.parseInt(inputArr[1]) - 1;
            int num = Integer.parseInt(inputArr[2]);
            if (isValid(row, col, num)) {
                board[row][col] = num;
                displayBoard();
                solved = isSolved();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move!");
            }
        }
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        JOptionPane.showMessageDialog(null, "Congratulations, you solved the puzzle in " + timeElapsed/1000 + " seconds!");
    }

    public boolean isSolved() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

   public static void main(String[] args) {
        k game = new k();
        game.startGame();
    }
}

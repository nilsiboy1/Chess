package de.nilsiboy.chess.main;

import de.nilsiboy.chess.gui.GUI;

public class Chess {

    public static char[][] board = new char[8][8];

    public static void main(String[] args) {

        GUI gui = new GUI();

        Chess.setBoard( "00R/10N/20B/30K/40Q/50B/60N/70R/" +
                        "01P/11P/21P/31P/41P/51P/61P/71P/" +
                        "06p/16p/26p/36p/46p/56p/66p/76p/" +
                        "07r/17n/27b/37q/47k/57b/67n/77r");

        gui.label.repaint();
    }

    public static void setBoard(String board){

        String[] positions = board.split("/");
        for(String position : positions){
            int x = Integer.parseInt("" + position.charAt(0));
            int y = Integer.parseInt("" + position.charAt(1));
            System.out.println(x + " " + y + " " + position.charAt(2));
            Chess.board[x][y] = position.charAt(2);
        }
    }
}

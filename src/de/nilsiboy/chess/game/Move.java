package de.nilsiboy.chess.game;

import de.nilsiboy.chess.main.Chess;

import java.util.ArrayList;
import java.util.List;

public class Move {

    public int x, y;
    public Action action;

    public static Move[] knightMoves = new Move[]{new Move(1, 2), new Move(-1, 2), new Move(1,-2), new Move(-1, -2),
                                                  new Move(2, 1), new Move(2, -1), new Move(-2,-1), new Move(-2, 1)},

                         kingMoves = new Move[]{new Move(1, 1), new Move(1, 0), new Move(1,-1), new Move(0, -1),
                                     new Move(0, 1), new Move(-1, -1), new Move(-1,0), new Move(-1, 1)};

    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Move(int x, int y, Action action){
        this.x = x;
        this.y = y;
        this.action = action;
    }

    public static List<Move> getPossibleMoves(int x, int y){

        List<Move> moves = new ArrayList<>();
        if(Chess.board[x][y] == 0) return moves;

        switch (Chess.board[x][y]){

            case 'p':
                if(y == 0) return moves;
                if(Chess.board[x][y - 1] == 0){
                    if(y == 1){
                        moves.add(new Move(x,0, Action.PAWN_TRANSFORMATION));
                    }
                    else{
                        moves.add(new Move(x,y - 1, Action.MOVE));
                        if(Chess.board[x][y - 2] == 0 && y == 6){
                            moves.add(new Move(x,y - 2, Action.PAWN_OPENING));
                        }
                    }
                }
                if(x < 7 && Character.isUpperCase(Chess.board[x + 1][y - 1])){
                    if(y == 1){
                        moves.add(new Move(x + 1,0, Action.PAWN_TRANSFORMATION));
                    }
                    else moves.add(new Move(x + 1,y - 1, Action.CAPTURE));
                }
                if(x > 0 && Character.isUpperCase(Chess.board[x - 1][y - 1])){
                    if(y == 1){
                        moves.add(new Move(x - 1,0, Action.PAWN_TRANSFORMATION));
                    }
                    else moves.add(new Move(x - 1,y - 1, Action.CAPTURE));
                }

                break;
            case 'P':

                if(y == 7) return moves;
                if(Chess.board[x][y + 1] == 0){
                    if(y == 6){
                        moves.add(new Move(x,7, Action.PAWN_TRANSFORMATION));
                    }
                    else{
                        moves.add(new Move(x,y + 1, Action.MOVE));
                        if(Chess.board[x][y + 2] == 0 && y == 1){
                            moves.add(new Move(x,y + 2, Action.PAWN_OPENING));
                        }
                    }
                }
                if(x < 7 && Character.isLowerCase(Chess.board[x + 1][y + 1])){
                    if(y == 6){
                        moves.add(new Move(x + 1,7, Action.PAWN_TRANSFORMATION));
                    }
                    else moves.add(new Move(x + 1,y + 1, Action.CAPTURE));
                }
                if(x > 0 && Character.isLowerCase(Chess.board[x - 1][y + 1])){
                    if(y == 6){
                        moves.add(new Move(x - 1,7, Action.PAWN_TRANSFORMATION));
                    }
                    else moves.add(new Move(x - 1,y + 1, Action.CAPTURE));
                }

                break;
            case 'b':
                for(int moveX = -1; moveX <= 1; moveX+=2){
                    for(int moveY = -1; moveY <= 1; moveY+=2){
                        for(int i = 1; i < 8; i++){
                            if((x + (i*moveX)) >= 8 || (x + (i*moveX)) < 0 || (y + (i*moveY)) >= 8 || (y + (i*moveY)) < 0) break;
                            if(Chess.board[x + (i*moveX)][y + (i*moveY)] != 0){
                                if(Character.isUpperCase(Chess.board[x + (i*moveX)][y + (i*moveY)])){
                                    moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.CAPTURE));
                                }
                                break;
                            }
                            moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.MOVE));
                        }
                    }
                }
                break;
            case 'B':
                for(int moveX = -1; moveX <= 1; moveX+=2){
                    for(int moveY = -1; moveY <= 1; moveY+=2){
                        for(int i = 1; i < 8; i++){
                            if((x + (i*moveX)) >= 8 || (x + (i*moveX)) < 0 || (y + (i*moveY)) >= 8 || (y + (i*moveY)) < 0) break;
                            if(Chess.board[x + (i*moveX)][y + (i*moveY)] != 0){
                                if(Character.isLowerCase(Chess.board[x + (i*moveX)][y + (i*moveY)])){
                                    moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.CAPTURE));
                                }
                                break;
                            }
                            moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.MOVE));
                        }
                    }
                }
                break;
            case 'n':

                for(Move move : knightMoves){
                    if(x + move.x < 8 && x + move.x >= 0 && y + move.y < 8 && y + move.y >= 0) {
                        if(Chess.board[x + move.x][y + move.y] == 0) {
                            moves.add(new Move(x + move.x, y + move.y, Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x + move.x][y + move.y])){
                                moves.add(new Move(x + move.x, y + move.y, Action.CAPTURE));
                            }
                        }
                    }
                }

                break;
            case 'N':
                break;
            case 'r':
                for(int moveX = -1; moveX <= 1; moveX+=2){
                    for(int i = 1; i < 8; i++){
                        if(x + (i * moveX) > 7 || x + (i * moveX) < 0) break;
                        if(Chess.board[x + (i * moveX)][y] == 0) {
                            moves.add(new Move(x + (i * moveX), y, Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x + (i * moveX)][y])){
                                moves.add(new Move(x + (i * moveX), y, Action.CAPTURE));
                            }
                            break;
                        }
                    }
                }
                for(int moveY = -1; moveY <= 1; moveY+=2){
                    for(int i = 1; i < 8; i++){
                        if(y + (i * moveY) > 7 || y + (i * moveY) < 0) break;
                        if(Chess.board[x][y + (i * moveY)] == 0) {
                            moves.add(new Move(x, y + (i * moveY), Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x][y + (i * moveY)])){
                                moves.add(new Move(x, y + (i * moveY), Action.CAPTURE));
                            }
                            break;
                        }
                    }
                }

                break;
            case 'R':
                break;
            case 'q':

                for(int moveX = -1; moveX <= 1; moveX+=2){
                    for(int i = 1; i < 8; i++){
                        if(x + (i * moveX) > 7 || x + (i * moveX) < 0) break;
                        if(Chess.board[x + (i * moveX)][y] == 0) {
                            moves.add(new Move(x + (i * moveX), y, Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x + (i * moveX)][y])){
                                moves.add(new Move(x + (i * moveX), y, Action.CAPTURE));
                            }
                            break;
                        }
                    }
                }
                for(int moveY = -1; moveY <= 1; moveY+=2){
                    for(int i = 1; i < 8; i++){
                        if(y + (i * moveY) > 7 || y + (i * moveY) < 0) break;
                        if(Chess.board[x][y + (i * moveY)] == 0) {
                            moves.add(new Move(x, y + (i * moveY), Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x][y + (i * moveY)])){
                                moves.add(new Move(x, y + (i * moveY), Action.CAPTURE));
                            }
                            break;
                        }
                    }
                }
                for(int moveX = -1; moveX <= 1; moveX+=2){
                    for(int moveY = -1; moveY <= 1; moveY+=2){
                        for(int i = 1; i < 8; i++){
                            if((x + (i*moveX)) >= 8 || (x + (i*moveX)) < 0 || (y + (i*moveY)) >= 8 || (y + (i*moveY)) < 0) break;
                            if(Chess.board[x + (i*moveX)][y + (i*moveY)] != 0){
                                if(Character.isUpperCase(Chess.board[x + (i*moveX)][y + (i*moveY)])){
                                    moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.CAPTURE));
                                }
                                break;
                            }
                            moves.add(new Move(x + (i*moveX),y + (i*moveY),Action.MOVE));
                        }
                    }
                }

                break;
            case 'Q':
                break;
            case 'k':

                for(Move move : kingMoves){
                    if(x + move.x < 8 && x + move.x >= 0 && y + move.y < 8 && y + move.y >= 0) {
                        if(Chess.board[x + move.x][y + move.y] == 0) {
                            moves.add(new Move(x + move.x, y + move.y, Action.MOVE));
                        }
                        else{
                            if(Character.isUpperCase(Chess.board[x + move.x][y + move.y])){
                                moves.add(new Move(x + move.x, y + move.y, Action.CAPTURE));
                            }
                        }
                    }
                }

                break;
            case 'K':
                break;
            default:
                break;
        }

        return moves;
    }

    private static List<Move> pawnMoves(int x, int y, boolean uppercase){
        List<Move> moves = new ArrayList<>();
        if((y == 0 && !uppercase) || (y == 7 && uppercase)) return moves;
        int direction = uppercase ? -1 : 1;
        if(Chess.board[x][y + direction] == 0){
            if(y == 1){
                moves.add(new Move(x,0, Action.PAWN_TRANSFORMATION));
            }
            else{
                moves.add(new Move(x,y - 1, Action.MOVE));
                if(Chess.board[x][y - 2] == 0 && y == 6){
                    moves.add(new Move(x,y - 2, Action.PAWN_OPENING));
                }
            }
        }
        if(x < 7 && Character.isUpperCase(Chess.board[x + 1][y - 1])){
            if(y == 1){
                moves.add(new Move(x + 1,0, Action.PAWN_TRANSFORMATION));
            }
            else moves.add(new Move(x + 1,y - 1, Action.CAPTURE));
        }
        if(x > 0 && Character.isUpperCase(Chess.board[x - 1][y - 1])){
            if(y == 1){
                moves.add(new Move(x - 1,0, Action.PAWN_TRANSFORMATION));
            }
            else moves.add(new Move(x - 1,y - 1, Action.CAPTURE));
        }
        return moves;
    }

    private static List<Move> bishopMoves(int x, int y, boolean uppercase){
        return  null;
    }

    private static List<Move> knightMoves(int x, int y, boolean uppercase){
        return  null;
    }

    private static List<Move> rockMoves(int x, int y, boolean uppercase){
        return  null;
    }

    private static List<Move> kingMoves(int x, int y, boolean uppercase){
        return  null;
    }

    public static boolean isPossibleMove(int originX, int originY, int moveX, int moveY){

        for(Move move : Move.getPossibleMoves(originX,originY)){
            if(move.x == moveX && move.y == moveY) return true;
        }

        return false;
    }

    public enum Action {

        CAPTURE("capture"),
        MOVE("move"),
        CHECK_WHITE("check"),
        CHECKMATE_WHITE("checkmate"),
        CHECK_BLACK("check"),
        CHECKMATE_BLACK("checkmate"),
        EN_PASSANT("capture"),
        PAWN_OPENING("move"),
        PAWN_TRANSFORMATION("move");

        public String texture;

        Action(String texture) {
            this.texture = texture;
        }
    }

}

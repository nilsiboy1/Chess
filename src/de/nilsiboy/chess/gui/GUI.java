package de.nilsiboy.chess.gui;

import de.nilsiboy.chess.game.Move;
import de.nilsiboy.chess.main.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

    public JFrame frame;
    public JLabel label;

    int width = 500;
    int height = 500;

    int fieldWidth = width/8;
    int fieldHeight = height/8;

    boolean selected = false;
    int selectedX, selectedY;

    public GUI(){
        frame = new JFrame("Chess");
        frame.setUndecorated(true);
        frame.setFocusable(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        label = new JLabel(){

            @Override
            public void paint(Graphics g) {
                g.drawImage(new ImageIcon("textures/board.png").getImage(), 0, 0, width, height, this);

                for(int y = 0; y < 8; y ++){
                    for(int x = 0; x < 8; x ++){
                        String color;
                        if(Character.isUpperCase(Chess.board[x][y])) color = "b";
                        else color = "w";
                        g.drawImage(new ImageIcon("textures/" + Chess.board[x][y] + color + ".png").getImage(), x * fieldWidth, y * fieldHeight, fieldWidth, fieldHeight, this);
                    }
                }

                if(!selected) return;
                for(Move move : Move.getPossibleMoves(selectedX, selectedY)){
                    g.drawImage(new ImageIcon("textures/" + move.action.texture + ".png").getImage(), move.x * fieldWidth, move.y * fieldHeight, fieldWidth, fieldHeight, this);
                }
            }
        };

        frame.add(label);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX()/fieldHeight;
                int y = e.getY()/fieldWidth;

                if(selected){

                    if(Move.isPossibleMove(selectedX, selectedY, x, y)){
                        char piece = Chess.board[selectedX][selectedY];
                        Chess.board[selectedX][selectedY] = 0;
                        Chess.board[x][y] = piece;
                    }

                    selected = false;
                }
                else if(Chess.board[x][y] != 0) {
                    selected = true;
                    selectedX = x;
                    selectedY = y;
                }
                label.repaint();
            }
        });

        frame.setVisible(true);
    }



}

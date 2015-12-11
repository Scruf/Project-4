import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Created by Egor Koziski on 12/10/2015.
 */
public class SolicitorChess extends JFrame implements Observer {
    public SolitorChessModel model;
    public JTextArea moveCounter;
    public JTextArea message;
    public ButtonPiece[][] buttons;
    public JButton reset;
    public JButton bestMove;
    public JButton rulse;

    SolicitorChess(SolitorChessModel model) {
        this.model = model;
        model.addObserver(this);
        init();
        model.reset();

    }
    public void init() {

        moveCounter = new JTextArea("Move  #" + model.getCounter());
        message = new JTextArea("");
        buttons = new ButtonPiece[model.getY()][model.getX()];
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(moveCounter);
        topPanel.add(message);
        topPanel.setVisible(true);
        reset = new JButton("Reset");
        reset.addActionListener(new ButtonListener(reset, this));
        bestMove = new JButton("Hint");
        bestMove.addActionListener(new ButtonListener(bestMove, this));
        rulse = new JButton("How to");
        rulse.addActionListener(new ButtonListener(rulse, this));
        for (int i = 0; i < model.getY(); i++) {
            for (int j = 0; j < model.getX(); j++) {
                ButtonPiece button = new ButtonPiece(j, i);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setVisible(true);
                button.addActionListener(new ButtonListener(button, this));
                buttons[i][j] = button;
            }
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(model.getY(), model.getX(), 15, 15));
        System.out.println(model.getX() + " " + model.getY());
        for (int i = 0; i < model.getY(); i++) {
            for (int j = 0; j < model.getX(); j++) {
                panel.add(buttons[i][j]);
            }

        }
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        bottom.add(reset);
        bottom.add(bestMove);
        bottom.add(rulse);
        bottom.setVisible(true);
        JFrame mainWindow = new JFrame("Solitare Chess Egor Kozitski ek5442");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.add(topPanel, BorderLayout.NORTH);
        mainWindow.add(panel, BorderLayout.CENTER);
        mainWindow.add(bottom, BorderLayout.SOUTH);
        mainWindow.setSize(model.getX() * 150, model.getY() * 150);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable obs, Object o) {
        HashMap < Coordinates, Piece > board = model.getBoard();
        for (ButtonPiece[] i: this.buttons) {
            for (ButtonPiece btn: i)
                btn.setText("");
        }
        for (Coordinates cord: board.keySet()) {
            buttons[cord.getyCoord()][cord.getxCoord()].setText(board.get(cord).getName());
        }
        moveCounter.setText("Moves #" + model.getCounter());
        if (board.size() == 1) message.setText("Winner");
        if (model.resetSize() == 0) colorFiller();

    }
    public void colorFiller() {
        int i = 0;
        int j = 0;
        for (ButtonPiece[] row: this.buttons) {
            for (ButtonPiece button: row) {
                if ((i + j) % 2 == 0) {
                    //Filling board with white squares
                    button.setBackground(Color.WHITE);

                    j += 1;
                } else if ((i + j) % 2 == 1) {
                    //filling with blue
                    button.setBackground(Color.BLUE);

                    j += 1;
                }
            }
            i += 1;
            j = 0;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            JOptionPane.showMessageDialog(null, "Invalid number of arguments");
            System.exit(0);
        }
        SolitorChessModel sol = new SolitorChessModel(new File(args[0]));
        new SolicitorChess(sol);
    }

    public class ButtonListener implements ActionListener {
        public JButton button;
        public SolicitorChess chess;

        public ButtonListener(JButton btn, SolicitorChess sol) {
            this.button = btn;
            this.chess = sol;
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getActionCommand().equals("Reset")) {
                model.reset();
                message.setText("");
            } else if (button instanceof ButtonPiece) {
                if (model.addPieces(((ButtonPiece) button).getCord())) {
                    chess.colorFiller();
                    button.setBackground(Color.DARK_GRAY);
                }
            } else if (button.getText().equals("Hint")) {
                if (message.getText().equals("Winner")) {
                    message.setText(" ");
                    System.exit(0);
                } else if (!model.nextBestMove(model)) message.setText("Invalid move");


            } else if (ev.getActionCommand().equals("How to")) {
                JOptionPane.showMessageDialog(chess,
                        "Based on normal chess rules, each chess piece has their own movements." + "To move a chess piece, click on the grid box of the chess piece, and \n" + "click to another destination. To capture, select the chess piece you want", "How to play", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}
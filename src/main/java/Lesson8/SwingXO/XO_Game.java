package Lesson8.SwingXO;

import javax.swing.*;
import java.awt.*;


public class XO_Game {
    protected static final int SIZE = 3;
    protected static final char DOT_X = 'X';
    protected static final char DOT_O = 'O';
    protected static final char DOT_EMPTY = '*';
    protected static final char[][] GAME_FIELD = new char[SIZE][SIZE];
    protected static JButton[][] gameButtons = new JButton[SIZE][SIZE];
    public static JTextArea textArea = new JTextArea(4, 1);
    public static Font font = new Font("Arial", Font.BOLD, 50);
    public static final int[] goodMove ={00, 02, 11, 20, 22};
    public static JFrame gameWindow = new JFrame("Крестики-нолики");
    public static JScrollPane scrollPane;
    public static JButton resetButton = new JButton("Reset");
    public static JButton resultsButton = new JButton("Results");


    public XO_Game(){
        gameWindow.setBounds(300, 300, 300, 400);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initializeMap(gameWindow);
        gameWindow.setVisible(true);
    }

    public static void clearMap(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                GAME_FIELD[i][j] = DOT_EMPTY;
                gameButtons[i][j].setText(String.valueOf(DOT_EMPTY));
                gameButtons[i][j].setEnabled(true);
                textArea.setEditable(false);
            }
        }
    }

    public static void initializeMap(JFrame frame) {
        JPanel gameFieldPanel = new JPanel(new GridLayout(SIZE, SIZE));
        resetButton.addActionListener(new ButtonActionHandler(resetButton));
        resultsButton.addActionListener(new ButtonActionHandler(resultsButton));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                GAME_FIELD[i][j] = DOT_EMPTY;
                gameButtons[i][j] = new JButton("*");
                gameButtons[i][j].setFont(font);
                gameButtons[i][j].setEnabled(true);
                gameButtons[i][j].addActionListener(new ButtonActionHandler(gameButtons[i][j]));
                gameFieldPanel.add(gameButtons[i][j]);
                textArea.setEditable(false);
            }
        }
        JPanel textAreaPanel = new JPanel(new GridLayout());

        textAreaPanel.add(textArea, BorderLayout.NORTH);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaPanel.add(scrollPane);

        JPanel resetResultsPanel = new JPanel(new GridLayout());
        resetResultsPanel.add(resetButton);
        resetResultsPanel.add(resultsButton);

        frame.add(textAreaPanel, BorderLayout.NORTH);
        frame.add(resetResultsPanel, BorderLayout.SOUTH);
        frame.add(gameFieldPanel, BorderLayout.CENTER);


    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(XO_Game::new); //пока не понимаю особо что это значит, но выглядит удобнее чем вариант ниже
    }

    //Прочитал, что такой вариант предпочтительнее использовать для многопоточности, чем через конструктор.
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new XO_Game();
//            }
//        });
//    }
}

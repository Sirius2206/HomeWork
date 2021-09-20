package Lesson8.SwingXO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonActionHandler implements ActionListener {
    private final JButton button;

    public ButtonActionHandler(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < XO_Game.SIZE; i++) {
            for (int j = 0; j < XO_Game.SIZE; j++) {
                if (XO_Game.gameButtons[i][j].equals(e.getSource())) {
                    XO_Game.gameButtons[i][j].setText(String.valueOf(XO_Game.DOT_X));
                    XO_Game.gameButtons[i][j].setEnabled(false);
                    XO_Game.GAME_FIELD[i][j] = XO_Game.DOT_X;
                    XO_Game.textArea.append("Игрок походил на " + (j + 1) + (i + 1) + "\n");
                    GameLogic.playerAndAiTurn();
                }
            }
        }
        if (button.equals(XO_Game.resetButton)){
            XO_Game.clearMap();
            XO_Game.textArea.setText(null);
            XO_Game.textArea.append("Сброс. Начало новой игры.\n");
        }
        if (button.equals(XO_Game.resultsButton)){
            XO_Game.textArea.append("Счет: \n");
            XO_Game.textArea.append("Игрок: " + GameLogic.score[0] + " Компьютер: " + GameLogic.score[1] + " Ничья: " + GameLogic.score[2] + "\n");
        }
    }


}

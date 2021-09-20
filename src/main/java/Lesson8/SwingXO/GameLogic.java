package Lesson8.SwingXO;

import java.util.Random;

public class GameLogic {
    protected static final int[] score = {0, 0, 0}; //{побед игрока, побед компьютера, ничьих}
    private static boolean isMapFull(){
        for (int i = 0; i < XO_Game.SIZE; i++) {
            for (int j = 0; j < XO_Game.SIZE; j++) {
                if (XO_Game.GAME_FIELD[i][j] == XO_Game.DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isWin (char c){
        int checkWin = 0;
        for (int i = 0; i < XO_Game.SIZE; i++){
            for(int j = 0; j < XO_Game.SIZE; j++){
                if (XO_Game.GAME_FIELD[i][j] == c) checkWin++;
            }
            if (checkWin == 3) return true;
            checkWin = 0;
        }
        for (int i = 0; i < XO_Game.SIZE; i++){
            for(int j = 0; j < XO_Game.SIZE; j++){
                if (XO_Game.GAME_FIELD[j][i] == c) checkWin++;
            }
            if (checkWin == 3) return true;
            checkWin = 0;
        }
        for (int i = 0; i < XO_Game.SIZE; i++){
            if (XO_Game.GAME_FIELD[i][i] == c) checkWin++;
        }
        if (checkWin == 3) return true;
        checkWin = 0;

        for (int i = 0; i < XO_Game.SIZE; i++){
            if(XO_Game.GAME_FIELD[i][XO_Game.SIZE - 1 - i] == c) checkWin++;
        }
        return checkWin == 3;
    }

    public static boolean isPlayerWin() {
        if (isWin(XO_Game.DOT_X)) {
            for (int i = 0; i < XO_Game.SIZE; i++) {
                for (int j = 0; j < XO_Game.SIZE; j++) {
                    XO_Game.gameButtons[i][j].setEnabled(false);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAiWin(){
        if (isWin(XO_Game.DOT_O)) {
            for (int i = 0; i < XO_Game.SIZE; i++) {
                for (int j = 0; j < XO_Game.SIZE; j++) {
                    XO_Game.gameButtons[i][j].setEnabled(false);
                }
            }
            return true;
        }
        return false;
    }

    public static int[] checkGoodMove(char c) {
        int[] ret = new int[2];
        int multiplier = 10;
        int worth = 0; // переменная для определения "ценности" хода
        if (c == 'X') multiplier = 1;
        int x = 0, y = 0;
        for (int i = 0; i < XO_Game.SIZE; i++) {
            for (int j = 0; j < XO_Game.SIZE; j++) {
                if (XO_Game.GAME_FIELD[i][j] == c) worth += multiplier;
                if (XO_Game.GAME_FIELD[i][j] != c && XO_Game.GAME_FIELD[i][j] != XO_Game.DOT_EMPTY) worth -= 50;
                if (XO_Game.GAME_FIELD[i][j] == XO_Game.DOT_EMPTY) {
                    worth += 3;
                    x = i;
                    y = j;
                }
                //worth принимает значение 5, когда в строке 2 'X' и 1 '*'; и 23 когда в строке 2 'O' и 1 '*'
                if ((worth == 5) || (worth == 23)) {
                    ret[0] = ((x * 10) + y);
                    ret[1] = worth;
                    return ret;
                }

            }
            worth = 0;
            x = 0;
            y = 0;
        }
        worth = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < XO_Game.SIZE; i++) {
            for (int j = 0; j < XO_Game.SIZE; j++) {
                if (XO_Game.GAME_FIELD[j][i] == c) worth += multiplier;
                if (XO_Game.GAME_FIELD[j][i] != c && XO_Game.GAME_FIELD[j][i] != XO_Game.DOT_EMPTY) worth -= 50;
                if (XO_Game.GAME_FIELD[j][i] == XO_Game.DOT_EMPTY) {
                    worth += 3;
                    x = j;
                    y = i;
                }
                if ((worth == 5) || (worth == 23)) {
                    ret[0] = ((x * 10) + y);
                    ret[1] = worth;
                    return ret;
                }
            }
            worth = 0;
            x = 0;
            y = 0;
        }
        worth = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < XO_Game.SIZE; i++) {
            if (XO_Game.GAME_FIELD[i][i] == c) worth += multiplier;
            if (XO_Game.GAME_FIELD[i][i] != c && XO_Game.GAME_FIELD[i][i] != XO_Game.DOT_EMPTY) worth -= 50;
            if (XO_Game.GAME_FIELD[i][i] == XO_Game.DOT_EMPTY) {
                worth += 3;
                x = i;
                y = i;
            }
            if ((worth == 5) || (worth == 23)) {
                ret[0] = ((x * 10) + y);
                ret[1] = worth;
                return ret;
            }
        }
        worth = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < XO_Game.SIZE; i++) {
            if (XO_Game.GAME_FIELD[i][XO_Game.SIZE - 1 - i] == c) worth += multiplier;
            if (XO_Game.GAME_FIELD[i][XO_Game.SIZE - 1 - i] != c && XO_Game.GAME_FIELD[i][XO_Game.SIZE - 1 - i] != XO_Game.DOT_EMPTY) worth -= 50;
            if (XO_Game.GAME_FIELD[i][XO_Game.SIZE - 1 - i] == XO_Game.DOT_EMPTY) {
                worth += 3;
                x = i;
                y = XO_Game.SIZE - 1 - i;
            }
            if ((worth == 5) || (worth == 23)) {
                ret[0] = ((x * 10) + y);
                ret[1] = worth;
                return ret;
            }
        }
        return ret;
    }

    public static void aiTurn() {
        int[] moveX = checkGoodMove(XO_Game.DOT_X);
        int[] moveO = checkGoodMove(XO_Game.DOT_O);
        if (moveO[1] > moveX[1]){
            XO_Game.GAME_FIELD[moveO[0]/10][moveO[0]%10] = XO_Game.DOT_O;
            XO_Game.gameButtons[moveO[0]/10][moveO[0]%10].setEnabled(false);
            XO_Game.gameButtons[moveO[0]/10][moveO[0]%10].setText(String.valueOf(XO_Game.DOT_O));
            XO_Game.textArea.append("Компьютер походил на " + (moveO[0]%10  + 1) + (moveO[0]/10 + 1) + "\n");
            return;
        }
        if (moveO[1] < moveX[1]){
            XO_Game.GAME_FIELD[moveX[0]/10][moveX[0]%10] = XO_Game.DOT_O;
            XO_Game.gameButtons[moveX[0]/10][moveX[0]%10].setEnabled(false);
            XO_Game.gameButtons[moveX[0]/10][moveX[0]%10].setText(String.valueOf(XO_Game.DOT_O));
            XO_Game.textArea.append("Компьютер походил на " + (moveX[0]%10 + 1) + (moveX[0]/10 + 1) + "\n");
            return;
        }
        //Если нет предвыигрышного хода или возможности помешать игроку, ходим на одну из перспективных точек
        for (int i : XO_Game.goodMove) {
            if (XO_Game.GAME_FIELD[i / 10][i % 10] == XO_Game.DOT_EMPTY) {
                XO_Game.GAME_FIELD[i / 10][i % 10] = XO_Game.DOT_O;
                XO_Game.gameButtons[i / 10][i % 10].setText(String.valueOf(XO_Game.DOT_O));
                XO_Game.gameButtons[i / 10][i % 10].setEnabled(false);
                XO_Game.textArea.append("Компьютер походил на " + (i % 10 + 1) + ((i / 10) + 1)+ "\n");
                return;
            }
        }

        // Если ценного хода на данный момент нет, то ставим случайную точку,
        // до сюда скорее всего программа не дойдет, но на всякий случай оставим.
        int x;
        int y;
        Random random = new Random();
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isAvailable(x, y));
        XO_Game.GAME_FIELD[x][y] = XO_Game.DOT_O;
        XO_Game.textArea.append("Компьютер походил на " + (y + 1) + (x + 1) + "\n");
        XO_Game.gameButtons[x][y].setText(String.valueOf(XO_Game.DOT_O));
        XO_Game.gameButtons[x][y].setEnabled(false);
    }

    public static boolean isAvailable(int x, int y) {
        if ((x<0) || (y < 0) || (x > XO_Game.SIZE) || (y > XO_Game.SIZE)) return false;
        return XO_Game.GAME_FIELD[x][y] == XO_Game.DOT_EMPTY;
    }

    protected static void playerAndAiTurn() {
        if (isMapFull()) {
            XO_Game.textArea.append("Ничья.\n");
            score[2]++;
            return;
        }
        if (isPlayerWin()){
            XO_Game.textArea.append("Игрок победил!\n");
            score[0]++;
            return;
        }
        aiTurn();
        if (isAiWin()) {
            XO_Game.textArea.append("Компьютер победил!\n");
            score[1]++;
        }



    }
}

package menu;

import core.ScreenSize;
import display.GameDisplay;
import game.Game;
import game.GameLoop;
import utils.ElseUtils;
import utils.FileLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Menu {

    private String GAME_VERSION;

    private static Icon bgImage;

    private static final int AOD = 0;
    private static final String AOD_PATH = "game/themes/angels_of_death/";
    private static final int AOT = 1;
    private static final String AOT_PATH = "game/themes/attack_on_titan/";
    private static final String THEME_PATH = "game/themes";


    public static String[] playerName;
    public static String[] maps;

    private static final String[] playerSheetsInFolder = new String[] {"stand.png", "walk.png", "hit.png"};

    private static final int npccount = 1;


    private final JFrame menu = new JFrame();
    private final int width, height;
    private final JLabel background = new JLabel();

    private final JButton bExit = new JButton("EXIT");
    private final JButton bChangeColor = new JButton("change colors");
    private JButton bStartGame;
    private static Rectangle startBounds;
    private JButton[] bPlayers;
    private JButton[] bMaps;
    private final JButton[] bThemes = new JButton[gameThemes.length];
    private static Rectangle buttonBounds;
    private JButton bBackPlayers = new JButton();
    private JButton bBackThemes = new JButton();
    private static JButton bBackMaps = new JButton();

    public static int labelHeight;
    public static int labelWidth1;
    public static int labelWidth2;
    public static int fontSize;

    private static double highScore;

    private static JLabel lThemeText = new JLabel("Theme:");
    private static JLabel lTheme = new JLabel("");
    private static JLabel lPlayerText = new JLabel("Character:");
    private static JLabel lPlayer = new JLabel("");
    private static JLabel lMapText = new JLabel("Map:");
    private static JLabel lMap = new JLabel("");
    private static JLabel lHighscoreText = new JLabel("Highscore:");
    private static JLabel lHighscore = new JLabel("");
    private static JLabel lScoreText = new JLabel("Score:");
    private static JLabel lScore = new JLabel("");

    /**
     * declaration of themes, player names and colors
     **/

    public static final String[] playerNamesAoD = FileLoader.loadFileNames(AOD_PATH + "characters", "npc", FileLoader.ALL);
    public static final String[] mapsAoD = FileLoader.loadFileNames(AOD_PATH + "maps");
    public static final Color bgColorAoD = new Color(128, 186, 224);

    public static final String[] playerNamesAoT = FileLoader.loadFileNames(AOT_PATH + "characters", "npc", FileLoader.ALL);
    public static final String[] mapsAoT = FileLoader.loadFileNames(AOT_PATH + "maps");
    public static final Color bgColorAoT = new Color(70, 90, 120);

    public static final String[] gameThemes = FileLoader.loadFileNames(THEME_PATH);
    private static final Color[] bgColors = new Color[]{bgColorAoD, bgColorAoT};

    private static int name;
    private static int theme;
    private static int map;

    public static int colorSetting = 0;

    private static final Color color1 = new Color(250, 200, 230);
    private static final Color color2 = new Color(4, 162, 236);
    private static final Color color3 = new Color(187, 120, 160);
    private static final Color color4 = new Color(255, 111, 197);
    private static final Color clear = new Color(0,0,0,0);

    private static final Color[] buttonColor =           { color1,         color1,         color3,        Color.BLACK};
    private static final Color[] backButtonColor =      { color2,         color2,         color3,        Color.BLACK};
    private static final Color[] labelColor =            { color2,         color2,         color3,        Color.BLACK};
    private static final Color[] textColor =             { Color.WHITE,    Color.BLACK,    Color.BLACK,   color4};
    private final String[] bgImages =                    {"bg_light.png", "bg_light.png", "bg_dark.png", "bg_dark.png"};

    public static final String textFont = "Comic Sans MS";


    private static void setPlayerNames(int i){
        if(i==AOD) playerName = playerNamesAoD;
        if(i==AOT) playerName = playerNamesAoT;
    }

    private static void setMaps(int i){
        if(i==AOD) maps = mapsAoD;
        if(i==AOT) maps = mapsAoT;
    }

    public Menu(String version){
        this.colorSetting = Integer.parseInt(Objects.requireNonNull(FileLoader.load("color")));
        this.width = ScreenSize.getWidth();
        this.height = ScreenSize.getHeight();
        this.GAME_VERSION = version;

        setHighscore();

        initializeMenu();

        initializeButtons();

        initializeLabels();

        initializeBackground();

        addAll();

        menu.setVisible(true);
    }


    private void initializeMenu() {
        menu.setSize(width,height);
        menu.setTitle("GameMenu   | " + GAME_VERSION + " |");
        menu.setIconImage(FileLoader.loadImage("sakura_icon.png", "/"));
        menu.setUndecorated(true);
        menu.getContentPane().setBackground(new Color(23, 139, 221));
        menu.setResizable(false);
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);
    }

    private void initializeLabels() {
        // Status-Anzeige
        labelHeight = menu.getHeight()/35;
        labelWidth1 = labelHeight*4;
        labelWidth2 = (int) ((double) labelHeight*6.3);
        fontSize = labelHeight-8;

        lThemeText.setBounds(menu.getWidth()-labelWidth1-labelWidth2-22, 35, labelWidth1, labelHeight);
        lThemeText.setOpaque(true);
        lThemeText.setForeground(textColor[colorSetting]);
        lThemeText.setBackground(labelColor[colorSetting]);
        lThemeText.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lTheme.setBounds(menu.getWidth()-labelWidth2-20, 35, labelWidth2, labelHeight);
        lTheme.setOpaque(true);
        lTheme.setForeground(textColor[colorSetting]);
        lTheme.setBackground(labelColor[colorSetting]);
        lTheme.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lPlayerText.setBounds(menu.getWidth()-labelWidth1-labelWidth2-22, 38+labelHeight, labelWidth1, labelHeight);
        lPlayerText.setOpaque(true);
        lPlayerText.setForeground(textColor[colorSetting]);
        lPlayerText.setBackground(labelColor[colorSetting]);
        lPlayerText.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lPlayer.setBounds(menu.getWidth()-labelWidth2-20, 38+labelHeight, labelWidth2, labelHeight);
        lPlayer.setOpaque(true);
        lPlayer.setForeground(textColor[colorSetting]);
        lPlayer.setBackground(labelColor[colorSetting]);
        lPlayer.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lMapText.setBounds(menu.getWidth()-labelWidth1-labelWidth2-22, 41+2*labelHeight, labelWidth1, labelHeight);
        lMapText.setOpaque(true);
        lMapText.setForeground(textColor[colorSetting]);
        lMapText.setBackground(labelColor[colorSetting]);
        lMapText.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lMap.setBounds(menu.getWidth()-labelWidth2-20, 41+2*labelHeight, labelWidth2, labelHeight);
        lMap.setOpaque(true);
        lMap.setForeground(textColor[colorSetting]);
        lMap.setBackground(labelColor[colorSetting]);
        lMap.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lHighscoreText.setBounds(menu.getWidth()-labelWidth1-labelWidth2-22, 44+3*labelHeight, labelWidth1, labelHeight);
        lHighscoreText.setOpaque(true);
        lHighscoreText.setForeground(textColor[colorSetting]);
        lHighscoreText.setBackground(labelColor[colorSetting]);
        lHighscoreText.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lHighscore.setBounds(menu.getWidth()-labelWidth2-20, 44+3*labelHeight, labelWidth2, labelHeight);
        lHighscore.setOpaque(true);
        lHighscore.setForeground(textColor[colorSetting]);
        lHighscore.setBackground(labelColor[colorSetting]);
        lHighscore.setFont(new Font(textFont, Font.PLAIN, fontSize));
        lHighscore.setText(" " + ElseUtils.shorten(String.valueOf(highScore),4));

        lScoreText.setBounds(menu.getWidth()-labelWidth1-labelWidth2-22, 47+4*labelHeight, labelWidth1, labelHeight);
        lScoreText.setOpaque(true);
        lScoreText.setForeground(textColor[colorSetting]);
        lScoreText.setBackground(labelColor[colorSetting]);
        lScoreText.setFont(new Font(textFont, Font.PLAIN, fontSize));

        lScore.setBounds(menu.getWidth()-labelWidth2-20, 47+4*labelHeight, labelWidth2, labelHeight);
        lScore.setOpaque(true);
        lScore.setForeground(textColor[colorSetting]);
        lScore.setBackground(labelColor[colorSetting]);
        lScore.setFont(new Font(textFont, Font.PLAIN, fontSize));
        lScore.setText(" " + ElseUtils.shorten(String.valueOf(GameDisplay.getScore()),4));
        // -Status-Anzeige-
    }

    private void addAll() {

        //bei Farbwechsel wird vorheriger Punkt beibehalten
        if(!lMap.getText().equals("")) {
            menu.add(bStartGame);
            menu.add(bBackMaps);
        } else if(!lPlayer.getText().equals("")) {
            addMaps();
            menu.add(bBackPlayers);
        } else if(!lTheme.getText().equals("")) {
            addPlayers();
            menu.add(bBackThemes);
        } else {
            addThemes();
        }


        menu.add(lTheme);
        lTheme.setVisible(true);
        menu.add(lThemeText);
        menu.add(lPlayer);
        lPlayer.setVisible(true);
        menu.add(lPlayerText);
        menu.add(lMap);
        lMap.setVisible(true);
        menu.add(lMapText);
        menu.add(lHighscore);
        lHighscore.setVisible(true);
        menu.add(lHighscoreText);
        menu.add(lScore);
        menu.add(lScoreText);
        menu.add(bChangeColor);
        menu.add(bExit);
    }

    private void initializeBackground() {
        background.setBounds(0,0,menu.getWidth(), menu.getHeight());
        bgImage = FileLoader.loadIcon(bgImages[colorSetting],"/menu/", menu.getWidth(), menu.getHeight());
        background.setIcon(bgImage);
        menu.setContentPane(background);
    }

    private void initializeButtons() {

        // start Button
        bStartGame = new JButton("START GAME");
        bStartGame.setBounds(menu.getWidth()/3,menu.getHeight()/3,menu.getWidth()/3,menu.getHeight()/5);
        bStartGame.setBackground(buttonColor[colorSetting]);
        bStartGame.setForeground(textColor[colorSetting]);
        bStartGame.setFont(new Font(textFont, Font.PLAIN, bStartGame.getHeight()/4));
        bStartGame.addActionListener(getActionListenerStart());
        startBounds = bStartGame.getBounds();
        // -start Button-


        // back Buttons
        int backHeight = menu.getHeight()/22;
        int backWidth = backHeight*4;

        Font font = new Font(textFont, Font.PLAIN, backHeight/5*3);

        bBackThemes = new JButton("<<back");
        bBackThemes.setBounds(10, 10, backWidth, backHeight);
        bBackThemes.setFont(font);
        bBackThemes.setBackground(backButtonColor[colorSetting]);
        bBackThemes.setForeground(textColor[colorSetting]);
        bBackThemes.addActionListener(getActionListenerBackThemes());

        bBackPlayers = new JButton("<<back");
        bBackPlayers.setBounds(bBackThemes.getBounds());
        bBackPlayers.setFont(font);
        bBackPlayers.setBackground(backButtonColor[colorSetting]);
        bBackPlayers.setForeground(textColor[colorSetting]);
        bBackPlayers.addActionListener(getActionListenerBackPlayers());

        bBackMaps = new JButton("<<back");
        bBackMaps.setBounds(bBackThemes.getBounds());
        bBackMaps.setFont(font);
        bBackMaps.setBackground(backButtonColor[colorSetting]);
        bBackMaps.setForeground(textColor[colorSetting]);
        bBackMaps.addActionListener(getActionListenerBackMaps());

        // -back Buttons-


        // color change Button
        bChangeColor.setBounds(menu.getWidth()-25-backWidth, menu.getHeight()-backHeight-10, backWidth, backHeight);
        bChangeColor.setFont(new Font(textFont, Font.PLAIN, backHeight/2));
        bChangeColor.setBackground(buttonColor[colorSetting]);
        bChangeColor.setForeground(textColor[colorSetting]);
        bChangeColor.addActionListener(getActionListenerColor());
        // - color change Button-


        // exit Button
        bExit.setBounds(10, menu.getHeight()-backHeight-10, backWidth, backHeight);
        bExit.setFont(font);
        bExit.setBackground(buttonColor[colorSetting]);
        bExit.setForeground(textColor[colorSetting]);
        bExit.addActionListener(getActionListenerExit());
        // - exit Button-
    }




    private void addPlayers(){
        bPlayers = new JButton[playerName.length];

        int height = menu.getHeight()/12;
        int width = height*10;

        for (int i = 0; i < playerName.length; i++) {
            bPlayers[i] = new JButton(ElseUtils.makeNameNice(playerName[i]));

            bPlayers[i].setBounds(menu.getWidth()/4,menu.getHeight()/10+i*(menu.getHeight()/12 + 10),width,height);
            bPlayers[i].setFont(new Font(textFont, Font.PLAIN, bPlayers[i].getHeight()/3));
            bPlayers[i].setBackground(buttonColor[colorSetting]);
            bPlayers[i].setForeground(textColor[colorSetting]);
            bPlayers[i].addActionListener(getActionListenerPlayers(i));
            menu.add(bPlayers[i]);
            menu.getContentPane().setBackground(getBGColor());
            menu.repaint();
        }
        buttonBounds = bPlayers[0].getBounds();
        lTheme.setText(" " + ElseUtils.makeNameNice(getGameTheme()));
        menu.add(bBackThemes);
        menu.repaint();
    }

    private void addThemes(){

        int height = menu.getHeight()/12;
        int width = height*10;

        for (int i = 0; i < gameThemes.length; i++) {
            bThemes[i] = new JButton(ElseUtils.makeNameNice(gameThemes[i]));

            bThemes[i].setBounds(menu.getWidth()/4,menu.getHeight()/10+i*(menu.getHeight()/12 + 10),width,height);
            bThemes[i].setFont(new Font(textFont, Font.PLAIN, bThemes[i].getHeight()/3));
            bThemes[i].setBackground(buttonColor[colorSetting]);
            bThemes[i].setForeground(textColor[colorSetting]);

            bThemes[i].addActionListener(getActionListenerThemes(i));
            menu.add(bThemes[i]);
        }
        menu.repaint();
    }

    private void addMaps(){
        bMaps = new JButton[maps.length];

        int width = menu.getWidth()/2;
        int height = menu.getHeight()/12;

        for (int i = 0; i < maps.length; i++) {
            bMaps[i] = new JButton(ElseUtils.makeNameNice(maps[i]));

            bMaps[i].setBounds(menu.getWidth()/4,menu.getHeight()/10+i*(menu.getHeight()/12 + 10),width,height);
            bMaps[i].setFont(new Font(textFont, Font.PLAIN, bMaps[i].getHeight()/3));
            bMaps[i].setBackground(buttonColor[colorSetting]);
            bMaps[i].setForeground(textColor[colorSetting]);

            bMaps[i].addActionListener(getActionListenerMaps(i));
            menu.add(bMaps[i]);
        }
        menu.add(bBackPlayers);
        menu.repaint();
    }



    public static String[] getPlayerSheetsInFolder() {
        return playerSheetsInFolder;
    }

    private ActionListener getActionListenerColor() {
        return e -> {
            if(colorSetting < buttonColor.length-1) {
                colorSetting++;
            } else {
                colorSetting = 0;
            }
            FileLoader.save("" + colorSetting, "color");
            new Menu(GAME_VERSION);
            menu.dispose();
        };
    }

    private ActionListener getActionListenerExit() {
        return e -> System.exit(0);
    }

    private ActionListener getActionListenerStart() {
        return e -> {
            GameLoop gameLoop = new GameLoop(new Game(GAME_VERSION));
            new Thread(gameLoop).start();
            menu.dispose();
        };
    }

    private ActionListener getActionListenerBackThemes(){
        return e-> {
            for (JButton button : bPlayers) {
                menu.remove(button);
            }
            lTheme.setText("");
            addThemes();
            menu.remove(bBackThemes);
            menu.repaint();
        };
    }

    private ActionListener getActionListenerBackPlayers() {
        return e -> {
            for (JButton button : bMaps) {
                menu.remove(button);
            }
            lPlayer.setText("");
            menu.remove(bBackPlayers);
            menu.repaint();
            addPlayers();
        };
    }

    private ActionListener getActionListenerBackMaps() {
        return e -> {
            lMap.setText("");
            menu.remove(bStartGame);
            menu.remove(bBackMaps);
            menu.repaint();
            addMaps();
        };
    }

    private ActionListener getActionListenerThemes(int in){
        return e -> {
            theme = in;
            setPlayerNames(theme);
            setMaps(theme);
            for (JButton button : bThemes) {
                menu.remove(button);
            }
            addPlayers();
            menu.add(bBackThemes);
            menu.repaint();
        };
    }


    private ActionListener getActionListenerPlayers(int in) {
        return e -> {
            name = in;
            for (JButton button : bPlayers) {
                menu.remove(button);
            }
            menu.remove(bBackThemes);
            lPlayer.setText(" " + ElseUtils.makeNameNice(getPlayerName()));
            addMaps();
            menu.add(bBackPlayers);
            menu.repaint();
        };
    }


    private ActionListener getActionListenerMaps(int in) {
        return e -> {
            map = in;
            for (JButton button : bMaps) {
                menu.remove(button);
            }
            menu.remove(bBackPlayers);
            lMap.setText(" " + ElseUtils.makeNameNice(getMapName()));
            menu.add(bBackMaps);
            menu.add(bStartGame);
            menu.repaint();
        };
    }

    private void setHighscore(){
        double highscore = Double.parseDouble(FileLoader.load("HighScore"));
        double score = GameDisplay.getScore();

        if (highscore >= score) {
            highScore = highscore;
        } else {
            FileLoader.save(String.valueOf(score), "HighScore");
            highScore = score;
        }
    }

    public static JLabel getHighscore(){
        return lHighscore;
    }

    public static JLabel getThemeLabel() {
        return lTheme;
    }

    public static JLabel getPlayerLabel() {
        return lPlayer;
    }

    public static JLabel getMapLabel(){
        return lMap;
    }

    public static JLabel getScore() {
        return lScore;
    }

    public static String getGameTheme(){
        return gameThemes[theme];
    }

    public static String getPlayerName(){
        return playerName[name];
    }

    public static String[] getPlayerNames(){
        return playerName;
    }

    public static String[] getNPCs(){
        String[] npcs = new String[npccount];

        for (int i = 0; i < npccount; i++) {
            npcs[i] = "npc_" + (i+1);
        }

        return npcs;
    }

    public static String getMapName(){
        return maps[map];
    }

    public static Color getBGColor(){
        return bgColors[theme];
    }

    public static Icon getBGImage(){
        return bgImage;
    }

    public static Color getButtonColor(){
        return buttonColor[colorSetting];
    }

    public static Color getTextColor(){
        return textColor[colorSetting];
    }

    public static Rectangle getBackBounds() {
        return bBackMaps.getBounds();
    }

    public static Rectangle getButtonBounds(){
        return buttonBounds;
    }

    public static Rectangle getStartBounds(){
        return startBounds;
    }

}
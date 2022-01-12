package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.ScreenSize;
import core.motion.NPCMotion;
import core.motion.PlayerMotion;
import entity.Grass;
import entity.Ground;
import entity.NPC;
import entity.Player;
import input.Input;
import utils.FileLoader;

import java.util.List;

public class GameState extends State {


    public GameState(Input input) {
        super(input);

        createMap();

        initializeCharacters();
    }

    private void initializeCharacters() {

        Player player = new Player(new PlayerController(input), new PlayerMotion(2), spriteLibrary, mapObjects, gameObjects);
        NPC npc = new NPC(new NPCController(), new NPCMotion(2), spriteLibrary, mapObjects, gameObjects);

        gameObjects.addAll(List.of(player, npc));
    }


    private void createMap() {

        int ground = ScreenSize.getGround();
        int screenHeight = ScreenSize.getHeight();

        String[][] sMap = FileLoader.loadMap();

        /*
        //everything underneath the ground
        for (int posY = screenHeight; posY >= ground+64; posY-=64) {
            int posX = -64;
            for (int j = 0; j < sMap[0].length-1; j++) {

                if(sMap[0][j].equalsIgnoreCase("G")){
                    Grass grass = new Grass(posX, posY, "grass");
                    mapObjects.add(grass);
                }

                posX += 64;
            }
        }

         */


        Ground groundBlock = new Ground(sMap[0].length*64,screenHeight-ground, -64, ground+64, "ground");
        mapObjects.add(groundBlock);


        for (int i = 1; i < sMap.length; i++) {
            for (int j = 0; j < sMap[i].length; j++) {
                if(sMap[i][j].equalsIgnoreCase("G")){
                    Grass grass = new Grass((j-1)*64, ground-(i-1)*64, "grass");
                    mapObjects.add(grass);
                }
            }
        }

    }
}

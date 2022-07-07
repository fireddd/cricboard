import service.GameService;
import service.PlayerService;
import service.TeamService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        File file = new File(
                "C:\\Users\\shubh\\Desktop\\Learn Git\\cricboard\\src\\main\\java\\Inputs.txt");
        Scanner sc = new Scanner(file);
        List<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String inp = sc.nextLine();
            inputs.add(inp);
        }
        PlayerService playerService = new PlayerService();
        TeamService teamService = new TeamService(playerService);
        GameService gameService = new GameService(playerService, teamService);
        Integer teamOneID = 1;
        Integer teamTwoID = 2;
        gameService.initializeGame(inputs.get(0), inputs.get(1));
        gameService.playGame(inputs, teamOneID, teamTwoID);
        gameService.printGameResults(teamOneID, teamTwoID);

        System.out.println();
        System.out.println(teamService.getTeam(teamOneID).getTeamScore().toString());

        System.out.println();
        System.out.println(playerService.getPlayer("P1").toString());

    }
}

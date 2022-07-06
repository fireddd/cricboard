import service.GameService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception
    {
        File file = new File(
                "C:\\Users\\shubh\\Desktop\\Learn Git\\cricboard\\src\\main\\java\\Inputs.txt");
        Scanner sc = new Scanner(file);
        List<String> inputs = new ArrayList<>();
        while (sc.hasNextLine())
        {
            String inp = sc.nextLine();
            inputs.add(inp);
        }
        GameService gameService = new GameService();
        gameService.initializeGame(inputs);
        gameService.playGame(inputs);
        gameService.printGameResults();

    }
}

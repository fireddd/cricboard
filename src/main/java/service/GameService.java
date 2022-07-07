package service;

import exceptions.InvalidInputException;
import exceptions.OutOfBoundBallException;
import model.Ball;
import model.BallType;
import model.Game;
import model.Team;
import strategy.BallProcessFactory;
import strategy.IBallProcessStrategy;
import util.BallUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GameService implements IGameService{
    Game game;
    BallProcessFactory ballProcessFactory;
    TeamService teamService;
    BallUtil ballUtil;
    PlayerService playerService;

    public GameService(PlayerService playerService, TeamService teamService){
        this.playerService = playerService;
        this.teamService = teamService;
        this.ballUtil = new BallUtil();
        this.ballProcessFactory = new BallProcessFactory(playerService, teamService);
    }

    @Override
    public void initializeGame(String numberOfPlayers, String numberOfOvers)
    {
        game = new Game();
        try{
            game.setNumberOfPlayers(Integer.parseInt(numberOfPlayers));
            game.setNumberOfOvers(Integer.parseInt(numberOfOvers));
        }
        catch(Exception exception)
        {
            throw new InvalidInputException();
        }

    }

    @Override
    public void playGame(List<String> gameInputs)
    {
        Integer teamOneID = 1;
        teamService.initializeTeam( teamOneID ,gameInputs.get(2));
        //System.out.println(firstTeam.toString());
        int oversPlayedInFirstInning = playInning(teamOneID, gameInputs,3);
        game.setFirstTeam(teamOneID);
        System.out.println();
        System.out.println("Scorecard for Team 1: ");
        System.out.println();
        teamService.printTeamScore(teamOneID);
        System.out.println();
        System.out.println();

        Integer teamTwoID = 2;
        teamService.initializeTeam(teamTwoID, gameInputs.get(3 + oversPlayedInFirstInning));
        //System.out.println(secondTeam.toString());
        playInning(teamTwoID, gameInputs,4 + oversPlayedInFirstInning);
        game.setSecondTeam(teamTwoID);
        System.out.println();
        System.out.println("Scorecard for Team 2: ");
        System.out.println();
        teamService.printTeamScore(teamTwoID);
        System.out.println();
        System.out.println();
    }

    private void process(Ball ball, Integer teamID)
    {
        Team team = teamService.getTeam(teamID);
        if(team.getTeamScore().getTotalWickets() == game.getNumberOfPlayers()-1)
            throw new InvalidInputException();
        IBallProcessStrategy processingBallStrategy = ballProcessFactory.getProcessingStrategy(ball.getBallType());
        processingBallStrategy.processBall(ball, teamID);
    }

    private void processOver(String overString, Integer teamID)
    {
        List<String> balls = Arrays.stream(overString.trim().split(" ")).collect(Collectors.toList());
        for(String ballString: balls)
        {
            BallType ballType = ballUtil.getBallTypeFromCode(ballString);
            Ball ball = null;
            try{
                int currentRuns = Integer.parseInt(ballString);
                if(currentRuns <= 0 || currentRuns > 6)
                    throw new OutOfBoundBallException();
                ball = new Ball(ballType, currentRuns);
            }
            catch (Exception e){
                ball = new Ball(ballType);
            }
            process(ball, teamID);
        }
        System.out.println();
        teamService.printTeamScore(teamID);
        teamService.changeStrike(teamID);
    }

    private int playInning(Integer teamID, List<String> inputs ,int startInputFrom)
    {
        Team team = teamService.getTeam(teamID);
        int currentOver = 0;
        while((currentOver < game.getNumberOfOvers() && team.getTeamScore().getTotalWickets() < game.getNumberOfPlayers() - 1))
        {
            processOver(inputs.get(startInputFrom+ currentOver), teamID);
            currentOver++;
        }
        return currentOver;
    }

    private void whoWon(Integer teamOneID, Integer teamTwoID)
    {
        Team firstTeam = teamService.getTeam(teamOneID);
        Team secondTeam = teamService.getTeam(teamTwoID);
        Integer firstTeamRuns = firstTeam.getTeamScore().getTotalRuns();
        Integer secondTeamRuns = secondTeam.getTeamScore().getTotalRuns();
        int delta = firstTeamRuns-secondTeamRuns;
        if(delta == 0)
        {
            System.out.println(" The match is a draw and both the team scored "+ firstTeamRuns);
        }
        else if(delta > 0)
        {
            System.out.println(" The match was won by the first team and they won by "+ delta + "runs");
        }
        else
        {
            int wicketsWon = game.getNumberOfPlayers() - secondTeam.getTeamScore().getTotalWickets();
            System.out.println(" The match was won by the second team and they won by "+ wicketsWon + "wickets");
        }

    }

    @Override
    public void printGameResults() {
        System.out.println("Game Results:");
        System.out.println();
        System.out.println("Team 1 Results:");
        teamService.printTeamScore(game.getFirstTeam());
        System.out.println();
        System.out.println("Team 2 Results:");
        teamService.printTeamScore(game.getSecondTeam());
        System.out.println();
        whoWon(1, 2);
    }


}

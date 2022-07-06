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
import util.TeamUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class GameService {
    Game game;
    BallProcessFactory ballProcessFactory;
    TeamUtil teamUtil;
    BallUtil ballUtil;

    public GameService(){
        this.teamUtil = new TeamUtil();
        this.ballUtil = new BallUtil();
        this.ballProcessFactory = new BallProcessFactory();
    }

    public void initializeGame(List<String> inputs)
    {
        game = new Game();
        game.setNumberOfPlayers(Integer.parseInt(inputs.get(0)));
        game.setNumberOfOvers(Integer.parseInt(inputs.get(1)));
    }


    public void playGame(List<String> gameInputs)
    {
        initializeGame(gameInputs);
        Team firstTeam = teamUtil.initializeTeam(gameInputs.get(2));
        //System.out.println(firstTeam.toString());
        int oversPlayedInFirstInning = playInning(firstTeam,gameInputs,3);
        game.setFirstTeam(firstTeam);
        System.out.println("Scorecard for Team 1: ");
        System.out.println();
        teamUtil.printTeamScore(firstTeam);
        System.out.println();
        System.out.println();
        Team secondTeam = teamUtil.initializeTeam(gameInputs.get(3 + oversPlayedInFirstInning));
        //System.out.println(secondTeam.toString());
        playInning(secondTeam,gameInputs,4 + oversPlayedInFirstInning);
        game.setSecondTeam(secondTeam);
        System.out.println("Scorecard for Team 2: ");
        System.out.println();
        teamUtil.printTeamScore(secondTeam);
        System.out.println();
        System.out.println();
    }

    private void process(Ball ball, Team team)
    {
        if(team.getTeamScore().getTotalWickets() == game.getNumberOfPlayers()-1)
            throw new InvalidInputException();
        IBallProcessStrategy processingBallStrategy = ballProcessFactory.getProcessingStrategy(ball.getBallType());
        processingBallStrategy.processBall(ball, team);
    }

    private void processOver(String overString, Team team)
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
            process(ball, team);
        }
        System.out.println();
        teamUtil.printTeamScore(team);
        teamUtil.changeStrike(team);
    }

    private int playInning(Team team, List<String> inputs ,int startInputFrom)
    {
        int currentOver = 0;
        while((currentOver < game.getNumberOfOvers() && team.getTeamScore().getTotalWickets() < game.getNumberOfPlayers() - 1))
        {
            processOver(inputs.get(startInputFrom+ currentOver), team);
            currentOver++;
        }
        return currentOver;
    }

    private void whoWon()
    {
        Integer firstTeamRuns = game.getFirstTeam().getTeamScore().getTotalRuns();
        Integer secondTeamRuns = game.getSecondTeam().getTeamScore().getTotalRuns();
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
            int wicketsWon = game.getNumberOfPlayers() - game.getSecondTeam().getTeamScore().getTotalWickets();
            System.out.println(" The match was won by the second team and they won by "+ wicketsWon + "wickets");
        }

    }
    public void printGameResults() {
        System.out.println("Game Results:");
        System.out.println();
        System.out.println("Team 1 Results:");
        teamUtil.printTeamScore(game.getFirstTeam());
        System.out.println();
        System.out.println("Team 2 Results:");
        teamUtil.printTeamScore(game.getSecondTeam());
        System.out.println();
        whoWon();
    }


}

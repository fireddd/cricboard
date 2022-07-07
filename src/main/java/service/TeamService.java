package service;

import exceptions.InvalidInputException;
import exceptions.OutOfBoundBallException;
import model.Ball;
import model.BallType;
import model.Player;
import model.Team;
import strategy.IBallProcessStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeamService {
    PlayerService playerService;

    public TeamService(PlayerService playerService) {
        this.playerService = playerService;
    }

//    public void changeStrike(Team team)
//    {
//        String striker = team.getCurrentStriker();
//        team.setCurrentStriker(team.getCurrentNonStriker());
//        team.setCurrentNonStriker(striker);
//    }
//
//    public Team initializeTeam(String players) {
//        Team team = new Team();
//        List<String> stringPlayers = Arrays.stream(players.trim().split(" ")).collect(Collectors.toList());
//        for(String player: stringPlayers)
//        {
//            team.getPlayerList().add(new Player(player));
//            playerService.addPlayer(player);
//        }
//
//        team.setCurrentStriker(team.getPlayerList().get(0).getName());
//        team.setCurrentNonStriker(team.getPlayerList().get(1).getName());
//        team.setNextPlayer(2);
//        return team;
//    }
//
//    public String getNextPlayer(Team team)
//    {
//        return team.getPlayerList().get(team.getNextPlayer()).getName();
//    }
//
//    public void printIndividualScore(Team team)
//    {
//        for(Player currentPlayer: team.getPlayerList())
//        {
//            System.out.println(currentPlayer.toString());
//        }
//    }
//
//    public void printTeamScore(Team team)
//    {
//        System.out.println(team.getTeamScore().toString());
//        printIndividualScore(team);
//    }
//
//    private void process(Ball ball, Team team)
//    {
//        if(team.getTeamScore().getTotalWickets() == game.getNumberOfPlayers()-1)
//            throw new InvalidInputException();
//        IBallProcessStrategy processingBallStrategy = ballProcessFactory.getProcessingStrategy(ball.getBallType());
//        processingBallStrategy.processBall(ball, team);
//    }
//
//    private void processOver(String overString, Team team)
//    {
//        List<String> balls = Arrays.stream(overString.trim().split(" ")).collect(Collectors.toList());
//        for(String ballString: balls)
//        {
//            BallType ballType = ballUtil.getBallTypeFromCode(ballString);
//            Ball ball = null;
//            try{
//                int currentRuns = Integer.parseInt(ballString);
//                if(currentRuns <= 0 || currentRuns > 6)
//                    throw new OutOfBoundBallException();
//                ball = new Ball(ballType, currentRuns);
//            }
//            catch (Exception e){
//                ball = new Ball(ballType);
//            }
//            process(ball, team);
//        }
//        System.out.println();
//        teamUtil.printTeamScore(team);
//        teamUtil.changeStrike(team);
//    }
//
//    private int playInning(Team team, List<String> inputs ,int startInputFrom)
//    {
//        int currentOver = 0;
//        while((currentOver < game.getNumberOfOvers() && team.getTeamScore().getTotalWickets() < game.getNumberOfPlayers() - 1))
//        {
//            processOver(inputs.get(startInputFrom+ currentOver), team);
//            currentOver++;
//        }
//        return currentOver;
//    }
}

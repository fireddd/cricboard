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
    private Game game;
    private BallProcessFactory ballProcessFactory;
    private TeamService teamService;

    public GameService(PlayerService playerService, TeamService teamService){
        this.teamService = teamService;
        this.ballProcessFactory = new BallProcessFactory(playerService, teamService);
    }

    @Override
    public void initializeGame(String numberOfPlayers, String numberOfOvers)
    {
        this.game = new Game();
        try{
            this.game.setNumberOfPlayers(Integer.parseInt(numberOfPlayers));
            this.game.setNumberOfOvers(Integer.parseInt(numberOfOvers));
        }
        catch(Exception exception)
        {
            throw new InvalidInputException();
        }

    }

    @Override
    public void playGame(List<String> gameInputs, Integer teamOneID, Integer teamTwoID)
    {
        this.teamService.initializeTeam( teamOneID ,gameInputs.get(2));

        int oversPlayedInFirstInning = playInning(teamOneID, gameInputs,3);
        this.game.setFirstTeam(teamOneID);
        System.out.println();
        System.out.println("Scorecard for Team 1: ");
        System.out.println();
        this.teamService.printTeamScore(teamOneID);
        System.out.println();
        System.out.println();


        this.teamService.initializeTeam(teamTwoID, gameInputs.get(3 + oversPlayedInFirstInning));

        playInning(teamTwoID, gameInputs,4 + oversPlayedInFirstInning);
        this.game.setSecondTeam(teamTwoID);
        System.out.println();
        System.out.println("Scorecard for Team 2: ");
        System.out.println();
        this.teamService.printTeamScore(teamTwoID);
        System.out.println();
        System.out.println();
    }


    private void validateInput(Ball ball, Integer teamID)
    {
        Team team = this.teamService.getTeam(teamID);
        int runToBeChased = Integer.MAX_VALUE;
        if(teamID == game.getSecondTeam())
        {
            runToBeChased = this.teamService.getTeam(game.getFirstTeam()).getTeamScore().getTotalRuns();
        }
        boolean isInvalid = team.getTeamScore().getTotalRuns() <= runToBeChased;
        if(team.getTeamScore().getTotalWickets() == this.game.getNumberOfPlayers()-1 || !isInvalid)
            throw new InvalidInputException();
    }
    private void processBall(Ball ball, Integer teamID)
    {
        validateInput(ball, teamID);
        IBallProcessStrategy processingBallStrategy =this.ballProcessFactory.getProcessingStrategy(ball.getBallType());
        processingBallStrategy.processBall(ball, teamID);
    }

    private void processOver(String overString, Integer teamID)
    {
        List<String> balls = Arrays.stream(overString.trim().split(" ")).collect(Collectors.toList());
        for(String ballString: balls)
        {
            BallType ballType = BallUtil.getBallTypeFromCode(ballString);
            Ball ball = null;
            try{
                int currentRuns = Integer.parseInt(ballString);
                if(currentRuns <= 0 || currentRuns > 6)
                    throw new OutOfBoundBallException();
                ball = new Ball(ballType, currentRuns);
            }
            catch (NumberFormatException e){
                ball = new Ball(ballType);
            }
            catch (OutOfBoundBallException e)
            {
                throw new OutOfBoundBallException();
            }

            processBall(ball, teamID);
        }
        System.out.println();
        this.teamService.printTeamScore(teamID);
        this.teamService.changeStrike(teamID);
    }

    private int playInning(Integer teamID, List<String> inputs ,int startInputFrom)
    {
        Team team = this.teamService.getTeam(teamID);
        int currentOver = 0;
        while((currentOver < this.game.getNumberOfOvers() && team.getTeamScore().getTotalWickets() < this.game.getNumberOfPlayers() - 1 ))
        {
            processOver(inputs.get(startInputFrom+ currentOver), teamID);
            currentOver++;
        }
        return currentOver;
    }

    private void whoWon(Integer teamOneID, Integer teamTwoID)
    {
        Team firstTeam = this.teamService.getTeam(teamOneID);
        Team secondTeam = this.teamService.getTeam(teamTwoID);
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
            int wicketsWon = this.game.getNumberOfPlayers() - secondTeam.getTeamScore().getTotalWickets();
            System.out.println(" The match was won by the second team and they won by "+ wicketsWon + "wickets");
        }

    }

    @Override
    public void printGameResults(Integer teamOneID, Integer teamTwoID) {
        System.out.println("Game Results:");
        System.out.println();
        System.out.println("Team 1 Results:");
        this.teamService.printTeamScore(this.game.getFirstTeam());
        System.out.println();
        System.out.println("Team 2 Results:");
        this.teamService.printTeamScore(this.game.getSecondTeam());
        System.out.println();
        whoWon(teamOneID, teamTwoID);
    }


}

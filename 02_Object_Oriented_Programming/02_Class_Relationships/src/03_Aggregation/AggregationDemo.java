package Aggregation;

public class AggregationDemo {
    public static void main(String[] args) {
        Team team = new Team("Warriors");
        team.addPlayer(new Player("Stephen"));
        team.addPlayer(new Player("Klay"));
        team.showTeam();
    }
}

/*
Output :
Team Warriors has players:
 - Stephen
 - Klay
*/
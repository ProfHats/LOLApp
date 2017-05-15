package wit.ie.lolappv1.models;

/**
 * Created by laptop user on 03/04/2017.
 */

public class SingleGame implements Comparable<SingleGame>{
    public String matchID;
    public String region;
    public String highestRank;
    public String matchResult;
    public String champUsed;

    public SingleGame(){}

    public SingleGame(String matchID, String region, String highestRank, String matchResult, String champUsed){
        this.matchID = matchID;
        this.region = region;
        this.highestRank = highestRank;
        this.matchResult = matchResult;
        this.champUsed = champUsed;
    }

    public SingleGame(String region, String highestRank, String matchResult, String champUsed){
        this.region = region;
        this.highestRank = highestRank;
        this.matchResult = matchResult;
        this.champUsed = champUsed;
    }

    public String getMatchID(){return matchID;}
    public void setMatchID(String matchID){this.matchID = matchID;}
    public String getRegion(){return region;}
    public void setRegion(String region){this.region = region;}
    public String getHighestRank(){return highestRank;}
    public void setHighestRank(String highestRank){this.highestRank=highestRank;}
    public String getMatchResult(){return matchResult;}
    public void setMatchResult(String matchResult){this.matchResult=matchResult;}
    public String getChampUsed(){return champUsed;}
    public void setChampUsed(String champUsed){this.champUsed = champUsed;}

    @Override
    public int compareTo(SingleGame other){
        return this.matchID.compareTo(other.matchID);
    }
}

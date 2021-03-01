import java.util.ArrayList;

public class GameCollector
{
    private ArrayList<Game> gameList;

    public
        void
        scan(ArrayList<String> filepaths, char scanType, PlatformName platform)
    {

    }


    public ArrayList<Game> getGameList()
    {
        return gameList;
    }


    public Game getGameByName(String name)
    {
        for (Game g : gameList)
        {
            if (g.getName().equals(name))
            {
                return g;
            }
        }
        return null;
    }


    public ArrayList<Game> getGamesByPlatform(PlatformName platform)
    {
        ArrayList<Game> platList = new ArrayList<>();
        for (Game g : gameList)
        {
            if (g.getPlatform() == platform)
            {
                platList.add(g);
            }
        }
        return platList;
    }
}

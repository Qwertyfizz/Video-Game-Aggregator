using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Indieteur.VDFAPI;
using Indieteur.SAMAPI;

namespace VDFTestApp
{
    class Program
    {
        static void Main(string[] args)
        {
            //VDFData vdfData = new VDFData();
            SteamAppsManager sam = new SteamAppsManager();
            IReadOnlyList<SteamApp> games = sam.SteamApps;
            //SteamApp value = games.FindAppByName("EndlessSpace2");
            List<String> outputList = new List<string>();
            /*
             * Need 3 things
             *  AppID - for launching (steam://rungameid/(AppID here))
             *  InstallDir - for finding the folder
             *  Name - Proper name (Better than the foldername)
             *  seperate fields by ,,,, - some games have , and | in the name, so a special sequence is needed
             *  seperate games by \n
             */
            foreach(SteamApp game in games){
                String gameCSV = game.AppID + ",,,," + game.InstallDir + ",,,," + game.Name;
                outputList.Add(gameCSV);
            }

            System.IO.File.WriteAllLines("steamGameData.txt", outputList);


        }
    }
}

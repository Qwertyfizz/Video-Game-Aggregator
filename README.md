# Video-Game-Aggregator
A program which collects video games from multiple different platforms into one program for easy management

User Instructions

*	Scanning for games
  	*	After opining the program, click the “Scan for games” button
  	*	The program will then scan the computer for the games that are installed
	  *	A window will pop up displaying what it found
* Adding a game
	* Click the “Add Game” button in the main view
	* Enter the Name, File path, and Launcher location
	*	You can use the “Browse” button to choose the file directly
	* Click the “Add Game” button
*	Searching the list
	*	You can search by typing the name of the game into the search box
	*	You can sort by platform by using the dropdown
*	Managing a game
	*	Select the game in the main view
	*	The information collected will be displayed in the main window
	*	Click “Open folder” to open the location where the game is installed
	*	Click “Launch selected game” to launch the game displayed
*	Get information from the Steam API
	*	This allows you to get extra information on your account for Steam games
	*	You need:
		*	Steam ID
			*	Navigate to your profile page
			*	Copy the URL and paste it into https://steamid.io/
			*	The number next to “steamID64” is your steam ID
		*	API key
			*	Go to https://steamcommunity.com/dev/apikey
			*	Enter anything you like into the domain name
			*	Agree to the Terms of Use and click “Register”
			*	Your key will be displayed
	*	Go to Edit -> Enter Steam Key
	*	Enter your Steam ID and API Key and click “Done”
	*	Scan for games again, and the list will update with both uninstalled games and the playtime for all your games
*	Saving and loading
	*	You can use the “Save” and “Load” buttons in the “File” menu in the upper left corner
	*	Note: Saving will overwrite all previous information with what is currently in the list

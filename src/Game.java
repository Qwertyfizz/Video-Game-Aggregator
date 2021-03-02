import java.io.IOException;

public class Game
{

    private String       name;
    private String       filepath;
    private String       exe;
    private PlatformName platform;

    /**
     * @param name
     * @param filepath
     * @param exe
     * @param platform
     */
    public Game(String name, String filepath, String exe, PlatformName platform)
    {
        this.name = name;
        this.filepath = filepath;
        this.exe = exe;
        this.platform = platform;
    }


    public void launch()
    {
        try
        {
            Runtime run = Runtime.getRuntime();
            run.exec(exe);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }


    /**
     * @return the filepath
     */
    public String getFilepath()
    {
        return filepath;
    }


    /**
     * @return the platform
     */
    public PlatformName getPlatform()
    {
        return platform;
    }


    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * @param filepath
     *            the filepath to set
     */
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }


    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(PlatformName platform)
    {
        this.platform = platform;
    }


	@Override
	public String toString() {
		return "name: " + name + "\nfilepath: " + filepath + "\nexe: " + exe + "\nplatform: " + platform;
	}

}

package map;

import java.util.ArrayList;
import java.util.List;

import game.audio.AudioMaster;
import game.audio.Source;

public class Map
{

	private List<Block> blocks;

	private int ambientSound;
	private Source ambientSource;

	public Map()
	{
		blocks = new ArrayList<Block>();
		
		ambientSound = AudioMaster.loadSound("ambient/ambient0");
		ambientSource = new Source();
		ambientSource.setPosition(0, 8, 80);
		ambientSource.setLooping(true);
		ambientSource.play(ambientSound);
	}

	public void cleanUp()
	{
		ambientSource.cleanUp();
	}

	public List<Block> getBlockArray()
	{
		return blocks;
	}

}

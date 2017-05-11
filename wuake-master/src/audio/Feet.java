package audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import game.texture.TextureMaterial;

public class Feet
{

	private final int VERSIONS = 4;

	private HashMap<TextureMaterial, List<Integer>> sounds;
	private TextureMaterial material;
	private TextureMaterial lastMaterial;
	private Source source;
	private Random random;
	private boolean shouldPlay;

	public Feet()
	{
		sounds = new HashMap<TextureMaterial, List<Integer>>();
		material = TextureMaterial.STEP;
		source = new Source();
		random = new Random();
		shouldPlay = true;

		List<Integer> dirtSounds = new ArrayList<Integer>();
		List<Integer> grassSounds = new ArrayList<Integer>();
		List<Integer> metalSounds = new ArrayList<Integer>();
		List<Integer> stepSounds = new ArrayList<Integer>();
		List<Integer> tileSounds = new ArrayList<Integer>();
		List<Integer> woodSounds = new ArrayList<Integer>();

		for (int i = 0; i < VERSIONS; i++)
		{
			dirtSounds.add(AudioMaster.loadSound("player/dirt" + i));
			grassSounds.add(AudioMaster.loadSound("player/grass" + i));
			metalSounds.add(AudioMaster.loadSound("player/metal" + i));
			stepSounds.add(AudioMaster.loadSound("player/step" + i));
			tileSounds.add(AudioMaster.loadSound("player/tile" + i));
			woodSounds.add(AudioMaster.loadSound("player/wood" + i));
		}

		sounds.put(TextureMaterial.DIRT, dirtSounds);
		sounds.put(TextureMaterial.GRASS, grassSounds);
		sounds.put(TextureMaterial.METAL, metalSounds);
		sounds.put(TextureMaterial.PLANK, woodSounds);
		sounds.put(TextureMaterial.STEP, stepSounds);
		sounds.put(TextureMaterial.TILE, tileSounds);
		sounds.put(TextureMaterial.WOOD, woodSounds);
	}

	public void play()
	{
		if ((!source.isPlaying() || lastMaterial != material) && shouldPlay)
		{
			source.play(sounds.get(material).get(random.nextInt(VERSIONS)));
			lastMaterial = material;
		}
	}
	
	public void cleanUp()
	{
		source.cleanUp();
	}
	
	public void shouldPlay(boolean shouldPlay)
	{
		this.shouldPlay = shouldPlay;
	}

	public void setMaterial(TextureMaterial material)
	{
		this.material = material;
	}

	public Source getSource()
	{
		return source;
	}

}

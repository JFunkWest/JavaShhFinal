package tool;

import game.texture.TextureMaterial;

public class TextureMaterialTools
{

	public static TextureMaterial fetchMaterial(String name)
	{
		if (name.startsWith("dirt")) return TextureMaterial.DIRT;
		else if (name.startsWith("grass")) return TextureMaterial.GRASS;
		else if (name.startsWith("metal")) return TextureMaterial.METAL;
		else if (name.startsWith("crate")) return TextureMaterial.PLANK;
		else if (name.startsWith("tile")) return TextureMaterial.TILE;
		else if (name.startsWith("wood")) return TextureMaterial.WOOD;
		else return TextureMaterial.STEP;
	}

}

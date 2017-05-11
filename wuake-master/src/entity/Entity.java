package entity;

import org.lwjgl.util.vector.Vector3f;

import game.model.TexturedModel;

public class Entity
{

	private Vector3f position;
	private Vector3f rotation;
	private float scale;

	private TexturedModel model;

	public Entity()
	{
	}

	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale)
	{
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}

	public TexturedModel getModel()
	{
		return model;
	}

	public void setModel(TexturedModel model)
	{
		this.model = model;
	}

}

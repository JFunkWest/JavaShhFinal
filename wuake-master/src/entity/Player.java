package entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import game.audio.AudioMaster;
import game.audio.Feet;
import game.map.Block;
import game.map.Map;

public class Player
{

	// Position and movement
	private Vector3f position;
	private Vector3f previous;
	private Vector3f rotation;
	private float dy, groundHeight;

	// Bounds
	private float width;
	private float height;
	private float length;

	private boolean onGround;
	private boolean walking;

	// Speed constants
	private float runSpeed;
	private float walkSpeed;
	private float speed;
	private float gravity;
	private float jumpForce;

	private Feet feet;
	private Map map;

	public Player(Map map)
	{
		this.map = map;

		position = new Vector3f(0, 35, 0);
		previous = new Vector3f(0, 35, 0);
		rotation = new Vector3f(0, 0, 0);

		width = 1;
		height = 4;
		length = 1;

		runSpeed = 22.0f;
		walkSpeed = 8.0f;
		gravity = 0.625f;
		jumpForce = 30.0f;

		feet = new Feet();

		calcGroundHeight();
	}

	public void update(float dt)
	{
		walking = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		speed = walking ? walkSpeed * dt : runSpeed * dt;
		feet.shouldPlay(!walking);

		verticalMovement(dt);

		if (Keyboard.isKeyDown(Keyboard.KEY_C))
		{
			position.set(0.0f, 35.0f, 0.0f);
			previous.set(0.0f, 35.0f, 0.0f);
			calcGroundHeight();
			dy = 0.0f;
		}

		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) Mouse.setGrabbed(true);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) Mouse.setGrabbed(false);

		boolean moved = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y)) * speed;
			if (collides()) position.x = previous.x;
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y)) * speed;
			if (collides()) position.z = previous.z;
			moved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			previous.setX(position.getX());
			position.x -= Math.sin(Math.toRadians(rotation.y)) * speed;
			if (collides()) position.x = previous.x;
			previous.setZ(position.getZ());
			position.z += Math.cos(Math.toRadians(rotation.y)) * speed;
			if (collides()) position.z = previous.z;
			moved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y - 90)) * speed;
			if (collides()) position.x = previous.x;
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y - 90)) * speed;
			if (collides()) position.z = previous.z;
			moved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y + 90)) * speed;
			if (collides()) position.x = previous.x;
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y + 90)) * speed;
			if (collides()) position.z = previous.z;
			moved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && onGround)
		{
			feet.play();
			dy = jumpForce * dt;
			onGround = false;
			moved = true;
		}

		Vector3f prevRot = new Vector3f(rotation);
		if (Mouse.isGrabbed())
		{
			rotation.y += (float) Mouse.getDX() * 7.25f * dt;
			rotation.x -= (float) Mouse.getDY() * 7.25f * dt;
			if (rotation.x < -90) rotation.x = -90;
			else if (rotation.x > 90) rotation.x = 90;
		}

		if (!prevRot.equals(rotation))
		{
			AudioMaster.updateOrientation(rotation);
		}
		if (moved)
		{
			calcGroundHeight();
			AudioMaster.updatePosition(position);
			AudioMaster.updateOrientation(rotation);
			feet.getSource().setPosition(position);
			feet.play();
		}
	}

	public void cleanUp()
	{
		feet.cleanUp();
	}

	private void verticalMovement(float dt)
	{
		if (dy != 0)
		{
			previous.setY(position.getY());
			position.y += dy;
			if (collides())
			{
				if (dy > 0)
				{
					position.setY(previous.getY());
					dy = 0;
				}
				else
				{
					position.y = groundHeight;
				}
			}
		}
		if (position.y <= groundHeight)
		{
			if (!onGround)
			{
				feet.play();
				onGround = true;
				position.y = groundHeight;
				dy = 0;
			}
		}
		else
		{
			onGround = false;
		}
		if (!onGround)
		{
			feet.shouldPlay(false);
			dy -= gravity * dt;
		}
	}

	private void calcGroundHeight()
	{
		groundHeight = Float.NEGATIVE_INFINITY;
		for (Block b : map.getBlockArray())
			if ((position.x <= b.getMaxX() && position.x + width >= b.getMinX()) && //
					(position.y >= b.getMaxY() && groundHeight <= b.getMaxY()) && //
					(position.z <= b.getMaxZ() && position.z + length >= b.getMinZ()))
			{
				feet.setMaterial(b.getModel().getTexture().getMaterial());
				groundHeight = b.getMaxY();
			}
		if (position.y != groundHeight)
		{
			feet.shouldPlay((onGround = false));
		}
	}

	private boolean collides()
	{
		for (Block b : map.getBlockArray())
			if ((position.x <= b.getMaxX() && position.x + width >= b.getMinX()) && //
					(position.y < b.getMaxY() && position.y + height >= b.getMinY()) && //
					(position.z <= b.getMaxZ() && position.z + length >= b.getMinZ())) //
				return true;
		return false;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

	public float getLenght()
	{
		return length;
	}

}

package render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.entity.Entity;
import game.entity.Player;
import game.map.Block;
import game.model.TexturedModel;

public class RenderMaster
{

	private static final float FOV = 100.0f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000.0f;

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;

	private EntityRenderer entityRenderer;
	private BlockRenderer blockRenderer;

	private Map<TexturedModel, List<Entity>> entities;
	private Map<Integer, List<Block>> blocks;

	public RenderMaster()
	{
		createProjectionMatrix();

		entityRenderer = new EntityRenderer(projectionMatrix);
		blockRenderer = new BlockRenderer(projectionMatrix);

		entities = new HashMap<TexturedModel, List<Entity>>();
		blocks = new HashMap<Integer, List<Block>>();
	}

	public void render(Player camera)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		createViewMatrix(camera);

		entityRenderer.render(viewMatrix, entities);
		blockRenderer.render(viewMatrix, blocks);

		entities.clear();
		blocks.clear();
	}

	public void processEntity(Entity entity)
	{
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null)
		{
			batch.add(entity);
		}
		else
		{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}

	public void processBlock(Block block)
	{
		int texture = block.getModel().getTexture().getID();
		List<Block> batch = blocks.get(texture);
		if (batch != null)
		{
			batch.add(block);
		}
		else
		{
			List<Block> newBatch = new ArrayList<Block>();
			newBatch.add(block);
			blocks.put(texture, newBatch);
		}
	}

	public void cleanUp()
	{
		entityRenderer.cleanUp();
		blockRenderer.cleanUp();
	}

	private void createViewMatrix(Player camera)
	{
		viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getX()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getY()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getZ()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
		Vector3f pos = camera.getPosition();
		Vector3f negPos = new Vector3f(-pos.x - camera.getWidth() / 2f, -pos.y - camera.getHeight(), -pos.z - camera.getLenght() / 2f);
		Matrix4f.translate(negPos, viewMatrix, viewMatrix);
	}

	private void createProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}

}

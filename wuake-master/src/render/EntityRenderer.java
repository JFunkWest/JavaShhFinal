package render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.entity.Entity;
import game.model.TexturedModel;
import game.shader.EntityShader;

public class EntityRenderer
{

	private EntityShader shader;

	public EntityRenderer(Matrix4f projectionMatrix)
	{
		shader = new EntityShader();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Matrix4f viewMatrix, Map<TexturedModel, List<Entity>> entities)
	{
		shader.start();
		shader.loadViewMatrix(viewMatrix);
		for (TexturedModel model : entities.keySet())
		{
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch)
			{
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
		shader.stop();
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
	}

	private void prepareTexturedModel(TexturedModel model)
	{
		GL30.glBindVertexArray(model.getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
	}

	private void unbindTexturedModel()
	{
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(entity.getPosition(), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(entity.getRotation().getX()), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(entity.getRotation().getY()), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(entity.getRotation().getZ()), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(entity.getScale(), entity.getScale(), entity.getScale()), matrix, matrix);

		shader.loadTransformationMatrix(matrix);
	}

}

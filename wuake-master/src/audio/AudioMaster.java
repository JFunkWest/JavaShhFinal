package audio;

import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.lwjgl.util.vector.Vector3f;

import game.Main;

public class AudioMaster
{

	private static HashMap<String, Integer> buffers;
	private static FloatBuffer bufferPosition;
	private static FloatBuffer bufferOrientation;
	private static float[] upVector;

	public static void init()
	{
		System.out.println("Initializing OpenAL.");
		try
		{
			AL.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}

		AL10.alDistanceModel(AL10.AL_INVERSE_DISTANCE_CLAMPED);

		buffers = new HashMap<String, Integer>();
		bufferPosition = BufferUtils.createFloatBuffer(3);
		bufferOrientation = BufferUtils.createFloatBuffer(6);
		upVector = new float[] { 0, 1, 0 };
	}

	public static void updatePosition(Vector3f position)
	{
		position.store(bufferPosition);
		bufferPosition.flip();
		AL10.alListener(AL10.AL_POSITION, bufferPosition);
		bufferPosition.clear();
	}

	public static void updateOrientation(Vector3f rotation)
	{
		float pitch = rotation.getX();
		float yaw = rotation.getY();

		float[] at = new float[] { //
				(float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))), //
				(float) (Math.sin(Math.toRadians(pitch))), //
				(float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))) };

		bufferOrientation.put(at);
		bufferOrientation.put(upVector);
		bufferOrientation.flip();
		AL10.alListener(AL10.AL_ORIENTATION, bufferOrientation);

		bufferOrientation.clear();
	}

	public static int loadSound(String file)
	{
		if (buffers.containsKey(file)) return buffers.get(file);
		System.out.println("Loading sound '" + file + "'.");
		int buffer = AL10.alGenBuffers();
		buffers.put(file, buffer);
		WaveData wav = WaveData.create("sounds/" + file + ".wav");
		if (wav == null)
		{
			System.err.println("Error loading sound '" + file + "'.");
			Main.forceStop();
			return 0;
		}
		AL10.alBufferData(buffer, wav.format, wav.data, wav.samplerate);
		wav.dispose();
		return buffer;
	}

	public static void cleanUp()
	{
		for (int buffer : buffers.values())
			AL10.alDeleteBuffers(buffer);
		AL.destroy();
	}

}

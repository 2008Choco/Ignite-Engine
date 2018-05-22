package me.choco.ignite.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.choco.ignite.IgniteGame;
import me.choco.ignite.model.Mesh;
import me.choco.ignite.model.Model;
import me.choco.ignite.model.Vertex;

import org.joml.Vector2f;
import org.joml.Vector3f;

public final class OBJLoader {

	private static final List<Integer> INDICES_BUFFER = new ArrayList<>();
	private static final List<Vertex> VERTEX_BUFFER = new ArrayList<>();
	
	private OBJLoader() { }
	
	public static Model loadModel(String path) {
		List<Vector3f> vertexes = new ArrayList<>();
		List<Vector2f> textureCoordinates = new ArrayList<>();
		List<Vector3f> normals = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(IgniteGame.class.getResourceAsStream(path)))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] lineParts = line.split("\\s+");
				
				float x, y, z;
				switch (lineParts[0]) {
					case "v": // Vertex
						x = Float.parseFloat(lineParts[1]);
						y = Float.parseFloat(lineParts[2]);
						z = Float.parseFloat(lineParts[3]);
						
						vertexes.add(new Vector3f(x, y, z));
						break;
					case "vt": // Texture Coordinate
						x = Float.parseFloat(lineParts[1]);
						y = Float.parseFloat(lineParts[2]);
						
						textureCoordinates.add(new Vector2f(x, y));
						break;
					case "vn": // Normal
						x = Float.parseFloat(lineParts[1]);
						y = Float.parseFloat(lineParts[2]);
						z = Float.parseFloat(lineParts[3]);
						
						normals.add(new Vector3f(x, y, z));
						break;
					
					// Face parsing
					case "f":
						parseAndRegisterFace(lineParts, vertexes, textureCoordinates, normals);
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Mesh mesh = new Mesh(VERTEX_BUFFER.toArray(new Vertex[VERTEX_BUFFER.size()]), INDICES_BUFFER.stream().mapToInt(Integer::intValue).toArray());
		Model model = new Model(mesh);
		
		VERTEX_BUFFER.clear();
		INDICES_BUFFER.clear();
		
		return model;
	}
	
	private static void parseAndRegisterFace(String[] lineParts, List<Vector3f> vertexes, List<Vector2f> textureCoordinates, List<Vector3f> normals) {
		/*
		 * "f 1/2/3 4/5/6 7/8/9"
		 * 1, 4 & 7 = Vertex index
		 * 2, 5 & 8 = Texture coordinate index
		 * 3, 6 & 9 = Normal index
		 */
		
		for (int i = 1; i <= 3; i++) {
			String[] faceData = lineParts[i].split("\\/");
			
			int vertexIndex = Integer.parseInt(faceData[0]);
			int textureCoordinateIndex = Integer.parseInt(faceData[1]);
			int normalIndex = Integer.parseInt(faceData[2]);
			
			Vertex vertex = new Vertex()
					.position(vertexes.get(vertexIndex))
					.textureCoordinates(textureCoordinates.get(textureCoordinateIndex))
					.normal(normals.get(normalIndex));
			
			VERTEX_BUFFER.add(vertex);
			INDICES_BUFFER.add(vertexIndex - 1);
		}
	}
	
}
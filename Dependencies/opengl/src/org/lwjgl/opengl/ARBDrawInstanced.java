/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import java.nio.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/ARB/draw_instanced.txt">ARB_draw_instanced</a> extension.
 * 
 * <p>A common use case in GL for some applications is to be able to draw the same object, or groups of similar objects that share vertex data, primitive
 * count and type, multiple times. This extension provides a means of accelerating such use cases while restricting the number of API calls, and keeping
 * the amount of duplicate data to a minimum.</p>
 * 
 * <p>This extension introduces two draw calls which are conceptually equivalent to a series of draw calls. Each conceptual call in this series is considered
 * an "instance" of the actual draw call.</p>
 * 
 * <p>This extension also introduces a read-only built-in variable to GLSL which contains the "instance ID." This variable initially contains 0, but increases
 * by one after each conceptual draw call.</p>
 * 
 * <p>By using the instance ID or multiples thereof as an index into a uniform array containing transform data, vertex shaders can draw multiple instances of
 * an object with a single draw call.</p>
 * 
 * <p>Requires {@link GL30 OpenGL 3.0} or <a href="http://www.opengl.org/registry/specs/EXT/gpu_shader4.txt">EXT_gpu_shader4</a> or <a href="http://www.opengl.org/registry/specs/NV/vertex_program4.txt">NV_vertex_program4</a>. Promoted to core in {@link GL31 OpenGL 3.1}.</p>
 */
public class ARBDrawInstanced {

	protected ARBDrawInstanced() {
		throw new UnsupportedOperationException();
	}

	static boolean isAvailable(GLCapabilities caps) {
		return checkFunctions(
			caps.glDrawArraysInstancedARB, caps.glDrawElementsInstancedARB
		);
	}

	// --- [ glDrawArraysInstancedARB ] ---

	/**
	 * Draw multiple instances of a range of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param first     the starting index in the enabled arrays
	 * @param count     the number of indices to be rendered
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawArraysInstancedARB(int mode, int first, int count, int primcount) {
		long __functionAddress = GL.getCapabilities().glDrawArraysInstancedARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callV(__functionAddress, mode, first, count, primcount);
	}

	// --- [ glDrawElementsInstancedARB ] ---

	/**
	 * Unsafe version of: {@link #glDrawElementsInstancedARB DrawElementsInstancedARB}
	 *
	 * @param count the number of elements to be rendered
	 * @param type  the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 */
	public static void nglDrawElementsInstancedARB(int mode, int count, int type, long indices, int primcount) {
		long __functionAddress = GL.getCapabilities().glDrawElementsInstancedARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callPV(__functionAddress, mode, count, type, indices, primcount);
	}

	/**
	 * Draws multiple instances of a set of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param count     the number of elements to be rendered
	 * @param type      the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 * @param indices   a pointer to the location where the indices are stored
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawElementsInstancedARB(int mode, int count, int type, long indices, int primcount) {
		nglDrawElementsInstancedARB(mode, count, type, indices, primcount);
	}

	/**
	 * Draws multiple instances of a set of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param type      the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 * @param indices   a pointer to the location where the indices are stored
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawElementsInstancedARB(int mode, int type, ByteBuffer indices, int primcount) {
		nglDrawElementsInstancedARB(mode, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), primcount);
	}

	/**
	 * Draws multiple instances of a set of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indices   a pointer to the location where the indices are stored
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawElementsInstancedARB(int mode, ByteBuffer indices, int primcount) {
		nglDrawElementsInstancedARB(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), primcount);
	}

	/**
	 * Draws multiple instances of a set of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indices   a pointer to the location where the indices are stored
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawElementsInstancedARB(int mode, ShortBuffer indices, int primcount) {
		nglDrawElementsInstancedARB(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), primcount);
	}

	/**
	 * Draws multiple instances of a set of elements.
	 *
	 * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indices   a pointer to the location where the indices are stored
	 * @param primcount the number of instances of the specified range of indices to be rendered
	 */
	public static void glDrawElementsInstancedARB(int mode, IntBuffer indices, int primcount) {
		nglDrawElementsInstancedARB(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), primcount);
	}

}
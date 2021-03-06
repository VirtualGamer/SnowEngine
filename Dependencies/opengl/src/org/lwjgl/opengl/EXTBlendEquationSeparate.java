/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/EXT/blend_equation_separate.txt">EXT_blend_equation_separate</a> extension.
 * 
 * <p><a href="http://www.opengl.org/registry/specs/EXT/blend_func_separate.txt">EXT_blend_func_separate</a> introduced separate RGB and alpha blend factors. <a href="http://www.opengl.org/registry/specs/EXT/blend_minmax.txt">EXT_blend_minmax</a> introduced a
 * distinct blend equation for combining source and destination blend terms. (<a href="http://www.opengl.org/registry/specs/EXT_blend_subtract/blend_subtract.txt">EXT_blend_subtract_blend_subtract</a> &
 * <a href="http://www.opengl.org/registry/specs/EXT/blend_logic_op.txt">EXT_blend_logic_op</a> added other blend equation modes.) OpenGL 1.4 integrated both functionalities into the core standard.</p>
 * 
 * <p>While there are separate blend functions for the RGB and alpha blend factors, OpenGL 1.4 provides a single blend equation that applies to both RGB and
 * alpha portions of blending.</p>
 * 
 * <p>This extension provides a separate blend equation for RGB and alpha to match the generality available for blend factors.</p>
 * 
 * <p>Requires {@link GL14 OpenGL 1.4} or {@link ARBImaging ARB_imaging} or <a href="http://www.opengl.org/registry/specs/EXT/blend_minmax.txt">EXT_blend_minmax</a> and/or
 * <a href="http://www.opengl.org/registry/specs/EXT_blend_subtract/blend_subtract.txt">EXT_blend_subtract_blend_subtract</a>. Promoted to core in {@link GL20 OpenGL 2.0}.</p>
 */
public class EXTBlendEquationSeparate {

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_BLEND_EQUATION_RGB_EXT   = 0x8009,
		GL_BLEND_EQUATION_ALPHA_EXT = 0x883D;

	protected EXTBlendEquationSeparate() {
		throw new UnsupportedOperationException();
	}

	static boolean isAvailable(GLCapabilities caps) {
		return checkFunctions(
			caps.glBlendEquationSeparateEXT
		);
	}

	// --- [ glBlendEquationSeparateEXT ] ---

	public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha) {
		long __functionAddress = GL.getCapabilities().glBlendEquationSeparateEXT;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callV(__functionAddress, modeRGB, modeAlpha);
	}

}
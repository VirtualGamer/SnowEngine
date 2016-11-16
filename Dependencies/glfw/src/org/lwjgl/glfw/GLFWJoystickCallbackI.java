/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.glfw;

import org.lwjgl.system.*;

import static org.lwjgl.system.dyncall.DynCallback.*;

/** Instances of this interface may be passed to the {@link GLFW#glfwSetJoystickCallback SetJoystickCallback} method. */
@FunctionalInterface
public interface GLFWJoystickCallbackI extends CallbackI.V {

	String SIGNATURE = "(ii)v";

	@Override
	default String getSignature() { return SIGNATURE; }

	@Override
	default void callback(long args) {
		invoke(
			dcbArgInt(args),
			dcbArgInt(args)
		);
	}

	/**
	 * Will be called when a joystick is connected to or disconnected from the system.
	 *
	 * @param jid   the joystick that was connected or disconnected
	 * @param event one of {@link GLFW#GLFW_CONNECTED CONNECTED} or {@link GLFW#GLFW_DISCONNECTED DISCONNECTED}
	 */
	void invoke(int jid, int event);

}
/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.stb;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.system.*;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

/**
 * Quad used for drawing a baked character, returned by {@link STBTruetype#stbtt_GetBakedQuad}.
 * 
 * <h3>Layout</h3>
 * 
 * <pre><code>struct stbtt_aligned_quad {
    float x0;
    float y0;
    float s0;
    float t0;
    float x1;
    float y1;
    float s1;
    float t1;
}</code></pre>
 */
public class STBTTAlignedQuad extends Struct implements NativeResource {

	/** The struct size in bytes. */
	public static final int SIZEOF;

	public static final int ALIGNOF;

	/** The struct member offsets. */
	public static final int
		X0,
		Y0,
		S0,
		T0,
		X1,
		Y1,
		S1,
		T1;

	static {
		Layout layout = __struct(
			__member(4),
			__member(4),
			__member(4),
			__member(4),
			__member(4),
			__member(4),
			__member(4),
			__member(4)
		);

		SIZEOF = layout.getSize();
		ALIGNOF = layout.getAlignment();

		X0 = layout.offsetof(0);
		Y0 = layout.offsetof(1);
		S0 = layout.offsetof(2);
		T0 = layout.offsetof(3);
		X1 = layout.offsetof(4);
		Y1 = layout.offsetof(5);
		S1 = layout.offsetof(6);
		T1 = layout.offsetof(7);
	}

	STBTTAlignedQuad(long address, ByteBuffer container) {
		super(address, container);
	}

	/**
	 * Creates a {@link STBTTAlignedQuad} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
	 * visible to the struct instance and vice versa.
	 *
	 * <p>The created instance holds a strong reference to the container object.</p>
	 */
	public STBTTAlignedQuad(ByteBuffer container) {
		this(memAddress(container), checkContainer(container, SIZEOF));
	}

	@Override
	public int sizeof() { return SIZEOF; }

	/** Returns the value of the {@code x0} field. */
	public float x0() { return nx0(address()); }
	/** Returns the value of the {@code y0} field. */
	public float y0() { return ny0(address()); }
	/** Returns the value of the {@code s0} field. */
	public float s0() { return ns0(address()); }
	/** Returns the value of the {@code t0} field. */
	public float t0() { return nt0(address()); }
	/** Returns the value of the {@code x1} field. */
	public float x1() { return nx1(address()); }
	/** Returns the value of the {@code y1} field. */
	public float y1() { return ny1(address()); }
	/** Returns the value of the {@code s1} field. */
	public float s1() { return ns1(address()); }
	/** Returns the value of the {@code t1} field. */
	public float t1() { return nt1(address()); }

	// -----------------------------------

	/** Returns a new {@link STBTTAlignedQuad} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
	public static STBTTAlignedQuad malloc() {
		return create(nmemAlloc(SIZEOF));
	}

	/** Returns a new {@link STBTTAlignedQuad} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
	public static STBTTAlignedQuad calloc() {
		return create(nmemCalloc(1, SIZEOF));
	}

	/** Returns a new {@link STBTTAlignedQuad} instance allocated with {@link BufferUtils}. */
	public static STBTTAlignedQuad create() {
		return new STBTTAlignedQuad(BufferUtils.createByteBuffer(SIZEOF));
	}

	/** Returns a new {@link STBTTAlignedQuad} instance for the specified memory address or {@code null} if the address is {@code NULL}. */
	public static STBTTAlignedQuad create(long address) {
		return address == NULL ? null : new STBTTAlignedQuad(address, null);
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
	 *
	 * @param capacity the buffer capacity
	 */
	public static Buffer malloc(int capacity) {
		return create(nmemAlloc(capacity * SIZEOF), capacity);
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
	 *
	 * @param capacity the buffer capacity
	 */
	public static Buffer calloc(int capacity) {
		return create(nmemCalloc(capacity, SIZEOF), capacity);
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated with {@link BufferUtils}.
	 *
	 * @param capacity the buffer capacity
	 */
	public static Buffer create(int capacity) {
		return new Buffer(BufferUtils.createByteBuffer(capacity * SIZEOF));
	}

	/**
	 * Create a {@link STBTTAlignedQuad.Buffer} instance at the specified memory.
	 *
	 * @param address  the memory address
	 * @param capacity the buffer capacity
	 */
	public static Buffer create(long address, int capacity) {
		return address == NULL ? null : new Buffer(address, null, -1, 0, capacity, capacity);
	}

	// -----------------------------------

	/** Returns a new {@link STBTTAlignedQuad} instance allocated on the thread-local {@link MemoryStack}. */
	public static STBTTAlignedQuad mallocStack() {
		return mallocStack(stackGet());
	}

	/** Returns a new {@link STBTTAlignedQuad} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero. */
	public static STBTTAlignedQuad callocStack() {
		return callocStack(stackGet());
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad} instance allocated on the specified {@link MemoryStack}.
	 *
	 * @param stack the stack from which to allocate
	 */
	public static STBTTAlignedQuad mallocStack(MemoryStack stack) {
		return create(stack.nmalloc(ALIGNOF, SIZEOF));
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
	 *
	 * @param stack the stack from which to allocate
	 */
	public static STBTTAlignedQuad callocStack(MemoryStack stack) {
		return create(stack.ncalloc(ALIGNOF, 1, SIZEOF));
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated on the thread-local {@link MemoryStack}.
	 *
	 * @param capacity the buffer capacity
	 */
	public static Buffer mallocStack(int capacity) {
		return mallocStack(capacity, stackGet());
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero.
	 *
	 * @param capacity the buffer capacity
	 */
	public static Buffer callocStack(int capacity) {
		return callocStack(capacity, stackGet());
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated on the specified {@link MemoryStack}.
	 *
	 * @param stack the stack from which to allocate
	 * @param capacity the buffer capacity
	 */
	public static Buffer mallocStack(int capacity, MemoryStack stack) {
		return create(stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
	}

	/**
	 * Returns a new {@link STBTTAlignedQuad.Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
	 *
	 * @param stack the stack from which to allocate
	 * @param capacity the buffer capacity
	 */
	public static Buffer callocStack(int capacity, MemoryStack stack) {
		return create(stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
	}

	// -----------------------------------

	/** Unsafe version of {@link #x0}. */
	public static float nx0(long struct) { return memGetFloat(struct + STBTTAlignedQuad.X0); }
	/** Unsafe version of {@link #y0}. */
	public static float ny0(long struct) { return memGetFloat(struct + STBTTAlignedQuad.Y0); }
	/** Unsafe version of {@link #s0}. */
	public static float ns0(long struct) { return memGetFloat(struct + STBTTAlignedQuad.S0); }
	/** Unsafe version of {@link #t0}. */
	public static float nt0(long struct) { return memGetFloat(struct + STBTTAlignedQuad.T0); }
	/** Unsafe version of {@link #x1}. */
	public static float nx1(long struct) { return memGetFloat(struct + STBTTAlignedQuad.X1); }
	/** Unsafe version of {@link #y1}. */
	public static float ny1(long struct) { return memGetFloat(struct + STBTTAlignedQuad.Y1); }
	/** Unsafe version of {@link #s1}. */
	public static float ns1(long struct) { return memGetFloat(struct + STBTTAlignedQuad.S1); }
	/** Unsafe version of {@link #t1}. */
	public static float nt1(long struct) { return memGetFloat(struct + STBTTAlignedQuad.T1); }

	// -----------------------------------

	/** An array of {@link STBTTAlignedQuad} structs. */
	public static class Buffer extends StructBuffer<STBTTAlignedQuad, Buffer> implements NativeResource {

		/**
		 * Creates a new {@link STBTTAlignedQuad.Buffer} instance backed by the specified container.
		 *
		 * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
		 * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
		 * by {@link STBTTAlignedQuad#SIZEOF}, and its mark will be undefined.
		 *
		 * <p>The created buffer instance holds a strong reference to the container object.</p>
		 */
		public Buffer(ByteBuffer container) {
			super(container, container.remaining() / SIZEOF);
		}

		Buffer(long address, ByteBuffer container, int mark, int pos, int lim, int cap) {
			super(address, container, mark, pos, lim, cap);
		}

		@Override
		protected Buffer self() {
			return this;
		}

		@Override
		protected Buffer newBufferInstance(long address, ByteBuffer container, int mark, int pos, int lim, int cap) {
			return new Buffer(address, container, mark, pos, lim, cap);
		}

		@Override
		protected STBTTAlignedQuad newInstance(long address) {
			return new STBTTAlignedQuad(address, container);
		}

		@Override
		protected int sizeof() {
			return SIZEOF;
		}

		/** Returns the value of the {@code x0} field. */
		public float x0() { return STBTTAlignedQuad.nx0(address()); }
		/** Returns the value of the {@code y0} field. */
		public float y0() { return STBTTAlignedQuad.ny0(address()); }
		/** Returns the value of the {@code s0} field. */
		public float s0() { return STBTTAlignedQuad.ns0(address()); }
		/** Returns the value of the {@code t0} field. */
		public float t0() { return STBTTAlignedQuad.nt0(address()); }
		/** Returns the value of the {@code x1} field. */
		public float x1() { return STBTTAlignedQuad.nx1(address()); }
		/** Returns the value of the {@code y1} field. */
		public float y1() { return STBTTAlignedQuad.ny1(address()); }
		/** Returns the value of the {@code s1} field. */
		public float s1() { return STBTTAlignedQuad.ns1(address()); }
		/** Returns the value of the {@code t1} field. */
		public float t1() { return STBTTAlignedQuad.nt1(address()); }

	}

}
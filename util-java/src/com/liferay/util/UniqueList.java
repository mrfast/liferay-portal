/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 */
public class UniqueList<E> extends ArrayList<E> {

	public UniqueList() {
		super();
	}

	public UniqueList(Collection<E> c) {
		super(c.size());

		addAll(c);
	}

	public UniqueList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public boolean add(E e) {
		if (!contains(e)) {
			return super.add(e);
		}
		else {
			return false;
		}
	}

	@Override
	public void add(int index, E e) {
		if (!contains(e)) {
			super.add(index, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		c = new ArrayList<E>(c);

		Iterator<? extends E> itr = c.iterator();

		while (itr.hasNext()) {
			E e = itr.next();

			if (contains(e)) {
				itr.remove();
			}
		}

		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		c = new ArrayList<E>(c);

		Iterator<? extends E> itr = c.iterator();

		while (itr.hasNext()) {
			E e = itr.next();

			if (contains(e)) {
				itr.remove();
			}
		}

		return super.addAll(index, c);
	}

	@Override
	public E set(int index, E e) {
		Thread currentThread = Thread.currentThread();

		StackTraceElement[] stackTraceElements = currentThread.getStackTrace();

		if (stackTraceElements.length >= 4) {
			StackTraceElement stackTraceElement = stackTraceElements[3];

			String stackTraceElementString = stackTraceElement.toString();

			if (stackTraceElementString.contains(_STACK_TRACE_COLLECTIONS)) {
				return super.set(index, e);
			}
		}

		if (!contains(e)) {
			return super.set(index, e);
		}
		else {
			return e;
		}
	}

	private static final String _STACK_TRACE_COLLECTIONS =
		"java.util.Collections.sort(Collections.java";

}
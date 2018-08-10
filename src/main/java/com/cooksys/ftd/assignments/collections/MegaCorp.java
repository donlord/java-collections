package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	private Map<FatCat, Set<Capitalist>> m = new HashMap<FatCat, Set<Capitalist>>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it and
	 * return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add it
	 * and return false
	 *
	 * @param capitalist the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {
		//last second change, has might be wrong
//		if (m.containsKey(capitalist)) {
//			return false;
//		}
		if (has(capitalist)) {
			return false;
		}
		if (capitalist.hasParent() == false && capitalist instanceof FatCat) {
			m.put((FatCat) capitalist, new HashSet<Capitalist>());
			return true;
		}
		if (capitalist.hasParent() && m.containsKey(capitalist.getParent())) {
			m.get(capitalist.getParent()).add(capitalist);
		} else {
			if (add(capitalist.getParent())) {
				m.get(capitalist.getParent()).add(capitalist);
				return true;
			}

		}
		return false;
	}

	/**
	 * @param capitalist the element to search for
	 * @return true if the element has been added to the hierarchy, false otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		if (m.containsKey(capitalist) || m.containsValue(capitalist)) {
			return true;
		} else
			return false;
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements have
	 *         been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		if (m.isEmpty()) {
			return new HashSet<Capitalist>();
		} else {
			Set<Capitalist> allElements = new HashSet<Capitalist>();
			for (FatCat c : m.keySet()) {
				allElements.addAll(m.get(c));
			}
			return allElements;
		}
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no parents
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		if (m.isEmpty()) {
			return new HashSet<FatCat>();
		} else {
			Set<FatCat> allParents = new HashSet<FatCat>();
			for (FatCat c : m.keySet()) {
				// add first FatCaT
				allParents.add(c);
				if (c.hasParent()) {
					allParents.add(c.getParent());
				}
			}
			return allParents;
		}

	}

	/**
	 * @param fatCat the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a direct
	 *         parent, or an empty set if the parent is not present in the hierarchy
	 *         or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		if (m.isEmpty() || m.containsKey(fatCat) == false) {
			return new HashSet<Capitalist>();
		}
		Set<Capitalist> allChildren = new HashSet<Capitalist>();
		for (Capitalist c : m.keySet()) {
			if (c.getParent() == fatCat) {
				allChildren.add(c);
			}
		}
		return allChildren;

	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of the
	 *         associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		if (m.isEmpty()) {
			return new HashMap<FatCat,Set<Capitalist>>();
		}
		//out of time returning for project compilation
		return new HashMap<FatCat,Set<Capitalist>>();
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the given
	 *         element has no parent or if its parent is not in the hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {
		if (m.isEmpty()) {
			return new ArrayList<FatCat>();
		}
		//out of time returning for project compilation
		return new ArrayList<FatCat>();
	}
}

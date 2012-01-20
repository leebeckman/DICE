/*
 * Created on 26.07.2004
 *
 */
package org.jresearch.gossip.am;

import java.io.Serializable;
import java.util.ArrayList;

import org.jresearch.gossip.am.model.IPermissionGuard;
import org.jresearch.gossip.am.values.PermissionPoint;

/**
 * @author dbelov
 * 
 */
public class PermissionGuard implements IPermissionGuard, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2050183370968552356L;

	private ArrayList permissions = new ArrayList();

	/**
	 * @param list
	 */
	public PermissionGuard(ArrayList list) {
		this();
		if (permissions == null) {
			throw new IllegalArgumentException(
					" argument can't have null value");
		}
		this.permissions = list;
	}

	/**
	 * 
	 */
	public PermissionGuard() {
		super();
	}

	/**
	 * @param p
	 */
	public void addPermission(PermissionPoint p) {
		if (this.permissions.contains(p)) {
			this.permissions.remove(p);
		}
		this.permissions.add(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.am.model.IPermissionGuard#checkPermission(org.jresearch.gossip.am.values.PermissionPoint)
	 */
	public boolean checkPermission(PermissionPoint p) {
		return this.permissions.contains(p);
	}

}
/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.hy.foundation.common.entity.jpa;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Serializable 接口用于序列号
 * 非ID字段OneToOne关联时关联实体需实现该接口
 * 
 * Cloneable 便于实现深克隆
 *
 * @author chenc
 *
 */

@JsonSerialize(include = Inclusion.NON_NULL)
@MappedSuperclass
public abstract class BasicEntity implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BasicEntity(){}
	public BasicEntity(String id){
		this.setId(Integer.parseInt(id));
	}

	// --------------------------------------- getter and setter -----------------------------------------------------------------
	public abstract Integer getId();

	public abstract void setId(Integer id);

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * 默认使用id进行比较。注意不要将不同类型对象进行比较，否则不同类型对象若id相同则会认为是相同对象。
	 */
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass())&&this.getId()==((BasicEntity)obj).getId();
	}
	
	//子类可选择性，重写此方法，不影响子类代码
	public BasicEntity generateDefaultEntity(){
		return null;
	}

}

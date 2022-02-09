package com.truper.spring.orm.practica27.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.truper.spring.orm.practica27.dao.IGenericDAO;

import lombok.Getter;

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	@Autowired
	protected @Getter SessionFactory sessionFactory;

	protected @Getter final Class<T> persistentClass;

	public GenericHibernateDAO(Class<T> type) {
		persistentClass = type;
	}

	@Override
	public void insert(T entity) {
		this.sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		this.sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public T findById(ID id) {
		return (T) this.sessionFactory.getCurrentSession().get(persistentClass, id);
	}

	@Override
	public T delete(ID id) {
		T entity = this.findById(id);
		return this.delete(entity);
	}

	@Override
	public T delete(T entity) {
		if (entity != null)
			this.sessionFactory.getCurrentSession().delete(entity);

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) this.sessionFactory.getCurrentSession()
				.createQuery("FROM " + persistentClass.getName()) // JPQL
				.list();
	}

	@Override
	public void detach(T entity) {
		this.sessionFactory.getCurrentSession().evict(entity);
	}

}

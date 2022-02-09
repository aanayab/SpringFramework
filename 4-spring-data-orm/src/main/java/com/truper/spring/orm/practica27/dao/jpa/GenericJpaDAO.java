package com.truper.spring.orm.practica27.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.truper.spring.orm.practica27.dao.IGenericDAO;

import lombok.Getter;

public abstract class GenericJpaDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected @Getter final Class<T> persistentClass;

	public GenericJpaDAO(Class<T> type) {
		persistentClass = type;
	}

	@Override
	public void insert(T entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	@Override
	public T findById(ID id) {
		return (T) this.entityManager.find(persistentClass, id);
	}

	@Override
	public T delete(ID id) {
		T entity = this.findById(id);
		return this.delete(entity);
	}

	@Override
	public T delete(T entity) {
		if (entity != null)
			this.entityManager.remove(entity);

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) this.entityManager
				.createQuery("FROM " + persistentClass.getName()) // JPQL
				.getResultList();
	}

	@Override
	public void detach(T entity) {
		this.entityManager.detach(entity);
	}

}

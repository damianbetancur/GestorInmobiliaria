/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.Cliente;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.UsuarioCliente;
import com.betancur.gestorinmobiliario.model.dao.exceptions.IllegalOrphanException;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class UsuarioClienteJpaController implements Serializable {

    public UsuarioClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioCliente usuarioCliente) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Cliente unClienteOrphanCheck = usuarioCliente.getUnCliente();
        if (unClienteOrphanCheck != null) {
            UsuarioCliente oldUnUsuarioClienteOfUnCliente = unClienteOrphanCheck.getUnUsuarioCliente();
            if (oldUnUsuarioClienteOfUnCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + unClienteOrphanCheck + " already has an item of type UsuarioCliente whose unCliente column cannot be null. Please make another selection for the unCliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente unCliente = usuarioCliente.getUnCliente();
            if (unCliente != null) {
                unCliente = em.getReference(unCliente.getClass(), unCliente.getId());
                usuarioCliente.setUnCliente(unCliente);
            }
            Inmobiliaria unaInmobiliariaUsuario = usuarioCliente.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario = em.getReference(unaInmobiliariaUsuario.getClass(), unaInmobiliariaUsuario.getId());
                usuarioCliente.setUnaInmobiliariaUsuario(unaInmobiliariaUsuario);
            }
            em.persist(usuarioCliente);
            if (unCliente != null) {
                unCliente.setUnUsuarioCliente(usuarioCliente);
                unCliente = em.merge(unCliente);
            }
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().add(usuarioCliente);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioCliente usuarioCliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioCliente persistentUsuarioCliente = em.find(UsuarioCliente.class, usuarioCliente.getId());
            Cliente unClienteOld = persistentUsuarioCliente.getUnCliente();
            Cliente unClienteNew = usuarioCliente.getUnCliente();
            Inmobiliaria unaInmobiliariaUsuarioOld = persistentUsuarioCliente.getUnaInmobiliariaUsuario();
            Inmobiliaria unaInmobiliariaUsuarioNew = usuarioCliente.getUnaInmobiliariaUsuario();
            List<String> illegalOrphanMessages = null;
            if (unClienteNew != null && !unClienteNew.equals(unClienteOld)) {
                UsuarioCliente oldUnUsuarioClienteOfUnCliente = unClienteNew.getUnUsuarioCliente();
                if (oldUnUsuarioClienteOfUnCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + unClienteNew + " already has an item of type UsuarioCliente whose unCliente column cannot be null. Please make another selection for the unCliente field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unClienteNew != null) {
                unClienteNew = em.getReference(unClienteNew.getClass(), unClienteNew.getId());
                usuarioCliente.setUnCliente(unClienteNew);
            }
            if (unaInmobiliariaUsuarioNew != null) {
                unaInmobiliariaUsuarioNew = em.getReference(unaInmobiliariaUsuarioNew.getClass(), unaInmobiliariaUsuarioNew.getId());
                usuarioCliente.setUnaInmobiliariaUsuario(unaInmobiliariaUsuarioNew);
            }
            usuarioCliente = em.merge(usuarioCliente);
            if (unClienteOld != null && !unClienteOld.equals(unClienteNew)) {
                unClienteOld.setUnUsuarioCliente(null);
                unClienteOld = em.merge(unClienteOld);
            }
            if (unClienteNew != null && !unClienteNew.equals(unClienteOld)) {
                unClienteNew.setUnUsuarioCliente(usuarioCliente);
                unClienteNew = em.merge(unClienteNew);
            }
            if (unaInmobiliariaUsuarioOld != null && !unaInmobiliariaUsuarioOld.equals(unaInmobiliariaUsuarioNew)) {
                unaInmobiliariaUsuarioOld.getUsuarios().remove(usuarioCliente);
                unaInmobiliariaUsuarioOld = em.merge(unaInmobiliariaUsuarioOld);
            }
            if (unaInmobiliariaUsuarioNew != null && !unaInmobiliariaUsuarioNew.equals(unaInmobiliariaUsuarioOld)) {
                unaInmobiliariaUsuarioNew.getUsuarios().add(usuarioCliente);
                unaInmobiliariaUsuarioNew = em.merge(unaInmobiliariaUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuarioCliente.getId();
                if (findUsuarioCliente(id) == null) {
                    throw new NonexistentEntityException("The usuarioCliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioCliente usuarioCliente;
            try {
                usuarioCliente = em.getReference(UsuarioCliente.class, id);
                usuarioCliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioCliente with id " + id + " no longer exists.", enfe);
            }
            Cliente unCliente = usuarioCliente.getUnCliente();
            if (unCliente != null) {
                unCliente.setUnUsuarioCliente(null);
                unCliente = em.merge(unCliente);
            }
            Inmobiliaria unaInmobiliariaUsuario = usuarioCliente.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().remove(usuarioCliente);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.remove(usuarioCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioCliente> findUsuarioClienteEntities() {
        return findUsuarioClienteEntities(true, -1, -1);
    }

    public List<UsuarioCliente> findUsuarioClienteEntities(int maxResults, int firstResult) {
        return findUsuarioClienteEntities(false, maxResults, firstResult);
    }

    private List<UsuarioCliente> findUsuarioClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioCliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UsuarioCliente findUsuarioCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioCliente> rt = cq.from(UsuarioCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

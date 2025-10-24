package org.example.lab3.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.lab3.data.ProjectMetric;

import java.util.List;

@Stateless
public class ProjectMetricDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void create(ProjectMetric projectMetric) {
        em.persist(projectMetric);
    }

    public void update(ProjectMetric projectMetric) {
        em.merge(projectMetric);
    }

    public void delete(Integer id) {
        ProjectMetric projectMetric = findById(id);
        if (projectMetric != null) {
            em.remove(projectMetric);
        }
    }

    public ProjectMetric findById(Integer id) {
        return em.find(ProjectMetric.class, id);
    }

    public List<ProjectMetric> findAll() {
        return em.createNamedQuery("ProjectMetric.findAll", ProjectMetric.class).getResultList();
    }
}
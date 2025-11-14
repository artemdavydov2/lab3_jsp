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

    // Зберігає новий проєкт у базі даних.
    public void create(ProjectMetric projectMetric) {
        em.persist(projectMetric);
    }

    // Оновлює існуючий проєкт у базі даних.
    public void update(ProjectMetric projectMetric) {
        em.merge(projectMetric);
    }

    // Видаляє проєкт за ідентифікатором з бази даних.
    public void delete(Integer id) {
        ProjectMetric projectMetric = findById(id);
        if (projectMetric != null) {
            em.remove(projectMetric);
        }
    }

    // Повертає проєкт за ідентифікатором з бази даних.
    public ProjectMetric findById(Integer id) {
        return em.find(ProjectMetric.class, id);
    }

    // Повертає всі проєкти з бази даних.
    public List<ProjectMetric> findAll() {
        return em.createNamedQuery("ProjectMetric.findAll", ProjectMetric.class)
                .getResultList();
    }
}
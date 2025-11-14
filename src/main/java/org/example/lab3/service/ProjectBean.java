package org.example.lab3.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.lab3.dao.ProjectMetricDao;
import org.example.lab3.data.ProjectMetric;

import java.io.Serializable;
import java.util.List;

@Named("projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

    @EJB
    private ProjectMetricDao projectMetricDao;

    private List<ProjectMetric> projects;
    private ProjectMetric newProject;

    @PostConstruct
    public void init() {
        projects = projectMetricDao.findAll();
        newProject = new ProjectMetric();
    }

    public void save() {
        projectMetricDao.create(newProject);
        // Оновлюємо список проектів на сторінці
        projects = projectMetricDao.findAll();
        // Створюємо новий порожній об'єкт для форми
        newProject = new ProjectMetric();

        // Додаємо повідомлення про успіх додавання
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Успіх!", "Проєкт додано."));
    }

    public void delete(Integer projectId) {
        projectMetricDao.delete(projectId);
        // Оновлюємо список проектів
        projects = projectMetricDao.findAll();

        // Додаємо повідомлення про успіх видалення
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Успіх!", "Проєкт видалено."));
    }

    // Геттери та сеттери
    public List<ProjectMetric> getProjects() {
        return projects;
    }

    public ProjectMetric getNewProject() {
        return newProject;
    }

    public void setNewProject(ProjectMetric newProject) {
        this.newProject = newProject;
    }
}

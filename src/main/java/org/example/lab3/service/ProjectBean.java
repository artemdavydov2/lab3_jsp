package org.example.lab3.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
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

    // Ініціалізує список проєктів і об'єкт форми.
    @PostConstruct
    public void init() {
        projects = projectMetricDao.findAll();
        newProject = new ProjectMetric();
    }

    // Зберігає новий або оновлений проєкт.
    public void save() {
        if (newProject.getId() == null) {
            projectMetricDao.create(newProject);
        } else {
            projectMetricDao.update(newProject);
        }
        projects = projectMetricDao.findAll();
        newProject = new ProjectMetric();
    }

    // Завантажує вибраний проєкт у форму для редагування.
    public void edit(ProjectMetric project) {
        this.newProject = project;
    }

    // Видаляє проєкт за ідентифікатором і оновлює список.
    public void delete(Integer projectId) {
        projectMetricDao.delete(projectId);
        projects = projectMetricDao.findAll();
    }

    // Повертає список проєктів для відображення в таблиці.
    public List<ProjectMetric> getProjects() {
        return projects;
    }

    // Повертає поточний проєкт, прив'язаний до форми.
    public ProjectMetric getNewProject() {
        return newProject;
    }

    // Встановлює поточний проєкт, прив'язаний до форми.
    public void setNewProject(ProjectMetric newProject) {
        this.newProject = newProject;
    }
}
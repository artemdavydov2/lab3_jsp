package org.example.lab3.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "project_metrics")
@NamedQuery(name = "ProjectMetric.findAll", query = "SELECT p FROM ProjectMetric p ORDER BY p.id ASC")
public class ProjectMetric implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name_project", nullable = false, length = 100)
    private String projectName;

    @Size(max = 255)
    @Column(name = "url_project")
    private String projectUrl;

    @Column(name = "budget_project", precision = 19, scale = 2)
    private BigDecimal projectBudget;

    @Column(name = "release_date_project")
    private LocalDate releaseDate;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getProjectUrl() { return projectUrl; }
    public void setProjectUrl(String projectUrl) { this.projectUrl = projectUrl; }
    public BigDecimal getProjectBudget() { return projectBudget; }
    public void setProjectBudget(BigDecimal projectBudget) { this.projectBudget = projectBudget; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMetric that = (ProjectMetric) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

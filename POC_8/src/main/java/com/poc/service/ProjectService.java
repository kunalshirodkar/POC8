package com.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.model.Project;
import com.poc.repository.ProjectRepository;

@Service
public class ProjectService{
	
	@Autowired 
	ProjectRepository projectRepository;

	
	public List<Project> findAll() {
		return projectRepository.findAll();
	}


	public Project findById(Long projectId) {
		Optional<Project> optional = projectRepository.findById(projectId);
		Project project = null;
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("Project not found for id: " + projectId);
		}
		return project;
	}

	
	public void saveProject(Project project) {
		projectRepository.save(project);	
	}


	public void deleteById(Long id) {
		projectRepository.deleteById(id);	
	}

}

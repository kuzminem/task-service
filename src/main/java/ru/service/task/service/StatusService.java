package ru.service.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.service.task.repository.StatusRepository;
import ru.service.task.repository.entity.StatusDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public String getStatus(long id) {
        return statusRepository.getById(id).getStatus();
    }

    public long getStatusId(String status) {
        return statusRepository.findByStatusEquals(status).getId();
    }

    public List<String> getAll() {
        return statusRepository.findAll().stream()
                .map(StatusDao::getStatus)
                .sorted()
                .collect(Collectors.toList());
    }
}

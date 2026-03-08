package com.example.ms_super_odette.service.subject;

import com.example.ms_super_odette.dto.request.SubjectRequest;
import com.example.ms_super_odette.dto.response.SubjectResponse;
import com.example.ms_super_odette.model.SessionData;
import com.example.ms_super_odette.model.Subject;
import com.example.ms_super_odette.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretarySubjectService implements SubjectStrategy {

    private final SubjectRepository subjectRepo;

    @Override
    public boolean supports(SessionData user) {
        return user.getRoles().contains("SECRETARIA");
    }

    @Override
    public List<SubjectResponse> getSubjects(SessionData user) {
        return subjectRepo.findAll().stream()
                .map(s -> new SubjectResponse(s.getId(), s.getName(), null, null))
                .toList();
    }

    public SubjectResponse upsert(Long id, SubjectRequest request) {
        subjectRepo.findByName(request.getName()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A subject with this name already exists.");
            }
        });

        Subject subject = (id != null)
                ? subjectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found."))
                : new Subject();

        subject.setName(request.getName());
        Subject saved = subjectRepo.save(subject);

        return new SubjectResponse(saved.getId(), saved.getName(), null, null);
    }

    public void delete(Long id) {
        if (!subjectRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found.");
        }
        subjectRepo.deleteById(id);
    }
}
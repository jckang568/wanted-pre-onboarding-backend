package kr.co.wanted.service;

import jakarta.transaction.Transactional;
import kr.co.wanted.api.request.JobPostingRequest;
import kr.co.wanted.common.http.ErrorType;
import kr.co.wanted.common.http.ApiResult;
import kr.co.wanted.domain.entity.JobPosting;
import kr.co.wanted.domain.mapper.JobPostingMapper;
import kr.co.wanted.repository.JobPostingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    public ApiResult<JobPosting> createJobPosting(JobPostingRequest jobPostingRequest) {
        JobPosting jobPosting = JobPostingMapper.INSTANCE.jobPostingRequestToJobPosting(jobPostingRequest);
        return ApiResult.ok(
                jobPostingRepository.save(jobPosting)
        );
    }

    @Transactional
    public ApiResult<JobPosting> updateJobPosting(Long jobPostingId, JobPostingRequest jobPostingRequest) {
        Optional<JobPosting> optionalJobPosting = jobPostingRepository.findById(jobPostingId);
        if (optionalJobPosting.isPresent()) {
            JobPosting jobPosting = optionalJobPosting.get();
            jobPosting.setRequiredSkills(jobPostingRequest.getRequiredSkills());
            jobPosting.setJobDescription(jobPostingRequest.getJobDescription());
            jobPosting.setPosition(jobPostingRequest.getPosition());
            jobPosting.setRewardAmount(jobPostingRequest.getRewardAmount());
            jobPosting.setUpdatedAt(LocalDateTime.now());
            return ApiResult.ok(jobPosting);
        } else {
            return ApiResult.error(ErrorType.JOBPOSTING_ERROR.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ApiResult<String> deleteJobPosting(Long jobPostingId) {
        if(jobPostingRepository.existsById(jobPostingId)) {
            jobPostingRepository.deleteById(jobPostingId);
            return ApiResult.ok(jobPostingId.toString());
        }
        return ApiResult.error(ErrorType.JOBPOSTING_ERROR.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ApiResult<List<JobPosting>> getAllJobPostings() {
        return ApiResult.ok(jobPostingRepository.findAll());
    }

    public List<JobPosting> searchJobPostings(String keyword) {
        // 채용공고 검색 조회 로직
        // jobPostingRepository.findByPositionContaining(keyword) 등을 사용
        return null;
    }

    public JobPosting getJobPostingDetails(Long jobPostingId) {
        // 채용상세페이지 조회 로직
        return jobPostingRepository.findById(jobPostingId).orElseThrow(() -> new RuntimeException(ErrorType.JOBPOSTING_ERROR.getMessage()));
    }
}


package kr.co.wanted.api;

import kr.co.wanted.api.request.JobPostingRequest;
import kr.co.wanted.common.http.ApiResult;
import kr.co.wanted.domain.entity.JobPosting;
import kr.co.wanted.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/jobpostings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping
    public ResponseEntity<ApiResult<JobPosting>> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest) {
        ApiResult<JobPosting> result = jobPostingService.createJobPosting(jobPostingRequest);
        if(result.isResult()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/{jobPostingId}")
    public ResponseEntity<ApiResult<JobPosting>> updateJobPosting(@PathVariable Long jobPostingId, @RequestBody JobPostingRequest jobPostingRequest) {
        ApiResult<JobPosting> updatedPosting = jobPostingService.updateJobPosting(jobPostingId, jobPostingRequest);
        if(updatedPosting.isResult()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedPosting);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedPosting);
    }

    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<ApiResult<String>> deleteJobPosting(@PathVariable Long jobPostingId) {
        ApiResult<String> result = jobPostingService.deleteJobPosting(jobPostingId);
        if(result.isResult()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<JobPosting>>> getAllJobPostings() {
        return ResponseEntity.ok(
                jobPostingService.getAllJobPostings()
        );
    }

    @GetMapping("/search")
    public List<JobPosting> searchJobPostings(@RequestParam String keyword) {
        return jobPostingService.searchJobPostings(keyword);
    }

    @GetMapping("/{jobPostingId}")
    public JobPosting getJobPostingDetails(@PathVariable Long jobPostingId) {
        return jobPostingService.getJobPostingDetails(jobPostingId);
    }
}

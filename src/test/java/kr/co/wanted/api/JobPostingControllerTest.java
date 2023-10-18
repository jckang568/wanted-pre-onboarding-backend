package kr.co.wanted.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.wanted.api.request.JobPostingRequest;
import kr.co.wanted.domain.entity.Company;
import kr.co.wanted.domain.entity.JobPosting;
import kr.co.wanted.service.JobPostingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobPostingService jobPostingService;

    @Test
    public void testCreateJobPosting() throws Exception {

        JobPostingRequest jobPostingRequest = new JobPostingRequest();
        jobPostingRequest.setCompany(1);
        jobPostingRequest.setPosition("백엔드 시니어 개발자");
        jobPostingRequest.setRewardAmount(1000000);
        jobPostingRequest.setJobDescription("원티드 인서트 테스트");
        jobPostingRequest.setRequiredSkills("JPA, Hibernate");

        mockMvc.perform(MockMvcRequestBuilders.post("/jobpostings")
                        .content(objectMapper.writeValueAsString(jobPostingRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateJobPosting() throws Exception {
        Long jobPostingId = 1L;
        JobPosting updateJobPosting = new JobPosting();
        updateJobPosting.setJobPostingId(1L);
        updateJobPosting.setPosition("Updated Backend Developer");
        updateJobPosting.setRewardAmount(1500000);
        updateJobPosting.setJobDescription("Updated job description...");
        updateJobPosting.setRequiredSkills("Java, Spring, Hibernate");

        mockMvc.perform(MockMvcRequestBuilders.put("/jobpostings/{id}", jobPostingId)
                        .content(objectMapper.writeValueAsString(updateJobPosting))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        JobPosting updatedJobPosting = jobPostingService.getJobPostingDetails(1L);
        assertEquals(updateJobPosting.getPosition(), updatedJobPosting.getPosition());
        assertEquals(updateJobPosting.getRewardAmount(), updatedJobPosting.getRewardAmount());
        assertEquals(updateJobPosting.getJobDescription(), updatedJobPosting.getJobDescription());
        assertEquals(updateJobPosting.getRequiredSkills(), updatedJobPosting.getRequiredSkills());
    }

    @Test
    public void testDeleteJobPosting() throws Exception {
        Long jobPostingId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/jobpostings/{id}", jobPostingId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetAllJobPostings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jobpostings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testSearchJobPostings() throws Exception {
        String keyword = "Backend";

        mockMvc.perform(MockMvcRequestBuilders.get("/jobpostings/search")
                        .param("keyword", keyword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testGetJobPostingDetails() throws Exception {
        Long jobPostingId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/jobpostings/{id}", jobPostingId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("백엔드 주니어 개발자"));
    }


}

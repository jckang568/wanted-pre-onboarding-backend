package kr.co.wanted.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobPostingRequest {
    @NotBlank
    private int company;
    private String position;
    @Min(1)
    private int rewardAmount;
    private String jobDescription;
    private String requiredSkills;
}

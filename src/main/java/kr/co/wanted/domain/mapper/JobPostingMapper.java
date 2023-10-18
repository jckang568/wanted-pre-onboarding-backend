package kr.co.wanted.domain.mapper;

import kr.co.wanted.api.request.JobPostingRequest;
import kr.co.wanted.domain.entity.Company;
import kr.co.wanted.domain.entity.JobPosting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobPostingMapper {
    JobPostingMapper INSTANCE = Mappers.getMapper(JobPostingMapper.class);

    @Mappings({
            @Mapping(target = "jobPostingId", ignore = true),
            @Mapping(source = "company", target = "company", qualifiedByName = "intToCompany")
    })
    JobPosting jobPostingRequestToJobPosting(JobPostingRequest jobPostingRequest);

    @Named("intToCompany")
    Company intToCompany(Integer companyId);
}

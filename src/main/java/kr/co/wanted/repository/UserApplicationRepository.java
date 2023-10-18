package kr.co.wanted.repository;

import kr.co.wanted.domain.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Long> {
}

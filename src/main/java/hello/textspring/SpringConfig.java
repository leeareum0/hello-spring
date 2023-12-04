package hello.textspring;

import hello.textspring.repository.JdbcMemberRepository;
import hello.textspring.repository.JpaMemberRepository;
import hello.textspring.repository.MemberRepository;
import hello.textspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    //스프링빈에 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}

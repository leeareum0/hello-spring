package hello.textspring.repository;

import hello.textspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
//  에러 원인 : findAll이 먼저 실행 됨 -> 이미 저장된 데이터가 출력됨
//  테스트 끝난 후 데이터(repository) 지워줘야 에러가 나지 않음 -> repository를 지워주는 코드 생성 필요
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//      테스트 방법1
//      System.out.println("result = " + (result == member));
//      Assertions.assertEquals : 방법2 member와 result랑 같은지  테스트 가능한 기능
//      Assertions.assertEquals(member, result);
//      assertThat : 방법3
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
//      member1 저장
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}


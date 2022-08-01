package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // 3. 필드주입
    // 코드가 간결해서 많은 개발자를 유혹하지만 외부에서 변경이 불가능해, 테스트가 힘들다.
    // DI 프레임워크가 없으면 아무것도 할 수 없다
    // 사용을 하지말자 (실제 코드와 관계 없는 테스트 코드, 스프링 설정을 목적으로 하는 @Configuration에서 사용)
//    @Autowired
    private MemberRepository memberRepository;
//    @Autowired
    private DiscountPolicy discountPolicy;


    // 1. 생성자주입
    // @Autowired가 없어도 bean을 상속받은 클래스는 등록할때 자동으로 생성자와 관련된 클래스를 주입한다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 2. 수정자 주입(setter 주입)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 3. 일반 메서드 주입
    // 일반메서드를 통해 주입, 잘 사용하지 않는다.
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

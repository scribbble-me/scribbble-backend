package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class MemberServiceTest(
    val memberService: MemberService
) : BehaviorSpec({

    Given("사용자가 아직 가입하지 않은 경우") {
        val email = "devhudi@gmail.com"
        val password = "password12345"
        val username = "후디"

        When("사용자가 가입을 한다면") {
            val memberRequest = MemberRequest(email, password, username)
            val memberResponse: MemberResponse = memberService.join(memberRequest)

            Then("사용자가 가입에 성공한다") {
                memberResponse.id shouldHaveLength 36
                memberResponse.email shouldBe email
                memberResponse.username shouldBe username
            }
        }

        When("사용자가 이미 가입된 이메일로 가입한다면") {
            val memberRequest = MemberRequest(email, password, username)

            Then("사용자 가입에 실패한다") {
                shouldThrow<java.lang.IllegalArgumentException> {
                    memberService.join(memberRequest)
                }
            }
        }
    }
})

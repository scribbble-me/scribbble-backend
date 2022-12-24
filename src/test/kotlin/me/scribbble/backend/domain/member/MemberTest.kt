package me.scribbble.backend.domain.member

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row

class MemberTest : BehaviorSpec({

    Given("Member 생성") {
        val email = "devhudi@gmail.com"
        val password = "password12345"
        val username = "후디"

        When("정상적인 정보를 전달하면") {
            Then("객체가 생성된다") {
                shouldNotThrow<IllegalArgumentException> {
                    Member(email, password, username)
                }
            }
        }

        When("잘못된 형식의 이메일을 전달하면") {
            Then("예외가 발생한다") {
                forAll(
                    row("devhudi"),
                    row("devhudi@"),
                    row("@gmail")
                ) { invalidEmail ->
                    shouldThrow<IllegalArgumentException> {
                        Member(invalidEmail, password, username)
                    }
                }
            }
        }

        When("비밀번호 8자보다 작거나, 64자를 초과하면") {
            Then("예외가 발생한다") {
                forAll(
                    row("1"),
                    row("1234567"),
                    row("1".repeat(65))
                ) { invalidPassword ->
                    shouldThrow<IllegalArgumentException> {
                        Member(email, invalidPassword, username)
                    }
                }
            }
        }

        When("유저네임이 1자보다 작거나, 5자를 초과하면") {
            Then("예외가 발생한다") {
                forAll(
                    row(""),
                    row("5자초과유저네임")
                ) { invalidUsername ->
                    shouldThrow<IllegalArgumentException> {
                        Member(email, password, invalidUsername)
                    }
                }
            }
        }
    }
})

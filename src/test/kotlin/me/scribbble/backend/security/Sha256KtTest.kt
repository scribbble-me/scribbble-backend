package me.scribbble.backend.security

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Sha256KtTest : StringSpec({

    "SHA256 해시" {
        // given
        val origin = "Hello, world!"

        // when
        val actual = sha256(origin)

        // then
        actual shouldBe "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3"
    }
})

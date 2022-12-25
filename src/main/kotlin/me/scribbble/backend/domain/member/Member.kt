package me.scribbble.backend.domain.member

import me.scribbble.backend.domain.school.School
import me.scribbble.backend.domain.support.DateBaseEntity
import me.scribbble.backend.security.sha256
import org.hibernate.annotations.GenericGenerator
import java.util.regex.Pattern
import javax.persistence.*

@Entity
class Member(
    val email: String,
    password: String,
    val username: String,
    @ManyToOne
    @JoinColumn(name = "school_id")
    val school: School
) : DateBaseEntity() {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: String = ""

    val password: String

    companion object {
        private const val EMAIL_REGEX =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        private const val PASSWORD_MIN_LENGTH = 8
        private const val PASSWORD_MAX_LENGTH = 64
        private const val USERNAME_MIN_LENGTH = 1
        private const val USERNAME_MAX_LENGTH = 5
    }

    init {
        validateEmail(email)
        validatePassword(password)
        validateUsername(username)

        this.password = sha256(password)
    }

    private fun validateEmail(email: String) {
        val isValid = Pattern.matches(EMAIL_REGEX, email)
        if (!isValid) {
            throw IllegalArgumentException("올바른 이메일 형식이 아닙니다.")
        }
    }

    private fun validatePassword(password: String) {
        if (password.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
            throw IllegalArgumentException("비밀번호는 ${PASSWORD_MIN_LENGTH}자 이상, ${PASSWORD_MAX_LENGTH}자 이하여야 합니다.")
        }
    }

    private fun validateUsername(username: String) {
        if (username.length !in USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH) {
            throw IllegalArgumentException("비밀번호는 ${USERNAME_MIN_LENGTH}자 이상, ${USERNAME_MAX_LENGTH}자 이하여야 합니다.")
        }
    }

    fun isCorrectPassword(password: String): Boolean {
        return this.password == sha256(password)
    }
}

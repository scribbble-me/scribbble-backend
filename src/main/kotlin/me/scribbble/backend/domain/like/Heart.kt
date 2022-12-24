package me.scribbble.backend.domain.like

import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.support.BaseEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Heart(
    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,
    val clientIp: String,
    val userAgent: String
) : BaseEntity()

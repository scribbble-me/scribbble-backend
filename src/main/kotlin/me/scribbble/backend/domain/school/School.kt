package me.scribbble.backend.domain.school

import me.scribbble.backend.domain.support.BaseEntity
import javax.persistence.Entity

@Entity
class School(
    val name: String
) : BaseEntity()

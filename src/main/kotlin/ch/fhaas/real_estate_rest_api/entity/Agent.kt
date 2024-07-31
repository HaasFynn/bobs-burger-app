package ch.fhaas.real_estate_rest_api.entity

import jakarta.persistence.*
import lombok.Getter

@Getter
@Table(name = "agent")
@Entity
data class Agent(
    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "firstname")
    val firstname: String,
    @Column(name = "surname")
    val surname: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "tel")
    val tel: String,
) {
    constructor() : this(0L, "", "", "", "")
}

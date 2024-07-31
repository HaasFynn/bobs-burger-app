package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Status
import ch.fhaas.real_estate_rest_api.entity_properties.Type
import jakarta.persistence.*
import lombok.Getter
import java.sql.Date

@Getter
@Table(name = "real_estate")
@Entity
data class RealEstate(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "type")
    val type: Type,
    @Column(name = "status")
    val status: Status,
    @Column(name = "price")
    val price: Double,
    @ManyToOne
    @JoinTable(name = "agent")
    var agent: Agent,
    @Column(name = "address")
    val address: String,
    @Column(name = "size_in_mm")
    val sizeInMM: Long,
    @Column(name = "num_of_rooms")
    val numOfRooms: Int,
    @Column(name = "num_of_floors")
    val numOfFloors: Int,
    @Column(name = "has_garage")
    val hasGarage: Boolean,
    @Column(name = "built_date")
    var builtDate: Date?
) {
    constructor() : this(
        0L,
        Type.UNDEFINED,
        Status.UNDEFINED,
        0.0, Agent(),
        "",
        0L,
        0,
        0,
        false,
        null
    )
}

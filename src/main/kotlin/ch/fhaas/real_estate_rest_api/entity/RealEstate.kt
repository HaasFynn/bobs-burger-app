package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.RealEstateStatus
import ch.fhaas.real_estate_rest_api.entity_properties.RealEstateType
import jakarta.persistence.*
import lombok.Getter
import java.sql.Date

@Getter
@Table(name = "RealEstate")
data class RealEstate(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "type_idfk")
    @ManyToOne(fetch = FetchType.EAGER)
    val type: RealEstateType,
    @Column(name = "status_idfk")
    @ManyToOne(fetch = FetchType.EAGER)
    val realEstateStatus: RealEstateStatus,
    @Column(name = "price")
    val price: Double,
    @Column(name = "agent_idfk")
    @ManyToOne(fetch = FetchType.EAGER)
    val agent: Agent,
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
    val builtDate: Date
)

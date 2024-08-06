package ch.fhaas.real_estate_rest_api.dao

import ch.fhaas.real_estate_rest_api.entity.StoreNextDoor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreNextDoorRepository: JpaRepository<StoreNextDoor, Long> {
}
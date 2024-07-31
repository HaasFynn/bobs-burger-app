package ch.fhaas.real_estate_rest_api.dao

import ch.fhaas.real_estate_rest_api.entity.RealEstate
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RealEstateDao: CrudRepository<RealEstate, Long> {
}
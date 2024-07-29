package ch.fhaas.real_estate_rest_api.jpa.dao

import ch.fhaas.real_estate_rest_api.entity.RealEstate

interface RealEstateDao {
    fun get(id: Long): RealEstate
    fun getAll(id: Long): List<RealEstate>
    fun save(entity: RealEstate): Boolean
    fun update(id: Long): Boolean
    fun delete(id: Long): Boolean
}
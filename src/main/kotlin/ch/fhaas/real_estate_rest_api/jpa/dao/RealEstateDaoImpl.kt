package ch.fhaas.real_estate_rest_api.jpa.dao

import ch.fhaas.real_estate_rest_api.entity.RealEstate
import jakarta.persistence.EntityManager

class RealEstateDaoImpl(entityManager: EntityManager): RealEstateDao {

    val em: EntityManager = entityManager

    override fun get(id: Long): RealEstate {
        TODO("Not yet implemented")
    }

    override fun getAll(id: Long): List<RealEstate> {
        TODO("Not yet implemented")
    }

    override fun save(entity: RealEstate): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}
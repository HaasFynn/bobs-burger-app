package ch.fhaas.real_estate_rest_api.jpa

import ch.fhaas.real_estate_rest_api.entity.RealEstate
import jakarta.persistence.EntityManagerFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

class EntityManagement {
    private var entityManagerFactory: EntityManagerFactory? = null

    fun createEntityManagerFactory(): EntityManagerFactory? {
        if (entityManagerFactory != null) {
            return entityManagerFactory
        }
        val registry =
            StandardServiceRegistryBuilder()
                .loadProperties("hibernate.properties").build()
        try {
            entityManagerFactory = MetadataSources(registry)
                .addAnnotatedClass(RealEstate::class.java)
                .buildMetadata()
                .buildSessionFactory()
        } catch (e: Exception) {
            StandardServiceRegistryBuilder.destroy(registry)
        }
        return entityManagerFactory
    }
}
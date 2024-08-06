package ch.fhaas.real_estate_rest_api.dao

import org.springframework.data.jpa.repository.JpaRepository
import ch.fhaas.real_estate_rest_api.entity.Character
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<Character, Long>
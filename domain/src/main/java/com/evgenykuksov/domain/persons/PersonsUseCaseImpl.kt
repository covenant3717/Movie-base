package com.evgenykuksov.domain.persons

class PersonsUseCaseImpl(private val repository: PersonsRepository) : PersonsUseCase {

    override fun getActor(id: Long) = repository.getActor(id)
}
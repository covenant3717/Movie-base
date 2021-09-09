package com.evgenykuksov.domain.actors

class ActorsUseCaseImpl(private val repository: ActorsRepository) : ActorsUseCase {

    override fun getActorInfo(id: Long) = repository.getActorInfo(id)
}
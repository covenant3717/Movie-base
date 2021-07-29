package com.evgenykuksov.domain.profile

import kotlinx.coroutines.flow.Flow

class ProfileUseCaseImpl(private val repository: ProfileRepository) : ProfileUseCase {

    override fun getRating(): Flow<Int> = repository.getRating()
}
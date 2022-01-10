package com.evgenykuksov.data.data.profile.local.memory

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

internal class ProfileMemoryDataSourceImpl : ProfileMemoryDataSource {

    override fun getRating() = flow {
        delay(2000)
        emit(4523)
    }
}
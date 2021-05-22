package br.com.leogmsantos.gitrepositories.persistence

import br.com.leogmsantos.gitrepositories.model.GITRepository
import br.com.leogmsantos.gitrepositories.model.GITRepositoryOwner

object MockTestUtil {

    fun mockRepository() = GITRepository(
        entityId = 1,
        id = 0,
        name = "Android MVVM Repository",
        description = "Just a sample of a mvvm project",
        owner = GITRepositoryOwner(
            avatarUrl = "https://avatars.githubusercontent.com/u/8831805?v=4",
            url = "https://api.github.com/users/clarkehe1",
            login = "clarkehe"
        ),
        forks = 40,
        watchers = 200
    )

    fun mockRepositoryList() = listOf(mockRepository())

}
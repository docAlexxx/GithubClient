package com.gb.poplib.githubclient.mvp.model.entity

class GitHubUserRepo {
    private val users = listOf(
        GithubUser("", "First User"),
        GithubUser("", "Second User"),
        GithubUser("", "Third User"),
        GithubUser("", "Fourth User"),
        GithubUser("", "Fifth User")
    )

    fun getUsers(): List<GithubUser> {
        return users
    }
}
package sealedClassHomeWork

import kotlin.properties.Delegates

class SocialNetworkRepository {

    //получение пользователя по его идентификатору
    fun findUserById(id: Int) : ResultResponse {
        lateinit var user: User
        for (userInList in NetworkDummy.getAllUsers()) {
            if (userInList.id == id) user = userInList
        }

        if (user == null) {
            return ResultResponse.Failure(NullPointerException())
        } else {
            return ResultResponse.Success(user)
        }
    }


    //получение списка постов, опубликованных пользователем.
    fun getUserPosts(user: User) : ResultResponse {
        val userPostsList = mutableListOf<Post>()
        for (post in NetworkDummy.getAllPosts()) {
            if (post.author == user) userPostsList.add(post)
        }

        if (userPostsList.isEmpty()) {
            return ResultResponse.Failure(NullPointerException())
        } else {
            return ResultResponse.Success(userPostsList)
        }
    }


    //создание нового поста пользователем
    fun createNewPost(user: User, content: String) : Post {
        val id = NetworkDummy.getAllPosts().size
        val author = user
        val content = content
        val post = Post(id, author, content)
        NetworkDummy.addNewPost(post)
        return Post(id, author, content)
    }

    //получение списка комментариев к посту.
    fun getPostComments (post: Post) : ResultResponse {
        val commentsList = NetworkDummy.getAllComments().filter { it.post == post}.toMutableList()

        if (commentsList.isEmpty()) {
            return ResultResponse.Failure(NullPointerException())
        } else {
            return ResultResponse.Success(commentsList)
        }
    }


    //поиск пользователя по имени
    //// возвращает список, т.к. пользователей с одним и тем же именем м.б. много
    fun findUsersByName(name: String) : ResultResponse {
        val userList = mutableListOf<User>()
        for (user in NetworkDummy.getAllUsers()) if (user.name == name) userList.add(user)

        if (userList.isEmpty()) {
            return ResultResponse.Failure(NullPointerException())
        } else {
            return ResultResponse.Success(userList)
        }
    }

}
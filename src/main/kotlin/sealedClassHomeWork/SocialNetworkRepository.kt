package sealedClassHomeWork

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
    fun findUserPosts(userId: Int) : ResultResponse {
        val userPostsList = mutableListOf<Post>()
        for (post in NetworkDummy.getAllPosts()) {
            if (post.author.id == userId) userPostsList.add(post)
        }

        if (userPostsList.isEmpty()) {
            return ResultResponse.Failure(NullPointerException())
        } else {
            return ResultResponse.Success(userPostsList)
        }
    }


    //создание нового поста пользователя по id Пользователя
    fun createNewPost(userId: Int, content: String) : Post? {
        val responseUser: ResultResponse = repository.findUserById(userId)
        when (responseUser) {
            is ResultResponse.Failure -> {
                println("User not found")
                return null
            }
            is ResultResponse.Success -> {
                val postAuthor: User = responseUser.data as User
                val postId = NetworkDummy.getAllPosts().size //id поста определяется по кол-ву имеющихся постов
                val post = Post(postId, postAuthor, content)
                NetworkDummy.addNewPost(post)
                return Post(postId, postAuthor, content)
            }
        }
    }

    //получение списка комментариев к посту.
    fun findPostComments (postId: Int) : ResultResponse {
        val commentsList = NetworkDummy.getAllComments().filter { it.post.id == postId}.toMutableList()

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
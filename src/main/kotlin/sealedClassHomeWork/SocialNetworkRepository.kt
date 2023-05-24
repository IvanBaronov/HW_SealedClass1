package sealedClassHomeWork

class SocialNetworkRepository {

    //получение пользователя по его идентификатору
    fun findUserById(id: Int, users: List<User>): ResultResponse {
        for (userInList in users) {
            if (userInList.id == id) {
                return ResultResponse.Success(userInList)
            }
        }
        return ResultResponse.Failure(NullPointerException())
    }

    //получение списка постов, опубликованных пользователем.
    fun findUserPosts(userId: Int, posts: List<Post>): ResultResponse {
        val needPosts = posts.filter { it.authorId == userId }
        return if (needPosts.isEmpty()) {
            ResultResponse.Failure(NullPointerException())
        } else {
            ResultResponse.Success(needPosts)
        }
    }


    //создание нового поста пользователя по id Пользователя
    fun createNewPost(userId: Int, content: String): Int? {
        val postId = posts.size //id поста определяется по кол-ву имеющихся постов
        val post = Post(postId, userId, content)
        NetworkDummy.addNewPost(post)
        return postId
    }


    //получение списка комментариев к посту.
    fun findPostComments(postId: Int, comments: List<Comment>): ResultResponse {
        val commentsList = comments.filter { it.post.id == postId }.toMutableList()

        return if (commentsList.isEmpty()) {
            ResultResponse.Failure(NullPointerException())
        } else {
            ResultResponse.Success(commentsList)
        }
    }


    //поиск пользователей по имени
    // возвращает список, т.к. пользователей с одним и тем же именем м.б. много
    fun findUsersByName(name: String, users: List<User>): ResultResponse {
        val userList = mutableListOf<User>()
        for (user in NetworkDummy.getAllUsers()) if (user.name == name) userList.add(user)

        val usersNeed = users.filter { it.name == name }
        return if (usersNeed.isEmpty()) {
            ResultResponse.Failure(NullPointerException())
        } else {
            ResultResponse.Success(userList)
        }
    }

}
package sealedClassHomeWork

import java.util.*

object NetworkDummy {

    private val users: MutableList<User>
    private val posts: MutableList<Post>
    private val comments: MutableList<Comment>
    //private val friendships: MutableList<Friendship>

    init {
        // Заполнение тестовых данных для пользователей
        users = generateUsers(1000)

        // Заполнение тестовых данных для постов
        posts = generatePosts(5000)

        // Заполнение тестовых данных для комментариев
        comments = generateComments(10000)

        // Заполнение тестовых данных для друзей
        //friendships = generateFriendships(users)
    }

    fun getAllUsers(): MutableList<User> {
        // Возвращение всех пользователей
        return users
    }

    fun getAllPosts(): MutableList<Post> {
        // Возвращение всех постов
        return posts
    }

    fun addNewPost(post: Post) {
        posts.add(post)
    }
    /*  добавил метод, позволяющий добавлять новые посты  в общий список, чтобы они не пропадали
    а также, чтобы генерировать id у новых постов в порядке их создания */

    fun getAllComments(): MutableList<Comment> {
        // Возвращение всех комментариев
        return comments
    }

//    fun getFriendshipsForUser(user: User): MutableList<User> {
//        // Возвращение списка друзей для указанного пользователя
//        val friendshipIds = friendships.filter { it.userId == user.id }.map { it.friendId }
//        return users.filter { it.id in friendshipIds }.toMutableList()
//    }

    fun generateUsers(count: Int): MutableList<User> {
        val userList = mutableListOf<User>()
        for (i in 1..count) {
            val id = i
            val name = "User $i"
            val avatarUrl = "https://example.com/avatar$i.jpg"
            val user = User(id, name, avatarUrl)
            userList.add(user)
        }
        return userList
    }

    fun generatePosts(count: Int): MutableList<Post> {
        val postList = mutableListOf<Post>()
        val random = Random()
        for (i in 1..count) {
            val id = i
            val author = users[random.nextInt(users.size)]
            val content = "Post $i content"
            val post = Post(id, author.id, content)
            postList.add(post)
        }
        return postList
    }

    fun generateComments(count: Int): MutableList<Comment> {
        val commentList = mutableListOf<Comment>()
        val random = Random()
        for (i in 1..count) {
            val id = i
            val post = posts[random.nextInt(posts.size)]
            val author = users[random.nextInt(users.size)]
            val content = "Comment $i content"
            val comment = Comment(id, post, author, content)
            commentList.add(comment)
        }
        return commentList
    }

//    fun generateFriendships(users: List<User>): MutableList<Friendship> {
//        val friendshipList = mutableListOf<Friendship>()
//        val random = Random()
//        for (user in users) {
//            val friendCount = random.nextInt(10) + 1 // Случайное количество друзей от 1 до 10
//            val friendIds = mutableListOf<Int>()
//            while (friendIds.size < friendCount) {
//                val friendId = random.nextInt(users.size) + 1
//                if (friendId != user.id && friendId !in friendIds) {
//                    friendIds.add(friendId)
//                }
//            }
//            val friendships = friendIds.map { Friendship(user.id, it) }
//            friendshipList.addAll(friendships)
//        }
//        return friendshipList
//    }

}
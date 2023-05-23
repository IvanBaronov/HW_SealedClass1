package sealedClassHomeWork

enum class Answer(val data: String) {
    YES("да"),
    NO("нет")
}

fun main(args: Array<String>) {

    //Создание репозитория сети
    val repository = SocialNetworkRepository()

    //генерация контента

    println("Введите число пользователей сети:")
    val usersCount = readln().toInt()
    val users = NetworkDummy.generateUsers(usersCount)
//    val friendships = NetworkDummy.generateFriendships(users)

    println("Введите число постов пользователей:")
    val postsCount = readln().toInt()
    val posts = NetworkDummy.generatePosts(postsCount)

    println("Введите число комментариев к постам:")
    val commentsCount = readln().toInt()
    val comments = NetworkDummy.generateComments(commentsCount)


    println(
        """Введите номер команды:
        |1. Найти пользователя по id
        |2. createNewPost
        |3. findUsersByName""".trimMargin()
    )

    val command = readln().toInt()


    when (command) {
        1 -> {
            println("Введите id пользователя:")
            val userId = readln().toInt()
            val responseUser: ResultResponse = repository.findUserById(userId)
            when (responseUser) {
                is ResultResponse.Failure -> println("error found")
                is ResultResponse.Success -> {
                    val user: User = (responseUser).data as User
                    println("user found\n $user")
                    println("Показать посты пользователя (да/нет)?")
                    val showPosts = readln()
                    if (showPosts == Answer.YES.data) {
                        val responsePost = repository.getUserPosts(user)
                        when (responsePost) {
                            is ResultResponse.Failure -> println("error found")
                            is ResultResponse.Success -> {
                                println("posts found\n .........")
                                //как извлечь пост из возвр значения?
                                val posts = (responsePost.data as? MutableList<Post>) ?: mutableListOf()
                                println("Показать комментарии к первому посту (да/нет)?")
                                val showComments = readln()
                                if (showComments == Answer.YES.data) {
                                    val userId = user.id
                                    val responseComment = repository.getPostComments(posts[0])
                                    when (responseComment) {
                                        is ResultResponse.Failure -> println("error found")
                                        is ResultResponse.Success -> {
                                            println("posts found\n .........")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

//                2 -> {
//                    println("Введите id пользователя:")
//                    val userId = readln().toInt()
//
//                }


            }


        }

    }
}



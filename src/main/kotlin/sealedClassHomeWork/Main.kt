package sealedClassHomeWork



fun main(args: Array<String>) {

    //Создание репозитория сети
    val repository = SocialNetworkRepository()


    //генерация контента

    println("Введите число пользователей сети:")
    val usersCount = readln().toInt()
    val users = NetworkDummy.generateUsers(usersCount)
    val friendships = NetworkDummy.generateFriendships(users)

    println("Введите число постов пользователей:")
    val postsCount = readln().toInt()
    val posts = NetworkDummy.generatePosts(postsCount)

    println("Введите число комментариев к постам:")
    val commentsCount = readln().toInt()
    val comments = NetworkDummy.generateComments(commentsCount)


    println("""Введите номер команды:
        |1. Найти пользователя по id
        |2. createNewPost
        |3. findUsersByName""".trimMargin())

    val command = readln().toInt()


    when (command) {
        1 -> {
            println("Введите id пользователя:")
            val userId = readln().toInt()
            val response = repository.findUserById(userId)
            when (response) {
                is ResultResponse.Failure -> println("error found")
                is ResultResponse.Success -> {
                    println("user found\n ${ResultResponse.Success}.........")
                    //как вытащить пользователя из возвращ значения?
                    val user: User
                    println("Показать посты пользователя (да/нет)?")
                    val showPosts = readln()
                    if (showPosts == "да") {
                        val userId = user.id
                        val response = repository.getUserPosts(userId)
                        when (response) {
                            is ResultResponse.Failure -> println("error found")
                            is ResultResponse.Success -> {
                                println("posts found\n .........")
                                //как извлечь пост из возвр значения?
                                val post: Post
                                println("Показать комментарии к посту (да/нет)?")
                                val showComments = readln()
                                if (showComments == "да") {
                                    val userId = user.id
                                    val response = repository.getPostComments(post)
                                    when (response) {
                                        is ResultResponse.Failure -> println("error found")
                                        is ResultResponse.Success -> {
                                            println("posts found\n .........")
                                }
                            }
                            //как убрать else?
                            else -> {}
                        }
                    }
                }
                else -> {}  //как убрать else?
            }
        }

        2 -> {
            println("Введите id пользователя:")
            val userId = readln().toInt()

        }


    }


}



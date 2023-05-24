package sealedClassHomeWork


//объявление переменной repository вынесено за пределы метода main, чтобы был доступ к репо из класса SocialNetworkRepository
lateinit var repository: SocialNetworkRepository
lateinit var users: MutableList<User>
lateinit var posts: MutableList<Post>
lateinit var comments: MutableList<Comment>

fun main() {

    //Создание репозитория сети
    repository = SocialNetworkRepository()

    //генерация контента

    println("Введите число пользователей сети:")
    val usersCount = readln().toInt()
    users = NetworkDummy.generateUsers(usersCount)

    //val friendships = NetworkDummy.generateFriendships(users)
    //метод generateFriendships приводит к зависанию программы!!!

    println("Введите число постов пользователей:")
    val postsCount = readln().toInt()
    posts = NetworkDummy.generatePosts(postsCount)

    println("Введите число комментариев к постам:")
    val commentsCount = readln().toInt()
    comments = NetworkDummy.generateComments(commentsCount)

    while (true) {

        println(
            """
        |Введите номер команды:
        |1. Найти пользователя по id
        |2. Найти пользователей по имени
        |3. Найти посты пользователя по его id 
        |4. Найти комментарии к посту по id поста
        |5. Создать новый пост заданного пользователя
        |6. Выход""".trimMargin()
        )

        val command = readln().toInt()


        when (command) {
            1 -> {
                println("Введите id пользователя:")
                val userId = readln().toInt()
                val responseUser: ResultResponse = repository.findUserById(userId, users)
                when (responseUser) {
                    is ResultResponse.Failure -> println("Пользователь не найден")
                    is ResultResponse.Success -> {
                        val user: User = (responseUser).data as User
                        println("Найден пользователь:\n $user")
                    }
                }
            }

            2 -> {
                println("Введите имя:")
                val requestName = readln()
                val responseUsers = repository.findUsersByName(requestName, users)
                when (responseUsers) {
                    is ResultResponse.Failure -> println("Пользователи с таким именем не найдены")
                    is ResultResponse.Success -> {
                        val users: MutableList<User> = responseUsers.data as MutableList<User>
                        println("Найдены следующие пользователи:\n$users")
                    }
                }
            }

            3 -> {
                println("Введите id пользователя:")
                val userId = readln().toInt()
                val responsePosts: ResultResponse = repository.findUserPosts(userId, posts)
                when (responsePosts) {
                    is ResultResponse.Failure -> println("Посты не найдены")
                    is ResultResponse.Success -> {
                        val posts = (responsePosts.data as? MutableList<Post>) ?: mutableListOf()
                        println("Гайдены следующие посты:\n $posts")
                    }
                }
            }

            4 -> {
                println("Введите id поста:")
                val commentId = readln().toInt()
                val responseComment = repository.findPostComments(commentId, comments)
                when (responseComment) {
                    is ResultResponse.Failure -> println("Комментарии не найдены")
                    is ResultResponse.Success -> {
                        val comments = (responseComment.data as? MutableList<Post>) ?: mutableListOf()
                        println("Найдены следующие комментарии:\n $comments")
                    }
                }
            }

            5 -> {
                println("Введите id пользователя:")
                val userId = readln().toInt()
                println("Введите содержимое поста:")
                val postContent = readln()
                val newPostId = repository.createNewPost(userId, postContent)
                if (newPostId != null) {
                    println("Пост добавлен. Id поста: $newPostId")
                }
            }

            6 -> return

            else -> println("Неверно введен номер команды")

        }
    }
}
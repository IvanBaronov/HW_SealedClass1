package sealedClassHomeWork

class Post(id: Int, author: User, content: String) {

//    constructor(id: Int, author: User, content: String) {
//        this.id = id
//        this.author = author
//        this.content = content
//    }

    private var id = 0
    private lateinit var author: User
    private lateinit var content: String

    fun getId () : Int {
        return this.id
    }

    fun getAuthor () : User {
        return this.author
    }

    fun getContent () : String {
        return this.content
    }


}
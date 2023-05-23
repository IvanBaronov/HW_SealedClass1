package sealedClassHomeWork

class Comment(id: Int, post: Post, author: User, content: String) {

    private var id = 0
    private lateinit var author: User
    private lateinit var content: String
    private lateinit var post: Post

    fun getId () : Int {
        return this.id
    }

    fun getAuthor () : User {
        return this.author
    }

    fun getContent () : String {
        return this.content
    }

    fun getPost () : Post {
        return this.post
    }




}
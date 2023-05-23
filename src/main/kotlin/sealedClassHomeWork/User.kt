package sealedClassHomeWork

class User(id: Int, name: String, avatarUrl: String) {

    var id = 0
    private lateinit var name: String
    private lateinit var avatarUrl: String

    fun getId () : Int {
        return this.id
    }

    fun getName () : String {
        return this.name
    }

    fun getAvatarUrl () : String {
        return this.avatarUrl
    }
}